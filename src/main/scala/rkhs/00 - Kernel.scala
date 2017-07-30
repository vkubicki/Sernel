package rkhs

import breeze.linalg._

/**
 * Biobliography:
 * -	https://en.wikipedia.org/wiki/Reproducing_kernel_Hilbert_space#Examples
 */
object Kernel {
  def linear(x: DenseVector[Double], y: DenseVector[Double]): Double = x dot y
}