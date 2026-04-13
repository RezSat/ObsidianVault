#Snippet #modeling #xgboost #lightgbm #catboost #tabular #gradient

When simple models fail or when you're competing for the top of the leaderboard. These handle non-linear relationships and complex tabular data.

```python
from xgboost import XGBClassifier
from lightgbm import LGBMRegressor
from catboost import CatBoostClassifier

# 1. XGBoost: The Competition King
# Great for handled missing values automatically
xgb = XGBClassifier(n_estimators=1000, learning_rate=0.05, max_depth=6)
xgb.fit(X_train, y_train, early_stopping_rounds=50, eval_set=[(X_val, y_val)])

# 2. LightGBM: The Speed Demon
# Use this if your dataset is huge (millions of rows)
lgbm = LGBMRegressor(num_leaves=31, learning_rate=0.05, n_estimators=100)

# 3. CatBoost: The Categorical Master
# Use this if you have many string/category columns (No One-Hot Encoding needed!)
cat = CatBoostClassifier(iterations=500, learning_rate=0.1, cat_features=cat_dims)
cat.fit(X_train, y_train)

# 4. Simple Ensemble (Voting)
# Combining different models usually boosts accuracy by 1-2%
from sklearn.ensemble import VotingClassifier
ensemble = VotingClassifier(estimators=[('xgb', xgb), ('cat', cat)], voting='soft')
```