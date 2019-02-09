# Prerequisites

`sudo apt-get install postgresql`

# Creating the database
This is probably not needed, because Spring Boot cares about everything. But I place it just in case.

`sudo -u postgres -i`

`psql --command="ALTER USER postgres PASSWORD '12345';"`

`createdb boardgamemanagerdb --host=localhost --port=5432 --username=postgres` (password is 12345)

# Removing the database
This is probably not needed, because Spring Boot cares about everything. But I place it just in case.


`sudo -u postgres -i`

`dropdb boardgamemanagerdb`