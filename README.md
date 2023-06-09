# Hotel Reservations

## Characteristics:
Language: Java 11  
Build automation tool: Maven  
Framework: Spring Boot  
External Libraries: Lombok

## API Description:
This API creates, updates, and reads **Reservations**.  
These **Reservations** are also stored locally in a `reservations` file in the root of the project.  
There's input validation, like checking for nulls; making sure one date and room combination is not repeated in another reservation; that the dates are not from the past, etc.  
When an input validation is not successful, it returns an error response with a message, and the application is still running.  
The code is structured in different layers, to differentiate between the controller, business logic, and what would be the repository (in this case just a local storage).


## Consuming the API
Get all reservations  
GET localhost:8080/api/v1/reservation  

Create a reservation  
POST localhost:8080/api/v1/reservation  
Body: `{
  "clientFullName": "Dave",  
  "roomNumber": 50,
  "reservationDates": [
  "2023-04-18"
]
}`

Modify a reservation  
PUT localhost:8080/api/v1/reservation  
Request param: id  
Body: `{
"clientFullName": "Dave",  
"roomNumber": 50,
"reservationDates": [
"2023-04-18"
]
}`

## Author
Jose Manuel Alarid Cons Gastelum