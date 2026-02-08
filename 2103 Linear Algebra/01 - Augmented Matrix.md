# Augmented Matrix

## Definition
An **augmented matrix** is the coefficient matrix with the constants appended as the last column.

For a system

$$
cases(
  a_(11) x_1 + ... + a_(1n) x_n = b_1,
  dots.v,
  a_(m 1) x_1 + ... + a_(m n) x_n = b_m,
)
$$

The augmented matrix is:

$$
#let  n = 4 // set this to the number of coefficient columns (n)
$$

$$
mat(
  delim: "[",
  augment: n,

  a_(11), a_(12), ..., a_(1n), b_1;
  dots.v,  dots.v,  dots.down, dots.v, dots.v;
  a_(m 1),  a_(m 2),  ...,      a_(m n), b_m;
)
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
