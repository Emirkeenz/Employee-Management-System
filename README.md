# Employee Management System

This project consists of two main components:

1. A **Java application** for managing employee data with CRUD operations in a relational database using JDBC.  
2. A **JavaFX application** for managing a list of employees and their salaries, using an abstract class structure to model different types of employees.

---

## Project Structure

### 1. Java Application: Database Management with JDBC

**Features:**

- **Database Setup:**
  - A database named `employee_db` with a table `employee` that includes:
    - `id` (int, primary key, auto-increment)
    - `name` (varchar)
    - `position` (varchar)
    - `salary` (double)
    - `hire_date` (date)

- **Entity Class:**
  - `Employee`: Models the employee data with fields:
    - `id`, `name`, `position`, `salary`, `hireDate`
    - Includes constructors, getters, setters, and a `toString()` method.

- **Data Access Class:**
  - `EmployeeData`: Handles database operations using JDBC. Methods include:
    - `createEmployee(Employee employee)`: Adds a new employee.
    - `getEmployeeById(int id)`: Retrieves an employee by ID.
    - `getAllEmployees()`: Fetches all employees.
    - `updateEmployee(Employee employee)`: Updates an employee's details.
    - `deleteEmployee(int id)`: Deletes an employee by ID.
    - A method to establish and manage database connections.

- **Testing:**
  - A `main()` method demonstrates CRUD operations:
    - Add an employee.
    - Retrieve and display an employee by ID.
    - Retrieve and display all employees.
    - Update an employee's details.
    - Delete an employee.

---

### 2. JavaFX Application: Employee Salary Management

**Features:**

- **Abstract Class Design:**
  - `Employee` (abstract class): Defines `calculateSalary()` method.
  - Subclasses implement `calculateSalary()`:
    - `FullTimeEmployee`: Calculates a fixed annual salary.
    - `PartTimeEmployee`: Calculates salary based on hourly wage and hours worked.
    - `Contractor`: Calculates salary based on an hourly rate and max monthly hours.

- **GUI Design:**
  - **TableView**: Displays employee details:
    - Name, Employee Type, Calculated Salary.
  - **Input Fields**:
    - Name, Type (Dropdown for Full-time, Part-time, Contractor).
    - Additional fields for Part-time and Contractor:
      - Hours worked (Part-time), Max hours (Contractor), Hourly rate.
  - **Buttons**:
    - Add Employee: Adds an employee to the table.
    - Calculate Salaries: Updates calculated salaries.
    - Remove Employee: Deletes an employee from the table.

- **Validation:**
  - Ensures proper input (e.g., positive numbers for hours and salary).

---

## Getting Started

### Prerequisites

- **JDK 17** or higher  
- **JavaFX SDK**  
- **MySQL or PostgreSQL** (for the database)  
- **JDBC Driver** (MySQL or PostgreSQL)

---

### Setup Instructions

#### Database Component:

1. **Create the database:**
   ```sql
   CREATE DATABASE employee_db;
   USE employee_db;

   CREATE TABLE employee (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100),
       position VARCHAR(100),
       salary DOUBLE,
       hire_date DATE
   );
2. **Configure database connection:**
  - Update the EmployeeData class with your JDBC URL, username, and password.

#### JavaFX Component:

1. Set up JavaFX:
   - Add the JavaFX SDK to your project.

2. Run the application:
   - Launch the JavaFX GUI to manage employees and calculate their salaries.

---

# Screenshots:
<img width="748" alt="Снимок экрана 2024-11-25 в 14 09 41" src="https://github.com/user-attachments/assets/eabed85b-0b5d-4406-8204-a0f41a9d3591">

---

# Future Improvements:

1. Fixing bugs related to updating data on existing employees
2. Add search and filtering options in the GUI.
3. Enable exporting employee data to CSV or Excel files.
