# Checkout Staffing Planner

## Project Overview
This is an API application for employees to add their preferred shifts to come to work on a wishbook and admins to add the employees to the actual schedule according to their preferences.
The framework used is Spring because it offers plenty of helpful functionalities that save a lot of time, such as query methods.  
It contains models for creating an employee object, a wishbook object and a schedule object, together with their repositories, services and controllers that send calls to the API.
## Installation instructions:
- For this project to run, you will need to have installed MySQL. The Spring project was generated from https://start.spring.io/ with the dependencies: **Spring Web, Spring Data JPA and MySQL Driver**  
- The setup of the database is written in _src/main/resources/application.properties_.  
- Before running the code, connect to MySQL Command Line and execute the command: **create database checkoutstaffing**. The database needs to be created before, otherwise it will throw an error.
- For testing I used Postman to send requests
## Usage instructions:
- After the database is created and the code runs, in Postman I created a few employees. It's a POST request and the url needed is: http://localhost:8080/employee/addEmployee and it request a JSON format body as shown below:  
**{  
  &ensp; "name": "Andrea",  
  &ensp; "admin": "false",  
  &ensp; "password": "testing"  
}**

OR

**{  
&ensp; "name": "Raul",  
&ensp; "admin": "true",  
&ensp; "password": "admin"  
}**

Create as many users as needed. You can see all the employees added by sending the following GET request: http://localhost:8080/employee/all or a specific user by name and password: http://localhost:8080/employee/find/{name}/{password}.


**<span style="color: red;">!!! It is mandatory for each user to have a name, a password and to be assigned if it's an admin or not
</span>**

- Add to any date and shift any users with the following POST request: http://localhost:8080/wishbook/add/{employeeName}/{employeePassword}/{date}/{shift}  
  Where:    
      - **employeeName** is the name of the employee that you want to add to the wishbook  
      - **employeePassword** is the password of the employee that you want to add to the wishbook  
      - **date and shift** are the date shift when the employee wants to be added to the wishbook


  Example with the user above:  
    http://localhost:8080/wishbook/add/Andrea/testing/2025-07-21/EARLY  
  **<span style="color: red;">!!! The user name and password must belong to a user that is NOT an admin, as an admin cannot be added to wishbooks</span>**



  **<span style="color: red;">!!! This is the format in which the date should be written. The shift is either "LATE" or "EARLY" </span>**  

- Add an user to a schedule if it also matches with their wishbook with the following POST request: https://localhost:8080/schedule/add/{adminId}/{employeeName}/{employeePassword}/{date}/{shift}  
 Where: 
     - **adminId** must be the id of an admin user, as this is the user that would execute the command
     - the rest of the variables have the same meaning as when adding to a wishbook
  
Example with the users above:  
https://localhost:8080/schedule/add/2/Andrea/testing/2025-07-21/EARLY

I set the adminId to 2 because I added only two users with my examples and my admin user would take the second id, but it can depend on how many employees are created and in which order.  
**<span style="color: red;">!!! If the adminId does not belong to an admin user, the application will throw an exception. Same procedure applies if it is tried to add an admin to a wishlist/schedule or if a schedule date does not coincide with any wishbook dates. </span>**   

To find a specific schedule by date, execute the following command: https://localhost:8080/schedule/find/{date}






  
