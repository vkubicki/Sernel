package p02kmeans

import breeze.linalg._
import p00rkhs.{Gram, Kernel}

object SimpleExample {
  def main {
    val proportion = DenseVector[Double](0.2, 0.8)
    val param = Vector(
        Vector(
            new Data.GaussianClassParam(0.0, 1.0),
            new Data.GaussianClassParam(10.0, 1.0)),
        Vector(
            new Data.GaussianClassParam(0.0, 1.0),
            new Data.GaussianClassParam(10.0, 1.0)))
    val nObs = 10
    
    val data = Data.gaussianMixture(
      proportion,
      param,
      nObs)
      
    val gram = Gram.generate(data, Kernel.linear) // compute Gram matrix
    // initialize algorithm by selecting a representative element per class and setting the class centers using them
    // launch the real computation, which alternates E and M steps, updating the computation state
    // the computation state contains all the information about the solution: the position of the class centers as weel as the class labels
  }
}