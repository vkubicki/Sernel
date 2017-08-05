package p02kmeans

import breeze.linalg._
import p00rkhs.Gram

/**
 */
object Base {
  /**
   * Evolution of the computation, updated across iterations.
   * 
   * Note that the original data points are not carried out during the computation, as the Gram
   * matrix contains all the relevant information. This simplifies the algorithm and help
   * factorize it over multiple data types.
   * 
   * @param obsLearning 
   * @param gram Gram matrix for the observed data
   * @param squaredNorm squared norm of each column vector of the Gram matrix
   * @param center of classes, expressed in the data learning
   * @param zik contains 1 if observation i belong to class k, 0 otherwise
   */
  class ComputationState (
      val gram: DenseMatrix[Double],
      val param: DenseMatrix[Double],
      val zik: DenseMatrix[Double])
      
  def main {
    // compute Gram matrix, using Gram.generate
    // initialize algorithm by selecting a representative element per class and setting the class centers using them
    // launch the real computation, which alternates E and M steps, updating the computation state
    // the computation state contains all the information about the solution: the position of the class centers as weel as the class labels
  }
  
  /**
   * ||K_x_i - C_k||^2 = ||K_x_i||^2 + ||C_k||^2 - 2 <K_x_i, C_k>
   * C_k = \sum_j a_{j, k} K_x_j, and this decomposition will be used multiple times here
   * ||K_x_i||^2 = <K_x_i, K_x_i> which is a diagonal element of the Gram matrix
   * ||C_k||^2 can be computed and reused multiple times. Since (K_x_1, ..., K_x_n) are not orthogonal, ||C_k||^2 can not be expressed using only <K_x_i, K_x_i> elements.
   * <K_x_i, C_k> = \sum_j a_{j, k} <K_x_i, K_x_j> where a_{j, k} is the corresponding element of initialState.param
   * \sum_j a_{j, k} <K_x_i, K_x_j> is the scalar product between a row of initialState.param and the row corresponding to i in the Gram matrix
   * 
   * Is it the same to directly compute K_x_i - C_k in the (K_x_1, ..., K_x_n) basis and compute the scalar product with itself ?
   * Yes, but bilinearity of scalar product means there are n^2 terms to compute for each of the n observation (n^3 in total), most of them null because K_x_i is sparse in (K_x_1, ..., K_x_n) basis
   * This could easily be tested numerically...
   * */
  def eStep(initialState: ComputationState): ComputationState = {
    val nObs = initialState.zik.rows
    val nClass = initialState.zik.cols
    
    val squaredNormCk = initialState
      .param(::, *)
      .map(c => Gram.scalarProduct(initialState.gram, c, c)) // compute the norm of each center of class (each column of param) as it is a term used multiple times in the computation of the distance
    
    val dik = DenseMatrix.tabulate[Double](nObs, nClass)(
        (i, k) => initialState.gram(i, i) // element {i, k} of dik is ||K_x_i - C_k||^2
        + squaredNormCk(k)
        - 2.0 * Gram.scalarProduct(
            initialState.gram,
            initialState.param(::, k),
            initialState.gram(::, i)))
            
    val minLoc = dik(*, ::).map(r => argmin(r)) // line by line, find the min, get the column index
    
    val zik = DenseMatrix.tabulate[Double](nObs, nClass)((i, k) => if (minLoc(i) == k) 1.0 else 0.0)

    return new ComputationState( // create new zik matrix with 1 at each min, and 0 everywhere else
        initialState.gram,
        initialState.param,
        zik)
  }
  
  def mStep(initialState: ComputationState): ComputationState = {
    val nObs = initialState.zik.rows
    val nClass = initialState.zik.cols
    
    val colSum = initialState.zik(::, *).map(c => sum(c)) // zik compute columns sum
    val param = DenseMatrix.tabulate[Double](nObs, nClass)((i, k) => initialState.zik(i, k) / colSum(k)) // take the zik matrix, and normalize each column to get param
    return new ComputationState(
        initialState.gram,
        param,
        initialState.zik)
  }
  
  def emIteration(initialState: ComputationState): ComputationState = mStep(eStep(initialState))
}