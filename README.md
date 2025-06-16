# visa-challenge-petstore-davidg

This repository contains the solution for the implementation of test automation and performance testing for the Pet Store API.

---

## 📌 Approach

The approach I followed was to complete the full CRUD functionality for the `pet` endpoint

---

## ✅ Test Cases

The test cases were automated in the following order:

1. Add a new pet to the Store  
2. Find a pet by ID  
3. Find a pet by tag  
4. Find a pet by status  
5. Update an existing pet by ID  
6. Update an existing pet based on form data  
7. Upload image of the pet  
8. Delete a pet by ID

> ⚠️ **Note:** The execution order was intentionally defined to guarantee successful responses.  
> For example, running the delete service before the create service would result in a failed response due to the missing resource.

---

## ⚙️ Error Handling

Failed responses are handled with custom exceptions.  
This implementation ensures that unexpected or invalid scenarios are also covered as part of the testing strategy.  
Depending on the context, we can adjust the expected response to validate alternative outcomes and improve test coverage.

---

## 🛠️ Tools Used

- **Java 11** – Core language programming 
- **JUnit** – for test execution
- **SerenityRest** – for API automation 
- **RestAssured** – for API automation
- **SerenityBdd** – for report Generation
- **Cucumber** – for Gherkin and BDD structure
- **Maven / Gradle** – for build and dependency management  
- **Apache JMeter** – for performance testing  
- **Excel / CSV** – for test data management in performance testing

---

## ▶️ How to Run the Tests

### 🧪 Functional Tests

The project can run the automated tests using either **Gradle** or **Maven**

#### Using Maven:

```bash
mvn clean test
```

To generate the Serenity report after the tests:

```bash
mvn serenity:aggregate
```
#### Using a Runner Class

It is also possible to run the tests using the `PetRunner` class directly.  
Also the **tags** are used to filter and execute specific test scenarios.  
Currently, the implementation runs by default with the tag:

```java
@CucumberOptions(tags = "@ApiTest")
```

This tag is included in all the test scenarios, ensuring complete coverage unless a specific subset is required.

---

### 🚀 Performance Tests

1. Open **Apache JMeter**.
2. Load the performance test plan located in the `petStorePerformance` folder.
3. Ensure test data (e.g., `names.csv`) is in the correct path.

---
