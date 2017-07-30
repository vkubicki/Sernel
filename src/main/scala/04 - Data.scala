import breeze.linalg._

object Data {
  /**
   * Given a vector of points generate the lower degree polynomial that passes through all of them.
   */
  def polynomial1D(x: DenseVector[Double], y: DenseVector[Double]): DenseVector[Double] = {
    val nPoints = x.length
    val vanderMat = vandermonde(x, nPoints)
    return vanderMat \ y
  }
  
  def vandermonde(x: DenseVector[Double], order: Int): DenseMatrix[Double] = {
    val nObs = x.length
    return DenseMatrix.tabulate[Double](nObs, order)((i, j) => math.pow(x(i), j))
  }
}