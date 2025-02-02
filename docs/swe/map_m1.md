# ECE297-M1 Mapper Functions
An example project of using C++ to make a Mapper application, which shows street segments and location info.

## Walkflow
### go to m1.h and see all the functions that need to be implemented  
open Mate Terminal, command `cd /cad2/ece297s/public/include/milestones` and see m1.h.  
assign the functions, the functions at the end are more likely to be harder.  

### Implement the functions
open a new terminal and command `cd ece297/work/mapper` or whatever the path to the m1.cpp file is.  

### Making and Testing
go to the location that contains MakeFile, and run the command `make` + `make clean`.
to test runn the command `ece297exercise 1`.  
```bash
#workflow
make clean
make CONF=release

ece297exercise 1

ece297exercise 1 --list_testers

#public_M1_perf_OSM_Test is one of the teseters listed
ece297exercise 1 --debug_test Public_M1_Perf_OSM_Tests

make test
```
below are the public testers for m1.h
```bash
	 Public_M1_Func_Intersection_Tests
	 Public_M1_Func_Street_Tests
	 Public_M1_Func_Distance-Time_Tests
	 Public_M1_Func_Spatial_Tests
	 Public_M1_Func_OSM_Tests

	 Public_M1_Perf_Intersection_Tests
	 Public_M1_Perf_Street_Tests
	 Public_M1_Perf_Distance-Time_Tests
	 Public_M1_Perf_OSM_Tests

	 Public_M1_Load_Maps
	 Public_M1_Valgrind

ece297exercise 1 --debug_test Public_M1_Load_Maps
make test

ece297exercise 1 --debug_test Public_M1_Func_Intersection_Tests
make test

ece297exercise 1 --debug_test Public_M1_Func_Intersection_Tests
make test

# Street Tests
ece297exercise 1 --debug_test Public_M1_Func_Street_Tests
make test

ece297exercise 1 --debug_test Public_M1_Perf_Street_Tests
make test

# Spatial Tests
ece297exercise 1 --debug_test Public_M1_Func_Spatial_Tests
make test

# Distance Time Tests
ece297exercise 1 --debug_test Public_M1_Func_Distance-Time_Tests
make test

ece297exercise 1 --debug_test Public_M1_Perf_Distance-Time_Tests
make test
```
