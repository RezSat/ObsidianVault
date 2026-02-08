# Elementary Row Operations

## The three legal moves
These do NOT change the solution set of the system:

1) Swap rows  
$$R_i \leftrightarrow R_j$$

2) Scale a row by a nonzero number  
$$
R_i \leftarrow cR_i,\quad c\neq 0
$$

3) Replace a row by itself plus a multiple of another row  
$$
R_i \leftarrow R_i + cR_j
$$

## Why they’re valid (intuition)
They match legal equation operations:
- swapping equations,
- multiplying an equation by a nonzero constant,
- adding a multiple of one equation to another.

## Practical note
Operation (3) is the “workhorse” for eliminating entries under/above pivots.

Next: [[03 - Pivots and Leading Entries]].
