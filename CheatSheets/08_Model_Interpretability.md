#Snippets #shap #interpreting
"Black box" models aren't enough for business. Managers will ask: _"Why did the model say this bus would be late?"_

```python
import shap

# 1. SHAP Values (The "Gold Standard" of explanation)
explainer = shap.TreeExplainer(xgb_model)
shap_values = explainer.shap_values(X_test)

# Visualize why a single prediction was made
shap.initjs()
shap.force_plot(explainer.expected_value, shap_values[0,:], X_test.iloc[0,:])

# 2. Feature Importance (Quick & Dirty)
import matplotlib.pyplot as plt
plt.barh(X.columns, xgb_model.feature_importances_)
```