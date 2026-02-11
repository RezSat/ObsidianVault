Handling missing data
Target Variable (comply with the business)
(try to get the percentages so like how many null values in each)

Technique 01: Removing null values
using pandas `df.dropna(), df.dropna(how='all'), df.dropna(subset=['col1', 'clo2'])`

Technique 02: Imputation Techniques
1. Constant Value Imputation
2. Statistical Imputation
3. Forward/Backward Fill (time series data)
4. Model Based Imputation (use regression, k-NN, or decision trees)
