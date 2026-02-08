# Choosing the Best Next Row Operation

## The decision principle
Pick the move that:
1) Creates a pivot easily, and
2) Minimizes arithmetic (small numbers, few steps, avoid fractions).

## Pivot choice order (practical)
1) If a row already has a 1 in the pivot column → use it.
2) Else use -1.
3) Else smallest absolute value.
4) Swap rows to bring that pivot into position.

## Elimination direction
- Going to REF: eliminate **below** pivot only.
- Going to RREF: eliminate below, then later eliminate **above**.

## “Which one: R2 - 2R1 or 2R1 - R2?”
Both can zero the target entry. Prefer:
- the one that keeps coefficients smaller,
- and avoids introducing negatives/fractions unnecessarily.

Rule of thumb:
To kill $(a)$ under pivot 1:
$$
R_{\text{target}} \leftarrow R_{\text{target}} - aR_{\text{pivot}}
$$
because it directly makes $(a-a=0).$

Related: [[08 - Row Reduction Shortcuts and Tricks]].
