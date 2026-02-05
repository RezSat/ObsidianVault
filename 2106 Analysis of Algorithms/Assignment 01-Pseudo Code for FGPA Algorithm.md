
**Problem**: Write an algorithm in pseudocode to calculate a student’s Final Grade Point Average (FGPA) based on the grades and credit values of all registered GPA courses, in accordance with the GPA calculation rules outlined in Pages 90–91 of the Student Handbook 2023/2024 (Faculty of Computing).

**Algorithm:**

Definitions: 
- Each course will have two properties:
	- credits (numerical value), 
	- attempts (list - where the last one is the most recent)
- Each attempt has a grade (or marks)

```

INPUT: 
	For each year y in (1,2,3,4):
		Courses[y] = list of GPA courses taken in year y
		
DEFINED CONSTANTS:
	year_weight[1, 2] = 0.2
	year_weight[3, 4] = 0.2
	A_plus = 4.00
	A = 4.00
	A_minus = 3.70
	B_plus = 3.30
	B = 3.00
	B_minus = 2.70
	C_plus = 2.30
	C = 2.00
	C_minus = 1.70
	D_plus = 1.30
	D = 1.00
	E = 0.00
	
// Function that will return grade by taking marks m
FUNCTION GRADE_FROM_MARKS(m):
	IF m >= 90 RETURN A_plus
	ELSE IF 80 <= m <= 89 RETURN A
	ELSE IF 75 <= m <= 79 RETURN A_minus
	ELSE IF 70 <= m <= 74 RETURN B_plus
	ELSE IF 65 <= m <= 69 RETURN B
	ELSE IF 60 <= m <= 64 RETURN B_minus
	ELSE IF 55 <= m <= 59 RETURN C_plus
	ELSE IF 50 <= m <= 54 RETURN C
	ELSE IF 45 <= m <= 49 RETURN C_minus
	ELSE IF 40 <= m <= 44 RETURN D_plus
	ELSE IF 30 <= m <= 39 RETURN D
	ELSE RETURN E
	
```

