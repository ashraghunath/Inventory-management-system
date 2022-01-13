# Inventory-management-system

Java - Spring boot applciation for inventory management system

## Details about application

### Ways to run the app
```bash
1) On Heroku 
Access the following link
https://ashwin-shopify-inventory.herokuapp.com/items
2) Locally using Docker (docker needs to be installed on the machine)
Run the following docker command on the terminal -
docker run -p 8081:8080 theashwinr/inventory-management-system:1.0
Here, 8081 is the host port and can be changed.
```
### Feature picked : Push a button export product data to a CSV
```bash
Export buttton shows up on top right of items page (NOTE: onyl if items exist)
Clicking on it will download the data into a CSV file
 ```
