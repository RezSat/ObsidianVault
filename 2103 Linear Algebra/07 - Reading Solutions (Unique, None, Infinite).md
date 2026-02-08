# Reading Solutions (Unique, None, Infinite)

Assume you row-reduced an augmented matrix.

## Unique solution
You get pivots in every variable column (no free variables).
RREF looks like identity on the left:
$$
[I|\,\text{numbers}]
$$

## No solution (inconsistent)
You get a row like:
$$
[0\ 0\ \cdots\ 0 \mid 1]
$$
Meaning \(0 = 1\) (impossible).

## Infinitely many solutions
You get at least one free variable (a variable column without a pivot),
and no inconsistent row.
You’ll see a row like:
$$
[0\ 0\ \cdots\ 0 \mid 0]
$$
and fewer pivots than variables.

Related: [[05 - Reduced Row Echelon Form (RREF)]], [[10 - Common Mistakes and Checks]].
