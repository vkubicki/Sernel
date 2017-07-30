package rkhs

import breeze.linalg._

/**
 * Note that only here are the data types known. In the Gram package, everything occurs in the RKHS, and the data types need to to be nown since
 * only the kernel evaluations are required.
 * Biobliography:
 * -	https://en.wikipedia.org/wiki/Reproducing_kernel_Hilbert_space#Examples
 */
object Kernel {
  def product(x: Double, y: Double): Double = x * y
  
  def gaussian(x: Double, y: Double, sd: Double): Double = math.exp(- math.pow(x - y, 2) / (2 * math.pow(sd, 2)))
  
  def linear(x: DenseVector[Double], y: DenseVector[Double]): Double = x dot y
}