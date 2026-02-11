## Handling missing data

Target Variable (comply with the business)
(try to get the percentages so like how many null values in each)

### Technique 01: Removing null values
using pandas `df.dropna(), df.dropna(how='all'), df.dropna(subset=['col1', 'clo2'])`

### Technique 02: Imputation Techniques
1. Constant Value Imputation
2. Statistical Imputation
3. Forward/Backward Fill (time series data)
4. Model Based Imputation (use regression, k-NN, or decision trees to predict missing values, using sklearn, fancyimpute, IterativeImputer - Data Scientist did not recommended because this is a output from first model to input to the second model, errors can be multiplied)

### Choosing the Right Technique ( Just from the Data Scientist Experience - No Hard Methods)
- Few nulls - Drop Rows
- Numeric Data - Mean/Median/Interpolation
- Categoric Data - Model/Constant value
- Time Series - FFill/BBill
- ML Preprocessing - Imputation + Indicator Column

## Handling Outliers

Using Quartiles and Box Plots for visualization

## Scaling & Transformation
- Standard Scaler - For linear models, Neural Networks
- Minmax Scaler - For distance based models
- Log/Box-Cox - For skewed data

