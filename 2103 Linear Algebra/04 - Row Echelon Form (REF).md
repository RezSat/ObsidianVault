# Row Echelon Form (REF)

## Definition (REF)
A matrix is in **row echelon form** if:
1) All nonzero rows are above any all-zero rows.
2) Each leading entry (pivot) of a lower row is to the right of the pivot above it.
3) All entries below each pivot are zero.

## Why do REF
- REF is enough to solve using **back-substitution**.
- Often faster than full RREF.

## How to get REF (Gauss)
- Move left to right:
  1) Choose a pivot.
  2) Zero out entries **below** it using row operations.
  3) Move to next row and next column.

Workflow file: [[06 - Gauss and Gauss-Jordan (Workflows)]].
Speed tips: [[08 - Row Reduction Shortcuts and Tricks]].
