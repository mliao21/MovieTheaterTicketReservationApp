To Run the Database:

- First Run the sql file in `ensf614project/ENSF_614_G8.sql`
- This will give the user access to the database - the script will create a username and password for the user
- Main App:
  - Run the app in `ensf614/src/app/MovieApp`
  - This will load data into the database as well as begin the ticket registration app
  - Note if you run this script again, you will see errors in the console for conflicts with the database, the app will still run but no new data will populate
  
- Admin App:
    - Run the app in `ensf614/src/app/AdminApp`
    - This will allow a user to create new movies in the database as well as send notifications to users once these movies are created