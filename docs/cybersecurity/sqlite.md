# SQL General Commands
```{warning}
!!! Warning: be careful of spelling !!!
```

## filtering data:
```bash
WHERE <colomn_header> BETWEEN <data_a> AND <data_b>;
```
e. g. MySQL>SELECT*
	>FROM machines
	>WHERE `os_patch_date` BETWEEN `'2023-03-02'` AND `'2024-01-03'`;

e.g.  >WHERE `index_number` BETWEEN `5` AND `8`;

```{tip}
the 'BETWEEN' command will include the data on both larger end and lower end  
<data_a> must be lower than <data_b> in the example above

```

## filtering data using AND, OR and NOT operators:

e.g. mysql >SELECT *
	->FROM machines
	->WHERE os_patch_date <= '2023-02-03' `AND` os_model = 'kali' `OR` os_model = 'parrot';

e.g. mysql >SELECT *
	->FROM machines
	->WHERE `NOT` os_model = 'kali' `OR NOT` os_model = 'parrot';
```{note}
this will return machines with os that is neither kali nor parrot!
```

## filter data when the name of the target is changing:

e.g. mysql >SELECT *
	->FROM machines
	->WHERE os_model `LIKE 'WIN%'`;
In this way, target machines such as "WIN 8", "WIN 9", and "WIN 11" will all be targeted.

```{warning}
make sure the specificy the title of the targets everytime a filtering operator is used.
```
e.g. ->WHERE country = 'China' OR 'Japan' is not correct,
	it should be replaced with ->WHERE country = 'China' OR <span style="color: red;">country = </span> 'Japan';



## Joining Tables:
There are two way of joining tables in mysql, inner and outer join.
Inner join combines two tables based on the common row entries that they've got.
There are 3 different kinds of outer join: left join, right join and full outer join.
left join preserves all the left table will combin the row entrys on the right only if they've got common terms.
For right join, vice versa as for full outer join, it is just brute force joining both tables.

syntaxes:
mysql>SELECT *
	->FROM machines
	->`LEFT JOIN` generators ON generators.usr = machines.usr;

mysql>SELECT *
	->FROM machines
	->`FULL OUTER JOIN` generators ON generators.usr = machines.usr;

mysql>SELECT *
	->FROM machines
	->`INNER JOIN` generators ON generators.usr = machines.usr;



