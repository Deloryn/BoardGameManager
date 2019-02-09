# Prerequisites

`sudo apt-get install postgresql`

# Creating the database

`sudo -u postgres -i`

`psql --command="ALTER USER postgres PASSWORD '12345';"`

`createdb boardgamemanagerdb --host=localhost --port=5432 --username=postgres` (password is 12345)

# Removing the database

`sudo -u postgres -i`

`dropdb boardgamemanagerdb`