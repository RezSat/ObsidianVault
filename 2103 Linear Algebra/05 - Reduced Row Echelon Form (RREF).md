# Reduced Row Echelon Form (RREF)

## Definition (RREF)
A matrix is in **reduced row echelon form** if:
1) It is in [[04 - Row Echelon Form (REF)]].
2) Every pivot is exactly 1.
3) Each pivot is the ONLY nonzero entry in its column (zeros above and below).

## Why do RREF
- Solutions can be read off immediately.
- Great for:
  - identifying free variables,
  - parametric solutions,
  - consistency checks.

## How to get RREF (Gauss–Jordan)
1) Get REF.
2) Scale pivots to 1.
3) Zero out entries **above** each pivot.

Workflow file: [[06 - Gauss and Gauss-Jordan (Workflows)]].
