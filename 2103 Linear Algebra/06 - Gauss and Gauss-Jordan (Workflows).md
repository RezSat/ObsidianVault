# Gauss and Gauss–Jordan (Workflows)

## Gauss elimination (to REF)
Use when you want speed and easy back-substitution.

1) Augmented matrix [[01 - Augmented Matrix]]
2) For each pivot column:
   - pick a good pivot row (swap if needed),
   - eliminate below pivot (make zeros below).
3) Stop at REF [[04 - Row Echelon Form (REF)]]
4) Back-substitute to solve.

## Gauss–Jordan (to RREF)
Use when you want a final “answers visible” matrix.

1) Do Gauss elimination to REF.
2) Scale pivot rows so pivot = 1.
3) Eliminate above pivots (zeros above).
4) Stop at RREF [[05 - Reduced Row Echelon Form (RREF)]]

Speed: [[08 - Row Reduction Shortcuts and Tricks]].
Choosing moves: [[09 - Choosing the Best Next Row Operation]].
