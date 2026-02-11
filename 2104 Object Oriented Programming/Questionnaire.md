#  - Answers
## Question 1

Explain the difference between Procedural Programming and Object-OrientedProgramming. Give ONE advantage of OOP over procedural programming.

**Answer:**
Procedural programming is like writing step by step instructions using functions. Everything is separated into functions and data is kind of global or passed around.

OOP is based on objects and classes. It combines data and  functions together inside objects.

**One advantage of OOP:**  
Better data protection (encapsulation). Data is safer.

---

## Question 2

Define the following terms:
- a) Class
- b) Object
- c) Instance Variable

**Answer:**

a) **Class** – A blueprint/template used to create objects.

b) **Object** – An actual thing created from a class. It occupies memory.

c) **Instance Variable** – A variable inside a class that belongs to each object separately.

---

## Question 3

Using the blueprint analogy, explain the relationship between a Class and an Object.Provide a real-world example (not Student, Car, or House).

**Answer:**

A class is like a blueprint of a Phone.
The blueprint says: brand, model, price.
An object is the actual phone you buy from the shop.
So class is the design  
Object is the real physical item created from that design.

---

## Question 4

What is the purpose of the this keyword in Java? When and why do we use it?

`this` refers to the current object.
We use it when instance variables and method parameters have the same name.
Example:

```java
this.name = name;
```

It tells Java “use the object's variable”.

---

## Question 5

Explain what the new keyword does in Java. What happens in memory when you write:

**Answer:**
`new` creates a new object in memory.

When we write:
```
Student s1 = new Student();
```

- Memory is allocated in heap
- Object is created
- s1 stores reference (address) of that object

---

## Question 6

State THREE benefits of Object-Oriented Programming.

**Answer:**
1. Data security
2. Code reusability
3. Easy maintenance
    

---

## Question 7

In procedural programming (C), we had problems with data protection. Explain this problem and how Java OOP solves it using the private keyword.

**Answer:**
In C, data was public. Anyone could modify it directly.

In Java OOP, we can use `private` keyword to restrict direct access.  
Then we use methods (getters/setters) to control access.
So data is protected.

---

## Question 8

Mark the following statements as TRUE or FALSE:

a) A class is an actual entity that occupies memory: **FALSE**  
b) Multiple objects can be created from a single class: **TRUE**  
c) Each object has its own copy of instance variables: **TRUE**  
d) The `main()` method is the entry point of a Java program **TRUE**

---

## Question 9

What are the THREE types of variables in Java? Give a brief description of each.

**Answer:**
1. **Instance variable** – belongs to object
2. **Static variable** – belongs to class
3. **Local variable** – declared inside a method
    

---

## Question 10

Identify which of the following are valid Java identifiers. If invalid, state why:

a) studentName → **Valid**  
b) 1stStudent → **Invalid (cannot start with number)**  
c) _totalMarks → **Valid**  
d) class → **Invalid (reserved keyword)**  
e) $price → **Valid**

---

# PART B: CODING QUESTIONS

## Question 11

```java
public class Book {
	String title;
	String author;
	double price;
	
	// Main method
	public static void main(String[] args) {
		System.out.println("Hello World");
		}
}
```

---

## Question 12

```java
class Phone {
	String brand;
	String model;
	double price;
}
```

---

## Question 13

```java
class Car {
	String brand;
	String color;
	int year;
}

public class Main {
	public static void main(String[] args) {

		Car car1 = new Car();
		Car car2 = new Car();
		
		car1.brand = "Toyota";
		car1.color = "Red";
		car1.year = 2023;
		
		car2.brand = "Honda";
		car2.color = "Blue";
		car2.year = 2024;
		
		System.out.println(car1.brand);
		System.out.println(car2.brand);
	
	}
}
```

---

## Question 14

**Output:**

Alice is 20 years old  
Bob is 22 years old

---

## Question 15

```java
class BankAccount {

	String accountNumber;
	double balance;
	String ownerName;

	void displayInfo() {
		System.out.println("Account Number: " + accountNumber);
		System.out.println("Owner Name: " + ownerName);
		System.out.println("Balance: " + balance);
	}
}
```

---

## Question 16

1. Error: `Class`  
    Correction: `class`
2. Error: Missing semicolon after String name  
    Correction: `String name;`
