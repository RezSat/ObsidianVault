
> ❌ Error: **ValueError: Input contains NaN, infinity or a value too large for dtype('float64').**

- Cause: You missed a null value or a division by zero.

Fix:

```python
# Check which column is the culprit
print(df.isna().sum()) 
# Quick fix: replace inf with NaN then fill
df.replace([np.inf, -np.inf], np.nan, inplace=True)
df.fillna(df.median(), inplace=True)

```


> ❌ Error: **ValueError: Found input variables with inconsistent numbers of samples**

- Cause: Your X (features) and y (target) don't have the same number of rows.

Fix:

```python
print(X.shape, y.shape)
# Usually caused by dropping rows in X but not y. 
# Always align them:
df = df.dropna()
X = df[features]
y = df[target]
```


> ❌ Error: **MemoryError or Kernel Crash**

- Cause: Dataset is too large for RAM.
Fix:

1. Refer to [[01_Data_Cleaning]] for `optimize_memory()`.

2. Use `chunksize` when reading CSV: `pd.read_csv('big_data.csv', chunksize=10000)`.

❌ Issue: Model is 100% accurate (Overfitting)
Cause: "Data Leakage." You likely included the target variable (or a proxy for it) in your training features.

Fix: Check if any feature has a 1.0 correlation with the target in [[03_Exploratory_Data_Analysis]].