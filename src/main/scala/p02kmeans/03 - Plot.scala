package p02kmeans

import breeze.linalg._
import p00rkhs.{Gram, Kernel}
import p02kmeans.Base.ComputationState
import p04various.Iterate

object Plot {
  class plotLim(
      val xMin: Double,
      val xMax: Double,
      val yMin: Double,
      val yMax: Double)
  
//  def plotRes(lim: plotLim, gram: DenseMatrix[]) {
//    
//  }
}