3. Error: `Student s1 = Student();`  
    Correction: `Student s1 = new student();` (or rename class properly)
4. Error: Missing semicolon after println  
    Correction: add `;`
    

---

## Question 17

```java
void setDetails(String name, double price, int quantity) {
	this.name = name;
	this.price = price;
	this.quantity = quantity;
}

void display() {
	System.out.println("Product: " + name);
	System.out.println("Price: $" + price);
	System.out.println("Quantity: " + quantity);
}
```

---

## Question 18

```java
class Rectangle {

	double length;
	double width;
	
	double calculateArea() {
		return length * width;
	}

	public static void main(String[] args) {
	
		Rectangle r = new Rectangle();
		r.length = 5.0;
		r.width = 3.0;
		
		System.out.println("Area: " + r.calculateArea());
	
	}
}
```

---

## Question 19

a) To protect data (encapsulation).

b) Using public methods (getters/setters).

c)

```java
public void displaySpecs() {
	System.out.println("Brand: " + brand);
	System.out.println("RAM: " + ramSize);
	System.out.println("Price: " + price);
}
```

---

## Question 20

```java
class Book {

	String title;
	String author;
	boolean isAvailable;

	void borrowBook() {
		if (isAvailable) {
			isAvailable = false;
			System.out.println("Book borrowed successfully");
		} else {
			System.out.println("Book is not available");
		}
	}

	public static void main(String[] args) {
		
		Book b1 = new Book();
		b1.title = "Java Programming";
		b1.author = "James Gosling";
		b1.isAvailable = true;
		
		b1.borrowBook();
		b1.borrowBook();
	
	}
}
```

---

## Question 21

Compare and contrast Instance Variables and Static Variables. Give one examplewhere you would use a static variable.

**Answer:**
Instance variable → belongs to object  
Static variable → belongs to class

Example:  
`static int totalStudents;` to count total students.

---

## Question 22
Explain the difference between a **Method** in Java (OOP) and a **Function** in C(Procedural). Why don't methods need to receive the object as a parameter?

**Answer:**
Method belongs to a class.  
Function in C is independent.

Methods don’t need object as parameter because they are already called using object reference.

---

## Question 23

No.
Because s1 and s2 are different objects in memory.
Changing s1.name does not affect s2.name.

---

## Question 24

What is **encapsulation** in Object-Oriented Programming? Explain how the privatekeyword helps achieve encapsulation and give ONE benefit of encapsulation.

**Answer:**
Encapsulation means hiding data and controlling access.
`private` prevents direct access.
Benefit: Data protection.

---

## Question 25

public is accessible from anywhere  
static only belongs to class  
void means returns nothing

---

## Question 26

C1: 7  
C2: 11

---

## Question 27

```java
class Employee {

	int employeeId;
	String name;
	double salary;
	
	void giveRaise(double percentage) {
		salary = salary + (salary * percentage / 100);
	}
	
	void displayDetails() {
		System.out.println("ID: " + employeeId);
		System.out.println("Name: " + name);
		System.out.println("Salary: " + salary);
	}
}
```

---

## Question 28

Errors:

1. Missing semicolon after brand
2. `system` should be `System`
3. `Println` should be `println`
4. `mobile` should be `Mobile`
5. Missing parentheses in `showInfo()`
6. Two public classes in same file
7. Case issues
8. Missing semicolon after price
9. `showInfo;` should be `showInfo();`
10. General syntax consistency
    

---

## Question 29

```java
class Circle {

	double radius;
	
	double calculateArea() {
		return 3.14 * radius * radius;
	}
	
	double calculateCircumference() {
		return 2 * 3.14 * radius;
	}
}
	
public class Main {
	public static void main(String[] args) {
	
		Circle c = new Circle();
		c.radius = 7.0;
		
		System.out.println("Area: " + c.calculateArea());
		System.out.println("Circumference: " + c.calculateCircumference());
	
	}
}
```

---

## Question 30

```java
class Student {

	String name;
	double marks1;
	double marks2;
	double marks3;

	double calculateAverage() {
		return (marks1 + marks2 + marks3) / 3;
	}

	void displayInfo() {
		System.out.println("Name: " + name);
		System.out.println("Average: " + calculateAverage());
	}
}
```

---
Edited and Formatted with Typst (Latex Alternative)