# Augmented Matrix

## Definition
An **augmented matrix** is the coefficient matrix with the constants appended as the last column.

For a system
$$ 
\
a_{11}x_1+ \cdots+a_{1n}x_n=b_1\\
\vdots\\
a_{m1}x_1+\cdots+a_{mn}x_n=b_m
the augmented matrix is:

\left[\begin{array}{cccc|c}
a_{11}&a_{12}&\cdots&a_{1n}&b_1\\
\vdots&\vdots&&\vdots&\vdots\\
a_{m1}&a_{m2}&\cdots&a_{mn}&b_m
\end{array}\right]
\
$$

## Why we use it
- Keeps bookkeeping clean.
- Row operations become mechanical.
- Scales to many equations/variables.

## How to build it (fast)
1. Pick a variable order (e.g., \(x,y,z\)).
2. Each equation → one row.
3. Missing variable? Put 0.
4. Constants go after the bar.

Next: [[02 - Elementary Row Operations]] and [[06 - Gauss and Gauss-Jordan (Workflows)]].
