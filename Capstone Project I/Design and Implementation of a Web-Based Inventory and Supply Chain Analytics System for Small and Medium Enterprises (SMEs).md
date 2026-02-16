

## 1. Background and Motivation

Small and Medium Enterprises (SMEs) in Sri Lanka often rely on spreadsheets or manual record-keeping systems to manage inventory and supplier operations. These approaches can lead to:

- Inaccurate stock tracking  
- Poor visibility into demand trends  
- Inefficient reorder decisions  
- Limited analytical insight into supplier performance  

While enterprise ERP systems exist, they are often expensive and overly complex for SMEs. There is a need for a lightweight, data-driven system that combines structured inventory management with analytical reporting.

This project aims to design and implement a web-based system that not only manages inventory transactions but also processes and analyzes supply chain data to support informed decision-making.

---

## 2. Problem Statement

How can a web-based system be designed to:

1. Efficiently manage inventory and supplier data for SMEs  
2. Process stock transaction data systematically  
3. Generate meaningful analytics such as demand trends, inventory turnover, and reorder recommendations  

while maintaining relational integrity and scalable database design?

---

## 3. Objectives

### Main Objective

To design and implement a web-based inventory and supply chain analytics system using PostgreSQL for structured data management and analytical processing.

### Specific Objectives

1. Design a normalized relational database schema for inventory and supply chain entities.
2. Implement core modules for:
   - Product management  
   - Supplier management  
   - Warehouse stock tracking  
   - Transaction logging  
3. Apply data cleaning and transformation techniques on transaction data.
4. Develop analytical features including:
   - Inventory turnover calculation  
   - Demand aggregation (daily/weekly/monthly)  
   - Reorder point estimation  
   - Supplier performance metrics  
5. Evaluate system correctness and performance.

---

## 4. Scope of the Project

### Included

- Multi-organization support within a single platform  
- Role-based user access control  
- Inventory and warehouse management  
- Stock-in and stock-out transaction logging  
- PostgreSQL-based relational data modeling  
- Analytical dashboards and reporting  
- Rule-based reorder alerts  

### Excluded

- Machine learning-based demand forecasting  
- Distributed system architecture  
- Payment processing or billing integration  
- Full enterprise ERP functionality  

---

## 5. System Architecture Overview

The system will follow a three-tier architecture:

### 1. Presentation Layer
- React / Next.js frontend

### 2. Application Layer
- FastAPI backend (Python)
- REST API endpoints for business logic

### 3. Data Layer
- PostgreSQL database (deployed via Supabase)
- Use of:
  - Relational schema design
  - Indexing strategies
  - Window functions
  - Aggregation queries
  - JSONB fields where appropriate

Supabase will be used as a managed PostgreSQL deployment environment with built-in authentication and row-level security.

---

## 6. Methodology

1. Requirement analysis and stakeholder identification  
2. Database schema design (ER diagrams, normalization)  
3. Backend API development  
4. Frontend interface development  
5. Data processing implementation:
   - Cleaning and validation  
   - Aggregation queries  
   - Metric computation  
6. Dashboard and reporting implementation  
7. Testing and validation  
8. Performance evaluation  

---

## 7. Expected Deliverables

- Fully functional web-based system  
- PostgreSQL database schema and documentation  
- Analytical dashboard with computed supply chain metrics  
- Technical documentation  
- Final report and presentation  

---

## 8. Feasibility Analysis

### Technical Feasibility
All selected technologies are open-source and well-supported.

### Time Feasibility
The project will be developed in modular phases across the semester.

### Cost Feasibility
No paid software or infrastructure required.

---

## 9. Expected Contribution

This project demonstrates:

- Application of relational database design principles  
- Practical implementation of data cleaning and transformation  
- Use of analytical SQL techniques for business intelligence  
- Integration of full-stack web technologies with structured data systems  
