# Techvis Co. – Integrated POS System

A desktop Point of Sale (POS) application developed using JavaFX and MySQL. The system allows store employees to manage smartphone products, process customer orders, and store transaction records in a database through a graphical user interface.

## Project Overview

Techvis Co. is a fictional smartphone retail business. This project was developed as an extension of an earlier JavaFX POS application, with a focus on database integration and product management.

The application provides an interface for employees to browse smartphone products, add items to a shopping cart, manage product information, and complete customer orders. Product details and transaction records are stored in a MySQL database.

## Key Features

### 1. Order Management
- Browse smartphone products by brand, including Apple, Samsung, and Google.
- Add products to the shopping cart and adjust item quantities.
- Apply a gift voucher discount of $1,000.
- Display an order summary and receipt preview.
- Enter customer details, including name, phone number, and delivery address.
- Validate customer information before confirming an order.
- Generate a receipt as a text file during checkout.
- Save completed orders and their associated product details to the database.

### 2. Product Management
- Search for products by brand, product ID, name, or price.
- View product information in a table.
- Add new products to the database.
- Update existing product information.
- Delete products from the database.
- Display operation results and system messages through the interface.

### 3. Database Integration

The application uses MySQL to store product information and order records. The database consists of three main tables:

| Table | Description |
|---|---|
| `smtph` | Stores smartphone information, including brand, name, price, image filename, and description. |
| `sale_order` | Stores order information, including order date, total price, and customer details. |
| `order_detail` | Stores the products and quantities associated with each order. |

Primary keys and foreign keys are used to establish relationships between the tables and maintain connections between orders and their product details.

## Technologies Used

- **Programming Language:** Java
- **GUI Framework:** JavaFX
- **Database:** MySQL
- **Database Management:** XAMPP / phpMyAdmin

## Screenshots

Screenshots below demonstrate the main functions and interface of the application.

### Order Entry System

The order entry interface allows employees to browse products, manage the shopping cart, enter customer information, and review the order before checkout.

### Product Maintenance

The product maintenance interface provides tools for searching, adding, updating, and deleting smartphone products.

### Database Structure

The MySQL database contains three related tables: `smtph`, `sale_order`, and `order_detail`, which store product information and completed transactions.

## What I Learned

Through this project, I gained practical experience in:

- Developing desktop applications using Java and JavaFX.
- Designing graphical user interfaces and handling user interactions.
- Connecting a Java application to a MySQL database.
- Performing database operations, including creating, retrieving, updating, and deleting records (CRUD).
- Designing relational database tables using primary keys and foreign keys.
- Implementing input validation and handling incomplete customer information.
- Integrating product management and order processing into one application.

## Project Information

- **Project Type:** Academic / Individual Project
- **Course:** Object-Oriented Programming
- **Institution:** National Kaohsiung University of Science and Technology (NKUST)
- **Completion Date:** June 2023

---

*This project was developed for academic purposes as a demonstration of JavaFX application development and relational database integration.*
