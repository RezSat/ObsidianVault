# Row Reduction Shortcuts and Tricks

## 1) Use integer operations as long as possible
Avoid fractions early. Do eliminations using multiples; divide later.

## 2) Prefer pivots 1, -1, 2, -2
Row swaps are cheap. Getting a pivot that keeps arithmetic small is a win.

## 3) “Kill with the pivot” formula
To eliminate an entry \(a\) under pivot \(p\) in the same column:
\[
R_{\text{target}} \leftarrow R_{\text{target}} - \frac{a}{p} R_{\text{pivot}}
\]
If \(p=1\), it becomes:
\[
R_{\text{target}} \leftarrow R_{\text{target}} - aR_{\text{pivot}}
\]

## 4) Combine steps (when safe)
Instead of:
- make pivot 1,
- then eliminate,
often do elimination first and normalize later.

## 5) Eliminate the “densest” row last
If a row has lots of nonzeros, avoid using it as pivot early—more arithmetic.
Prefer pivot rows with zeros.

## 6) Use symmetry / pairing
If two rows differ only by signs or one term, subtracting them can instantly eliminate a variable (like we did when rows shared the same \(x\) coefficient).

## 7) Quick self-checks
After each pivot step:
- pivot column should become \([0,0,\dots,1,\dots,0]^T\) (eventually in RREF),
- numbers shouldn’t explode; if they do, consider a different pivot swap.

Companion: [[09 - Choosing the Best Next Row Operation]].
