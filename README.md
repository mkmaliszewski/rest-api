# rest-api

This project is using REST architecture to operate on a database, which contains list of contacts, each one having basic information such as name, last name, number, e-mail and date of birth. The databse used in the project is PostgreSQL, here is the SQL code to create required table:

```
CREATE TABLE contacts
(
    id SERIAL PRIMARY KEY,
    name text NOT NULL,
    last_name text NOT NULL,
    mail text NOT NULL,
    number text NOT NULL,
    born text NOT NULL
);
```

The project returns data formatted as json or xml, depending on request headers. The available operations are:
- /api/list with a get request to retrieve all contacts,
- /api/contact/id with a get request to retrieve single contact with specified id,
- /api/add with post request filled with correct data to add a new contact,
- /api/delete/id with delete request to delete the contact with specified id,
- /api/update/id with put request filled with correct data to update the contact with specified id.
