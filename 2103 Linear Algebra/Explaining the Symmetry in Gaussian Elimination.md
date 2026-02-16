
Gaussian Elimination can be worked in both direction, although it is best to teach the usual standard way of going from Augmented Matrix $->$ REF $->$ Do Back Substitution

The method will automatically works for the operations to be reversed obtaining a Lower Triangular Matrix ($L$) (instead of a typical REF (Row Echelon Form) which is an special type of Upper Triangular Matrix ($U$)) and then do a Forward Substitution. (But again, it is more standard and easy to teach as Upper Triangular Matrix is more standard as REF)

This property of supporting both ways can be described in multiple terms:

#### 1. Directional Invariance

Means that the fundamental algebraic property of the system does not change regardless of whether you process the equations from top-to-bottom or bottom-to-top.
#### 2. Duality in Elimination

This could be one of the most formal way to describe this,
- Forward Elimination: Produces a Upper Triangular
- Backward Elimination: Produces a Lower Triangular 
#### 3. Reflective Property of LU Elimination

Given a matrix $U$ (Upper Triangular or REF), its transpose is a matrix $L$ which is a Lower Triangular.
This property therefore can referred to as **Transpositional Symmetry**.

>So $"A"x = b$ if we have $U$ (with back-sub) we automatically have a method for $L$ (with forward-sub) simply by "reflecting" the operations across the main diagonal.
#### 4. Persymmetric View 

While this isn't particularly given for this Gaussian method, this is also a name come up to near to explaining these properties. If a given matrix is symmetrical about its anti-diagonal ( the one form bottom-left to top-right) is called Persymmetric.

> Although, Gaussian Elimination isn't strictly Persymmetric.

Other formally acceptable terms like Algorithmic Symmetry, Forward v Backward Substitution Paris, Lower-Upper duality