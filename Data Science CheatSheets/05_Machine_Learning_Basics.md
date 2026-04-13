#Snippet #machine #learning #sklearn #scikit 
The standard toolkit for any Datathon.

```python
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import StandardScaler
from sklearn.ensemble import RandomForestRegressor

# 1. The Robust Pipeline
# Scales data and trains model in one go to prevent data leakage
pipeline = Pipeline([
    ('scaler', StandardScaler()),
    ('model', RandomForestRegressor(n_estimators=100))
])

pipeline.fit(X_train, y_train)
```