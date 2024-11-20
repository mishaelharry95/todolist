To-Do List API
This is a RESTful API for managing a simple To-Do List application. It provides endpoints for creating, retrieving, updating, and deleting to-do items. The API is built with Spring Boot and uses an H2 in-memory database.

Features
1. Create a new to-do item.
2. Retrieve all to-do items.
3. Update an existing to-do item.
4. Delete a to-do item.

Prerequisites
Before you begin, ensure you have the following installed:

Java JDK 17 or higher
Maven (optional if using an IDE like IntelliJ IDEA or Eclipse)
Postman or cURL (to test the API, optional)
Setup and Run
1. Clone the Repository
   git clone <repository_url>
   cd todo-list-api
2. Build the Project
   Use Maven to build the project: mvn clean install
4. Run the Application
   Start the application using Maven:
   mvn spring-boot:run
   Or use your IDE to run the main method in the TodoListApiApplication class.

The server will start at http://localhost:8080.

4. Access the H2 Console (Optional)
   To access the database, go to:
   http://localhost:8080/h2-console
   JDBC URL: jdbc:h2:mem:todo_db
   Username: sa
   Password: password

API Endpoints
1. Create a To-Do Item
   Endpoint:
   POST /api/todos
Request Body (JSON):
{
"title": "Buy groceries",
"completed": false
}

Response:
201 Created: Item created successfully.
400 Bad Request: Invalid input.

Example (cURL):
curl -X POST -H "Content-Type: application/json" \
-d '{"title": "Buy groceries", "completed": false}' \
http://localhost:8080/api/todos

2. Retrieve All To-Do Items
   Endpoint:
   GET /api/todos

Response:
200 OK: Returns a list of all to-do items.
Example (cURL):
curl -X GET http://localhost:8080/api/todos

3. Update a To-Do Item
   Endpoint:
   PUT /api/todos/{id}

Request Body (JSON):
{
"title": "Buy groceries and snacks",
"completed": true
}
Response:
200 OK: Item updated successfully.
404 Not Found: Item not found.
Example (cURL):
curl -X PUT -H "Content-Type: application/json" \
-d '{"title": "Buy groceries and snacks", "completed": true}' \
http://localhost:8080/api/todos/1

4. Delete a To-Do Item
   Endpoint:
   DELETE /api/todos/{id}

Response:
204 No Content: Item deleted successfully.
404 Not Found: Item not found.
Example (cURL):
curl -X DELETE http://localhost:8080/api/todos/1
