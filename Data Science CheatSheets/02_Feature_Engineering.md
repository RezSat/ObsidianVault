#Snippet #feature #engineering

Creating "signals" out of "noise." This is where you win competitions.
```python
# 1. Cyclical Time Features (Sin/Cos encoding)
# Tells the model that Hour 23 is close to Hour 0
def encode_cyclical_time(df, col, max_val):
    df[col + '_sin'] = np.sin(2 * np.pi * df[col]/max_val)
    df[col + '_cos'] = np.cos(2 * np.pi * df[col]/max_val)
    return df

# 2. Lag Features (For Time Series/Forecasting)
# "What happened 1 hour ago?"
df['prev_hour_value'] = df['target'].shift(1)

# 3. Binning (Turning continuous into categorical)
df['age_bins'] = pd.cut(df['age'], bins=[0, 18, 35, 60, 100], labels=['Youth', 'Adult', 'Senior', 'Elder'])
```