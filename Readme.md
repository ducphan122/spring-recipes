JetBrains Academy - Recipe Project

## Learning Outcomes

Get to know the backend development. Use Spring Boot to complete this project. Learn about JSON, REST API, Spring Boot Security, H2 database, LocalDateTime, Project Lombok, and other concepts useful for the backend.
This project is a part of the following track
Java Backend Developer.

What you’ll do and what you’ll learn

<details open="open">
  <summary>Task Details</summary>
  <ol>
  <li><a href="tasks/1-first-recipe/README.md">First Recipe</a>: Create a Spring Boot Project with Endpoints</li>
  <li><a href="tasks/2-multiple-recipes/README.md">Multiple Recipes</a>: Save and Delete recipes in memory</li>
  <li><a href="tasks/3-store-a-recipe/README.md">Store a Recipe</a>: Save and Delete Recipes from a database</li>
  <li><a href="tasks/4-sort-and-update/README.md">Sort & Update</a> : Filter results with queries</li>
  <li><a href="tasks/5-more-chefs-to-the-table/README.md">More Chefs to the table</a>: Learn User Authentication and authorization</li>
  </ol>
</details>

## Start the project

`./gradlew build`
`./gradlew bootRun`

## API

- POST /api/recipe/new
  {
  "name": "Fresh Mint Tea",
  "description": "Light, aromatic and refreshing beverage...",
  "ingredients": [
  "boiled water",
  "honey",
  "fresh mint leaves"
  ],
  "directions": [
  "Boil water",
  "Pour boiling hot water into a mug",
  "Add fresh mint leaves",
  "Mix and let the mint leaves seep for 3-5 minutes",
  "Add honey and mix again"
  ]
  }
  Response: 200, body: {
  "id": 1
  }

- GET /api/recipe/{id}
  Response: 200, body: {...}

- DELETE /api/recipe/{id}
  Response: 204

## Stage 3

Store all recipes permanently in a database: after a server restart, all added recipes should be available to a user;

- Implement a new DELETE /api/recipe/{id} endpoint. It deletes a recipe with a specified {id}. The server should respond with the 204 (No Content) status code. If a recipe with a specified id does not exist, the server should return 404 (Not found);
- The service should accept only valid recipes – all fields are obligatory, name and description shouldn't be blank, and JSON arrays should contain at least one item. If a recipe doesn't meet these requirements, the service should respond with the 400 (Bad Request) status code.

## Stage 4
This is what your program can do:

POST /api/recipe/new receives a recipe as a JSON object and returns a JSON object with one id field;
GET /api/recipe/{id} returns a recipe with a specified id as a JSON object;
DELETE /api/recipe/{id} deletes a recipe with a specified id.

In this stage, the recipe structure should contain two new fields:

- category represents a category of a recipe. The field has the same restrictions as name and description. It shouldn't be blank;
- date stores the date when the recipe has been added (or the last update). You can use any date/time format, for example 2021-09-05T18:34:48.227624 (the default LocalDateTime format), but the field should have at least 8 characters.

Also, the service should support the following endpoints:

- PUT /api/recipe/{id} receives a recipe as a JSON object and updates a recipe with a specified id. Also, update the date field too. The server should return the 204 (No Content) status code. If a recipe with a specified id does not exist, the server should return 404 (Not found). The server should respond with 400 (Bad Request) if a recipe doesn't follow the restrictions indicated above (all fields are required, string fields can't be blank, arrays should have at least one item);

- GET /api/recipe/search takes one of the two mutually exclusive query parameters:

1. category – if this parameter is specified, it returns a JSON array of all recipes of the specified category. Search is case-insensitive, sort the recipes by date (newer first);
2. name – if this parameter is specified, it returns a JSON array of all recipes with the names that contain the specified parameter. Search is case-insensitive, sort the recipes by date (newer first).
3. If no recipes are found, the program should return an empty JSON array.

## Stage 5

The stage is divided into 3 steps. In the first step, you need to add an endpoint responsible for the user registration. The endpoint receives 2 fields: email and password. The second step is to enable Spring Security and configure the access restrictions – only the registered users with the correct login and password should have the rights to use the service. After that, restrict the deletion and updating to the recipe author only.
The service should contain all features from the previous stages. To complete the project, you need to add the following functionality:

- New endpoint POST /api/register receives a JSON object with two fields: email (string), and password (string). If a user with a specified email does not exist, the program saves (registers) the user in a database and responds with 200 (Ok). If a user is already in the database, respond with the 400 (Bad Request) status code. Both fields are required and must be valid: email should contain @ and . symbols, password should contain at least 8 characters and shouldn't be blank. If the fields do not meet these restrictions, the service should respond with 400 (Bad Request). Also, do not forget to use an encoder before storing a password in a database. BCryptPasswordEncoder is a good choice.

- Include the Spring Boot Security dependency and configure access to the endpoints – all implemented endpoints (except /api/register) should be available only to the registered and then authenticated and authorized via HTTP Basic auth users. Otherwise, the server should respond with the 401 (Unauthorized) status code.

- Add additional restrictions – only an author of a recipe can delete or update a recipe. If a user is not the author of a recipe, but they try to carry out the actions mentioned above, the service should respond with the 403 (Forbidden) status code.

- For testing purposes, POST/actuator/shutdown should be available without authentication.

Tip: If you use Postman or any similar program for testing and receive 403 (Forbidden), try to disable CSRF (Cross-Site Request Forgery) protection. You can disable this type of protection by calling the following methods – .csrf(csrf -> csrf.disable()) on the HttpSecurity instance injected in the method where you build a SecurityFilterChain object.

If you use the H2 console, you need to unblock it by disabling CSRF and X-Frame-Options that prevents clickjacking attacks, by calling the following methods: .csrf(csrf -> csrf.disable()).headers(headers -> headers.frameOptions().disable()) on the HttpSecurity instance. Also, make sure that Spring Security does not block the H2 console URLs.