# Pivots and Leading Entries

## Definitions
- **Leading entry**: first nonzero number in a row (from left).
- **Pivot**: a leading entry chosen as the “anchor” for elimination.
- **Pivot column**: the column containing a pivot.

## Why pivots matter
They define the “staircase” structure in [[04 - Row Echelon Form (REF)]] and let you:
- eliminate entries below (REF),
- eliminate below and above (RREF).

## Pivot strategy (fast)
- Prefer pivots that are:
  - 1 or -1 (easy arithmetic),
  - small numbers,
  - already positioned without swapping.
- Swap rows early to get a nice pivot.

Next: [[09 - Choosing the Best Next Row Operation]].
