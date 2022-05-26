# How to use

## 1. Run MySQL in Docker
`application.yml` already contains connection information to this DB.
```shell script
docker-compose up -d
```

## 2. Run the Spring Boot application
At the first run, sample data will be loaded, including 1000 products, 1 admin, 1 customer, 1 cart, and 1 order.

## 3. Test API end-points at OpenApi UI
Navigate to ```http://localhost:8080/swagger-ui.html``` to view all the end-points and test them.

To login as an admin, please send a ```POST /auth/login``` with request body
```json
{
  "email": "admin@hardvare.com",
  "password": "admin"
}
```
Paste the token responded in the authentication header or in the "Authorize" button in OpenApi UI

To login as a customer, please login with
```json
{
  "email": "customer1@gmail.com",
  "password": "customer1"
}
```

## Search feature
To search a product, please input the search parameter in ```GET /products``` with operations,
<br> ```:``` alike match of String/ exact match with numbers,
<br> ```>``` larger than,
<br> ```<``` smaller than

The end-point also supports pagination.

E.g.
To search products with name contains "Computer" and price higher than 50 at page 1 with size 5, request
<br> ```GET /products?search=name:computer,price>50&page=1&size=5```
