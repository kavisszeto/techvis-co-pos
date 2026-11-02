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
<p align="center">
  <img src="screenshots/order-entry.png" width="600">
</p>
The interface displays the selected products, shopping cart, customer details, and receipt preview. Employees can review the order before completing the checkout process.

### Customer Information Validation
<p align="center">
  <img src="screenshots/customer-validation.png" width="600">
</p>
The system checks whether all required customer information has been provided. If any fields are incomplete, a warning message is displayed and the previously entered customer information is cleared.

### Product Maintenance
<p align="center">
  <img src="screenshots/add-product-1.png" width="600">
</p>
This interface allows employees to search for smartphone products by ID, name, or price. Products can also be filtered by brand and displayed in a table for easier management.

### Add Product
<p align="center">
  <img src="screenshots/add-product-2.png" width="600">
</p>
Employees can enter the details of a new smartphone and add it to the database. A confirmation message is displayed when the product has been successfully added.

### Product Management
<p align="center">
  <img src="screenshots/update-delete-product-db.png" width="600">
</p>
<p align="center">
  <img src="screenshots/update-delete-product-pos.png" width="600">
</p>
<p align="center">
  <img src="screenshots/update-delete-product-db2.png" width="600">
</p>
Employees can select an existing product (e.g. Galaxy S21 FE) and remove it from the database. The system displays a confirmation message after the deletion is completed.

### Database Structure
<p align="center">
  <img src="screenshots/database-structure_order_detail.png" width="600">
</p>
This table stores the products associated with each order, including product IDs, quantities, prices, and names. Foreign keys link each record to the corresponding order and product.
<p align="center">
  <img src="screenshots/database-structure_sale_order.png" width="600">
</p>
This table stores order records, including order dates, total prices, and customer information such as names, phone numbers, and delivery addresses.
<p align="center">
  <img src="screenshots/database-structure_smtph.png" width="600">
</p>
This table stores smartphone product information, including product IDs, brands, names, prices, image filenames, and descriptions.

## What I Learned

Through this project, I gained practical experience in:

- Developing desktop applications using Java and JavaFX.
- Designing graphical user interfaces and handling user interactions.
- Connecting a Java application to a MySQL database.
- Performing database operations, including creating, retrieving, updating, and deleting records (CRUD).
- Designing relational database tables using primary keys and foreign keys.
- Implementing input validation and handling incomplete customer information.
- Integrating product management and order processing into one application.

## Additional Documentation

A detailed project documentation in Traditional Chinese (繁體中文) is also available in Microsoft Word format.

📄 [View Traditional Chinese Documentation](docs/Techvis-Co-Project-Documentation-zh-TW.docx)

## Project Information

- **Project Type:** Academic / Individual Project
- **Course:** Object-Oriented Programming
- **Institution:** National Kaohsiung University of Science and Technology (NKUST)
- **Completion Date:** June 2023

---

*This project was developed for academic purposes as a demonstration of JavaFX application development and relational database integration.*
