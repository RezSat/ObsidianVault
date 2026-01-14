Problems at Larger Scale
 -  No Data Protection
 ```
 students[0].marks = -999; // Invalid but allowed!
 students[0].age = -5; // Nonsense;
 students[0].id = 9999; // Could be duplicate;
 ```
 - Function Explosion
 ```
 addStudent(), displayStudent(), calculateStudentAverage(), addTeacher(), displayTeacher()....hundreds of functions!
 ```
 - Hard to maintain
 ```
 Changing One strucuture (ex:student) might require updating 30+ functions
 ```
 
 