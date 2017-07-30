# Sernel

Kernel methods in Scala, to learn, at the moment.

# To Do

## Short term

- Implement kernel regression, because it contains a lot of basic ideas about RKHS, kernel and the use of the Representer Theorem

## Medium term

- Gram matrix symmetry should be exploited to reduce computation times
- BFGS might not be the best algorithm to exploit the quadratic nature of the Representer Theorem optimisation problem.
    - A more specialized algorithm might help enhance performances.
    - There is a quadratic optimizer in Scala Breeze

## Long term

- Use the existing framework to implement other kernel methods:
    - k-means
    - SVM

# Bugs

- "WARNING: Failed to load implementation from: com.github.fommil.netlib.NativeSystemBLAS" on Windows