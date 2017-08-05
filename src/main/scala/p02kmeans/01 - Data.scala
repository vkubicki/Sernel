package p02kmeans

import breeze.linalg._
import breeze.stats.distributions._

/**
 * First data generation is simple, gaussian mixture model.
 */
object Data {
  class GaussianClassParam (
      val mean: Double,
      val sd: Double)
  
  /**
   * Gaussian Mixture Model is simple to write. It is a good candidate to generate data
   * for a k-means algorithm. More "distorted" data will be generated with other methods
   * to test the performance of various kernels.
   * 
   * @param proportion marginal probability for each label
   * @param param param[j][k] returns the GaussianClassParam for the k class of the j variable
   * @param nObs number of observations to be generated
   * @result DenseMatrix_ij where i is the observation index and j is the variable number
   */
  def gaussianMixture(
      proportion: DenseVector[Double],
      param: Vector[Vector[GaussianClassParam]],
      nObs: Int)
  : DenseVector[DenseVector[Double]] = {
    val nVar = param.size
    val multiSampler = Multinomial(proportion) // sampler for the latent class
    val varSamplerVec = param.map(v => v.map(c => Gaussian(c.mean, c.sd)))
    val zi = DenseVector.fill[Int](nObs)(multiSampler.sample)

    val data = zi.map(k => DenseVector.tabulate[Double](nVar)(j => varSamplerVec(j)(k).sample))
    
    return data
  }
  
  def gaussianMixtureTest {
    val proportion = DenseVector[Double](0.2, 0.8)
    val param = Vector(
        Vector(
            new GaussianClassParam(0.0, 1.0),
            new GaussianClassParam(10.0, 1.0)),
        Vector(
            new GaussianClassParam(0.0, 1.0),
            new GaussianClassParam(10.0, 1.0)))
    val nObs = 10
    
    val data = gaussianMixture(
      proportion,
      param,
      nObs)
      
    data.foreach(println)
  }
}