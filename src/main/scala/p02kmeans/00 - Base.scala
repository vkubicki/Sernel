package p02kmeans

import breeze.linalg._

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
  }
  
  def eStep(initialState: ComputationState): ComputationState = {
    // compute distance in 
    // line by line, find the mode, get the row index
    // create new zik matrix where, for each column, the 
    return new ComputationState(
        DenseMatrix.zeros[Double](3, 3),
        DenseMatrix.zeros[Double](3, 3),
        DenseMatrix.zeros[Double](3, 3))
  }
  
  def mStep(initialState: ComputationState): ComputationState = {
    // take the zik matrix, and normalize each column to get param
    return new ComputationState(
        DenseMatrix.zeros[Double](3, 3),
        DenseMatrix.zeros[Double](3, 3),
        DenseMatrix.zeros[Double](3, 3))
  }
  
  def emIteration(initialState: ComputationState): ComputationState = mStep(eStep(initialState))
}