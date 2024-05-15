# Circuits Basics

## Nodal and Mesh Analysis

### Nodal Analysis
Nodal voltage = node voltage = potential  

#### Step 1: select a reference (ground) node if
not already given and identify the rest of
the nodes (e.g. V1, V2...)

#### Step 2: write a KCL equation for each node
except the ground node and the trivial
node (a node joining only two elements).

#### Step 3: solve the set of equations to find the node voltages (e.g. V1, V2...).

#### Step 4: use node voltages (e.g. V1, V2...) to find other voltages/currents in the circuit.

### Loop Analysis
Assume planar circuit (i.e. no element
crosses another element)

#### Step 1: assign mesh current for each mesh (e.g. i1, i2...)
#### Step 2: write a KVL equation for each mesh.
#### Step 3: solve the set of equations to find the mesh currents (e.g. i1, i2...).
#### Step 4: use mesh currents (e.g. i1, i2...) to find other voltages/currents in the circuit.




## Source Transformation

### What is this transformation about
Current Source and resistors in parallel
convert into a resistor and a voltage source in series
and vice versa

### When can the transformation be used?

It can be used anytime, the source transformation is only
a technique to simplify the circuit.

  It is absolutely plausible to simplify the circuit first using the source transformation and then  
  finish it using super-position or mesh analysis.

### What to look out for when using source transformation?

The number one mistake that i make when doing source transformations is  
when adding current sources/ or voltages sources.

  When adding current sources make sure to double check if they are actually in parallel.
  When adding up voltage sources, make sure they are in series.
  In addition, do not forget to check the signs when adding up, the direction of the current source/ voltage source will determine their signs.

## Superposition

### What is super-position?
Superposition is a technique used to simplify complecated circuits using one-thing-at-a-time technique.
When there are multiple voltage sources and current sources present in the circuit, it might be hard to find the voltage/current at a point.
  
When analysing a circuit using super-position, switch all current sources to open circuit and all voltage sources to closed circuit execpt one. Then it will become much easier to find the current/voltage at a point.
Use this method recursively to find the current/voltage at the point on each situation.
  
Finally add up the values for the current/voltage at the point to get its value.

### What to look out for?
Be very careful with the signs when adding up the results for the senerios.

## Thevenin's and Norton's Law

## Maximum Power Transfer

## First Order Circuits

## AC Circuits

## Rectangular and Polar Coordinates

## Exam Essentials