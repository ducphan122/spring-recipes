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
