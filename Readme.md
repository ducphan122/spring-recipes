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

- Following this: https://www.digitalsanctuary.com/java/springboot-devtools-auto-restart-and-live-reload.html, but somehow the bootrun is not working hot reload, we have to start the project as debug. In cursor,
  vi no bi bug ntn nen se co 2 project, chon cai dau tien, qan trong la luc no restart ko được tạo thêm bất kì cái beans nào

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

If no recipes are found, the program should return an empty JSON array. If 0 parameters were passed, or more than 1, the server should return 400 (Bad Request). The same response should follow if the specified parameters are not valid. If everything is correct, it should return 200 (Ok).