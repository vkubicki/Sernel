package p02kmeans

import breeze.linalg._
import p00rkhs.{Gram, Kernel}
import p02kmeans.Base.ComputationState
import p04Various.Iterate

object SimpleExample {
  def main {
    val proportion = DenseVector[Double](0.3, 0.7)
    val paramGenerator = Array(
        Array(
            new Data.GaussianClassParam(0.0, 1.0),
            new Data.GaussianClassParam(10.0, 1.0)),
        Array(
            new Data.GaussianClassParam(0.0, 1.0),
            new Data.GaussianClassParam(10.0, 1.0)))
    val nObs = 10
    val nClass = 2
    
    val nIteration = 1
    
    val data = Data.gaussianMixture(
      proportion,
      paramGenerator,
      nObs)
      
    val gram = Gram.generate(data.data, Kernel.linear) // compute Gram matrix
    val param = Base.init(nObs, nClass) // initialize the algorithm by selecting a representative element per class and setting the class centers using them
    val zeroCompState = new ComputationState(
        0,
        gram,
        param,
        DenseMatrix.zeros[Double](nObs, nClass))
    val initCompState = Base.eStep(zeroCompState) // an e step is performed, so that the ComputationState is valid
    
//    val res = Stream // launch the real computation, which alternates E and M steps, updating the computation state
//      .iterate(initCompState)(Base.emIteration)
//      .take(nIteration)
//      .last
    
    val res = Iterate.iterate( // launch the real computation, which alternates E and M steps, updating the computation state
        initCompState,
        Base.emIteration,
        (s: ComputationState) => s.nIteration == nIteration)
      
    println(res.zik)
    println(data.zi)
  }
}