Why Scala - Vincent KUBICKI - 2017

# Abstract

This document summarizes the experience of doing numerical computations in Scala. It is based on my experience coding this library. I will not include external resources or researches. Coming from a math / physics background and not a CS background, I lack a complete picture of the type system, category theory and such. This is really a description of Scala applied to non trivial statistical computations.

# Pro

## libraries

- Breeze provides linear algebra, probabilities and vizualization
- The use of functional programming and the fact that Spark is written in Scala might help porting to this platform.

## functional programming

- Drastically reduces the need for canonical design patterns.
- Is very similar to the mathematical way of describing a computation, which is often declarative in nature.

## software engineering
- Hierarchical package system (vs flat file structures in an R package)

## other

- Once the code is written and compiles, it is highly likely to be correct and show a minimum of runtime bugs.
- Using constructs like map and reduce make the algorithm easy to scale using Spark for example.
- Immutability removes a lot of errors, like use of uninitialized variables.
- Strong typing allows to avoid errors like using the entire vector instead of a particular vector element.
- For the rare cases where immutability is impractible, it is easy to locally use mutability.
- Powerful IDE.
- Compiles to jar that can be used on any installed jvm.
    - With sbt assembly plugin, a fat jar will contain all the dependencies, easing distribution.
- SBT allows for easy retrieval of dependencies

# Cons

- Less people trained in Scala than in Python or R.
   - But Scala is concise and easy to learn once the basic concepts are understood.
   - If immutability is a problem, it is easy to temporarily shift to a more familiar, mutable style of programming.