#Snippet #cleaning #row #pandas #numpy
Turning raw, "dirty" data into something usable. Essential for Sri Lankan datasets with missing GPS or inconsistent labels.

```python
import pandas as pd
import numpy as np

# 1. Quick Info & Missing Data Summary
def check_health(df):
    print(df.info())
    return df.isnull().sum() / len(df) * 100

# 2. Handling Outliers (Z-Score method)
def remove_outliers(df, col):
    upper = df[col].mean() + 3*df[col].std()
    lower = df[col].mean() - 3*df[col].std()
    return df[(df[col] < upper) & (df[col] > lower)]

# 3. Memory Optimization (Crucial for large Datathon files)
def optimize_memory(df):
    for col in df.select_dtypes(include=['int']).columns:
        df[col] = pd.to_numeric(df[col], downcast='integer')
    for col in df.select_dtypes(include=['float']).columns:
        df[col] = pd.to_numeric(df[col], downcast='float')
    return df
```
Go to [[02_Feature_Engineering]] once data is clean.