Visualizing patterns before building models.

```python
import seaborn as sns
import matplotlib.pyplot as plt

# 1. Correlation Heatmap (Find redundant features)
def plot_corr(df):
    plt.figure(figsize=(10, 8))
    sns.heatmap(df.corr(), annot=True, cmap='coolwarm')
    plt.show()

# 2. Distribution Plot (Check for Skewness)
sns.histplot(df['target_variable'], kde=True)

# 3. Pairplot (Spot clusters quickly)
sns.pairplot(df, hue='target_category')
```