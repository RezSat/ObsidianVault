#Snippet
For "Actual Business Solving." Converting ML accuracy into money/impact.

```python
# 1. Precision-Recall Tradeoff (Business Logic)
# Precision: "When we predict a delay, how often is it actually delayed?" (Avoid false alarms)
# Recall: "Of all delays, how many did we catch?" (Avoid missing problems)

# 2. Calculating Expected Value (Business Impact)
# Cost of False Positive: LKR 500
# Gain of True Positive: LKR 2000
def business_gain(y_true, y_pred):
    tp = sum((y_true == 1) & (y_pred == 1))
    fp = sum((y_true == 0) & (y_pred == 1))
    return (tp * 2000) - (fp * 500)
```

See [[05_Machine_Learning_Basics]] for the model that generates these predictions.