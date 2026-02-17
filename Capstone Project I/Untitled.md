# Project Proposal  
## UniVenue: A Web-Based University Venue Booking and Approval System  

**Degree Program:** B.Sc. (Hons) in Data Science  
**Course:** Capstone Project I  
**Student Name:**
**Student Index:**
**Project Type:** Individual Project  

---

## 1. Introduction  

Efficient management of university venues such as auditoriums, lecture halls, and seminar rooms is essential for smooth academic and extracurricular operations. Currently, venue booking processes in many institutions rely on manual methods such as paper forms or email communication. This leads to scheduling conflicts, delays in approval, lack of transparency, and poor tracking of venue utilization.

This project proposes the development of **UniVenue**, a centralized web-based venue booking and approval system designed to streamline and digitize the university venue reservation process.

---

## 2. Problem Statement  

The existing manual or semi-manual venue booking process results in:

- Double bookings and scheduling conflicts  
- Delayed approval workflows  
- Lack of centralized tracking  
- Limited visibility of venue availability  
- Poor record-keeping and reporting  

There is a need for a centralized system that enables efficient booking, automated conflict detection, and structured approval management.

---

## 3. Project Objectives  

The primary objectives of this project are:

1. To design and implement a centralized web-based venue booking system.  
2. To implement automated time-slot conflict detection.  
3. To provide a structured approval workflow for venue requests.  
4. To maintain a digital record of bookings for monitoring and reporting purposes.  
5. To ensure role-based access control within the system.  

---

## 4. Proposed Solution  

UniVenue will be a web-based application that allows authorized users to request venue bookings and designated administrators to review and approve those requests.

The system will include:

- Real-time venue availability checking  
- Automated conflict validation  
- Single-level administrative approval  
- Booking status tracking  
- Historical booking records  

---

## 5. System Scope  

### 5.1 User Roles  

The system will support three primary roles:

**1. User (Student / Staff):**
- View available venues  
- Submit booking requests  
- View booking status  

**2. Approver (Dean or Authorized Administrator):**
- View pending booking requests  
- Approve or reject requests  
- Add remarks if necessary  

**3. System Administrator:**
- Manage venues  
- Manage user accounts  
- Oversee system operations  

---

### 5.2 Core Functionalities  

#### 1. Venue Management  
- Add and manage venue details (name, capacity, location)

#### 2. Booking Request Submission  
Users will specify:
- Venue  
- Date  
- Start time  
- End time  
- Event description  

#### 3. Automated Conflict Detection  
The system will automatically prevent overlapping bookings using time-slot validation logic to ensure no two approved bookings conflict.

#### 4. Approval Workflow  
All booking requests will initially be marked as **Pending**.  
The authorized approver can:
- Approve  
- Reject  

The booking status will be updated accordingly.

#### 5. Booking Records  
All requests (approved, rejected, pending) will be stored for tracking and reporting.

---

## 6. Technical Architecture  

### Frontend  
- Next.js (React-based framework)  
- Responsive user interface  

### Backend  
- RESTful API (Node.js / Next.js API routes)  
- Role-based access control  

### Database  
- PostgreSQL  
- Relational schema design with proper normalization  

---

## 7. Data Management and Analytics Component  

Although the system is primarily operational, it will include a basic analytical component suitable for a Data Science project:

- Venue usage statistics  
- Most frequently booked venues  
- Peak booking times  
- Booking trends over time  

These insights will be generated using structured database queries and basic data aggregation techniques.

---

## 8. Methodology  

The project will follow a structured development process:

1. Requirements Analysis  
2. System Design (Database Schema & Architecture)  
3. Implementation  
4. Testing (Functional and Conflict Testing)  
5. Documentation and Reporting  

---

## 9. Expected Outcomes  

Upon completion, the system will:

- Digitize the university venue booking process  
- Eliminate double-booking conflicts  
- Streamline administrative approvals  
- Provide structured booking records  
- Improve transparency and efficiency  

---

## 10. Project Feasibility  

The project is:

- Technically feasible within the semester timeline  
- Scalable for future enhancements  
- Suitable for real-world university deployment  
- Appropriate in scope for an individual capstone project  

The system design is intentionally focused and achievable within the available timeframe while maintaining academic and technical depth.

---

## 11. Future Enhancements (Beyond Current Scope)  

- Multi-level approval workflows  
- Email notifications  
- Maintenance scheduling  
- Advanced predictive booking analytics  
- Mobile application integration  

---

## 12. Conclusion  

UniVenue aims to provide a structured, efficient, and data-driven solution to university venue management. By replacing manual booking processes with a centralized web application, the system will improve operational efficiency, reduce scheduling conflicts, and provide valuable insights into venue utilization.

This project balances practical software engineering with data management and analytical components, making it well-suited for a Capstone Project in Data Science.
