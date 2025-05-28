# Python Scripting and SQL

## Creating and Configuring a database

### When you already have a csv file
working with MySQL workbench GUI:  
MySQL Connections -> `+` -> give it a password and test the connections  
In the left `SCHEMAS` tab, right and create a new schema  
Right click on the schema and click `Table Data Import Wizard`

### When you need to append a csv to a existing table
Click on the table and click `Table Data Import Wizard`  
Do not select truncate

## Connecting with a database using python scripts
### Basic Configurations
`pip install pymsql`
```bash
import pymysql
from datetime import datetime

# 1. Connection settings
HOST     = '127.0.0.1'
PORT     = 3306
USER     = 'root'
PASSWORD = '1234'
DATABASE = 'myDB' # schema name
```

### Connections
```bash
# 2. Table and column you want to query
TABLE_NAME = 'may2025'
TIME_COLUMN = 'Date'
SDESCR_COLUMN = 'Sub-description'
VAL_COLUMN = 'Amount'
DESCR_COLUMN = 'Description'

# 3. Define your interval (YYYY-MM-DD HH:MM:SS)
START = '2025-04-01 00:00:00'
END   = '2025-05-26 23:59:59'

def fetch_between(start_ts: str, end_ts: str):
    """
    Connects to MySQL and returns all rows where TIME_COLUMN
    is BETWEEN start_ts AND end_ts.
    """
    conn = pymysql.connect(
        host=HOST,
        port=PORT,
        user=USER,
        password=PASSWORD,
        database=DATABASE,
        charset='utf8mb4',
        cursorclass=pymysql.cursors.DictCursor
    )
    try:
        with conn.cursor() as cur:
            sql = f"""
                <your sql query>
            """
            cur.execute(sql, (start_ts, end_ts))
            return cur.fetchall()
    finally:
        conn.close()

if __name__ == '__main__':
    rows = fetch_between(START, END)
    if not rows:
        print("No entries found in that interval.")
    else:
        for row in rows:
            print(row)
```

## SQL Query
