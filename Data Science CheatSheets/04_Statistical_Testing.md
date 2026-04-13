#Snippet #statistical #testing #scipy #numpy 
To prove that the results aren't just "luck." In business, this justifies a budget; in a Datathon, it validates your feature engineering.

```python
from scipy import stats
import numpy as np

# 1. Normality Test (Shapiro-Wilk)
# ALWAYS do this first. If p > 0.05, data is normal (use T-test). 
# If p < 0.05, it's not (use Mann-Whitney).
def check_normality(data):
    stat, p = stats.shapiro(data)
    return "Normal" if p > 0.05 else "Not Normal"

# 2. Comparing 2 Groups (The "A/B Test" standard)
# Does Strategy A result in higher revenue than Strategy B?
t_stat, p_val = stats.ttest_ind(group_a, group_b) 

# 3. Comparing 3+ Groups (ANOVA)
# Is there a difference between Morning, Afternoon, and Night bus delays?
f_stat, p_val = stats.f_oneway(group_1, group_2, group_3)

# 4. Categorical Independence (Chi-Square)
# Is "Customer Type" (Student/Pro) related to "Purchase" (Yes/No)?
contingency_table = pd.crosstab(df['type'], df['purchased'])
chi2, p, dof, ex = stats.chi2_contingency(contingency_table)
```

Validated findings here can be fed into [[05_Machine_Learning_Basics]].