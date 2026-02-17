**Project Title:**  
Design and Implementation of a Web-Based Inventory and Supply Chain Analytics System for Small and Medium Enterprises (SMEs)

**Background and Motivation:**  
Many SMEs in Sri Lanka manage inventory and supplier operations using spreadsheets or manual record-keeping systems. These approaches often lead to inaccurate stock tracking, inefficient reorder decisions, limited demand visibility, and poor supplier performance analysis. Existing enterprise ERP systems are typically costly and overly complex for small businesses. Therefore, there is a need for a lightweight, data-driven system that integrates structured inventory management with analytical reporting.

**Problem Statement:**  
The project aims to design a web-based system capable of managing inventory and supplier data efficiently, systematically processing stock transaction data, and generating analytical insights such as demand trends, inventory turnover, and reorder recommendations while maintaining relational integrity and scalable database design.

**Objectives:**

- Design a normalized relational database schema for inventory and supply chain entities.
    
- Implement core modules for product management, supplier management, warehouse tracking, and transaction logging.
    
- Apply data cleaning and transformation techniques to transaction data.
    
- Develop analytical features including inventory turnover calculation, demand aggregation (daily/weekly/monthly), reorder point estimation, and supplier performance metrics.
    
- Evaluate system correctness and performance.
    

**Scope:**  
Included features:

- Multi-organization support within a single platform
    
- Role-based user access control
    
- Inventory and warehouse management
    
- Stock-in and stock-out transaction logging
    
- PostgreSQL-based relational data modeling
    
- Analytical dashboards and reporting
    
- Rule-based reorder alerts
    

Excluded features:

- Machine learning-based forecasting models
    
- Distributed systems or cluster architecture
    
- Payment gateway integration
    
- Full enterprise ERP functionality
    

**Proposed Technologies:**

- Frontend: React / Next.js
    
- Backend: FastAPI (Python)
    
- Database: PostgreSQL (deployed via Supabase)
    

PostgreSQL is selected for its strong relational integrity, indexing capabilities, analytical query support (including window functions), and ability to handle semi-structured data using JSONB. Supabase will be used as a managed PostgreSQL deployment platform with secure authentication and row-level security features.

**Expected Outcome:**  
A fully functional web-based inventory and supply chain analytics system demonstrating relational database design, data processing techniques, analytical SQL implementation, and full-stack system integration.

I kindly request your feedback and approval for this proposed topic. I am open to any suggestions or modifications you may recommend.

Thank you for your time and consideration.

Best regards,  
Yehan