Why Scala - Vincent KUBICKI - 2017

# Abstract

This document summarizes the experience of doign numerical computations in Scala. It is based on my experience coding this library. I will not include external ressources or researches. Coming from a math / physics background and not a CS background, I lack a complete picture of the type system, category theory and such. This is really a description of Scala applied to non trivial statistical computations.

# Pro

- Once the code is written and compiles, it is highly likely to be correct and show a minimum of runtime bugs
- Using constructs like map and reduce make the algorithm easy to scale using Spark for example
- Immutability removes a lot of errors, like use of uninitialized variables
- strong typing allows to avoid errors like using the entire vector instead of a particular vector element
- For the rare cases where immutability is impractible, it is easy to locally use mutability.

# Cons