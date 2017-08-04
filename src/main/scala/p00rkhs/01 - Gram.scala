package p00rkhs

import breeze.linalg._

object Gram {
  /**
   * Observations are presented in a matrix, where each row is an observation, and each
   * column is a variable. The kernel is passed as an argument.
   * */
  def generate[Data](
      observations: DenseVector[Data],
      kernel: (Data, Data) => Double)
  : DenseMatrix[Double] = {
    val nObs = observations.length
    
    return DenseMatrix.tabulate[Double](nObs, nObs)((i, j) => kernel(observations(i), observations(j)))
  }
  
  def generateTest {
    val obs = DenseVector[DenseVector[Double]](
        DenseVector[Double](1.0, 2.0),
        DenseVector[Double](3.0, 4.0),
        DenseVector[Double](5.0, 6.0))
        
    val gram = generate(obs, Kernel.linear)
    
    println(gram)
  }
}
