# Augmented Matrix

## Definition
An **augmented matrix** is the coefficient matrix with the constants appended as the last column.

For a system

the augmented matrix is:

$$
cases(
  a_(1 1) x_1 + ... + a_(1 n) x_n = b_1,
  dots.v,
  a_(m 1) x_1 + ... + a_(m n) x_n = b_m,
)

mat(
  delim: "[",
  augment: 4,  // replace 4 with your actual n (must be an integer)

  a_(1 1), a_(1 2), ..., a_(1 n), b_1;
  dots.v,  dots.v,  dots.down, dots.v, dots.v;
  a_(m 1), a_(m 2), ..., a_(m n), b_m;
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
