import breeze.linalg._

object Gram {
  /**
   * Observations are presented in a matrix, where each row is an observation, and each
   * column is a variable. The kernel is passed as an argument.
   * */
  def generate(
      observations: DenseMatrix[Double],
      kernel: (DenseVector[Double], DenseVector[Double]) => Double)
  : DenseMatrix[Double] = {
    val nObs = observations.rows
    
    return DenseMatrix.tabulate[Double](nObs, nObs)((i, j) => kernel(observations(i, ::).t, observations(j, ::).t))
  }
  
  def generateTest {
    val obs = DenseMatrix(
        (1.0, 2.0),
        (3.0, 4.0),
        (5.0, 6.0))
        
    val gram = generate(obs, Kernel.linear)
    
    println(gram)
  }
}
