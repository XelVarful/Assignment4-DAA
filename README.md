# DAA Assignment 4 — Graph Algorithms
## About the Project

This project was created as part of DAA (Design and Analysis of Algorithms) course — Assignment 4.
The main goal is to implement and analyze graph algorithms using Java and Maven.

It includes:

Finding Strongly Connected Components (SCC) using Tarjan’s Algorithm

Building a Condensation Graph (DAG) from SCCs

Performing Topological Sorting using Kahn’s Algorithm

Computing Shortest and Longest (Critical) paths in a DAG

A Graph Generator for random datasets

Performance metrics (timing and operation counts)

JUnit 5 tests for validation

### Technologies

Java 17

Maven

JUnit 5

Gson (JSON parsing)

### How to Run
Build the project
```
mvn clean install
```
Run the demo
```
mvn exec:java -Dexec.mainClass=graph.Main
```

To run with a specific dataset:
```
mvn exec:java -Dexec.mainClass=graph.Main -Dexec.args="data/medium1.json"
```
Run tests
```
mvn test
``` 
### Project Structure
```
DAA-Assignment4/
├── pom.xml
├── README.md
├── data/
│   └── small1.json
└── src/
    ├── main/java/graph/
    │   ├── Graph.java
    │   ├── Edge.java
    │   ├── TarjanSCC.java
    │   ├── CondensationGraph.java
    │   ├── TopologicalSort.java
    │   ├── DAGPaths.java
    │   ├── Metrics.java
    │   ├── GraphGenerator.java
    │   ├── GraphLoader.java
    │   └── Main.java
    └── test/java/graph/
        ├── TarjanSCCTest.java
        ├── TopologicalSortTest.java
        ├── DAGPathsTest.java
        └── GraphGeneratorTest.java
```
### Input Data Format (JSON)

Example file: data/small1.json
```
{
  "directed": true,
  "n": 6,
  "edges": [
    {"u": 0, "v": 1, "w": 3},
    {"u": 1, "v": 2, "w": 2},
    {"u": 2, "v": 0, "w": 1},
    {"u": 3, "v": 4, "w": 2},
    {"u": 4, "v": 5, "w": 5},
    {"u": 5, "v": 3, "w": 1},
    {"u": 2, "v": 3, "w": 4}
  ]
}
```

Explanation:

"n" → number of vertices

"edges" → list of directed edges with weights

"u", "v", "w" → from, to, weight

### Example Output
```
Loading graph from: data/small1.json
Graph loaded: 6 nodes
SCC count: 2
SCCs: [[2,1,0],[5,4,3]]
Condensed DAG size: 2
Topological Order on condensed DAG: [0,1]
Shortest distances from 0: {0=0,1=4}
Longest distances from 0: {0=0,1=6}
```
### What happens step-by-step:
Step	Description
1️	Load graph from JSON
2️	Find SCCs using Tarjan’s algorithm
3️	Build condensation DAG
4️	Apply topological sort
5️	Compute shortest and longest paths
### Example Datasets
Dataset	Nodes	Edges	Type	Description
small1.json	6	7	Cyclic	2 SCCs + simple DAG
medium1.json	15	25	Mixed	Several SCCs
large1.json	40	100	Dense	Stress test
### Example Metrics (Performance)
Dataset	Vertices	Edges	SCC Time (ms)	Topo Time (ms)	Path Time (ms)
small1.json	6	7	0.45	0.12	0.15
medium1.json	15	25	1.20	0.35	0.40
large1.json	40	100	3.80	0.95	1.10

These metrics were collected using Metrics.java (System.nanoTime-based).
Results may vary slightly per system.

### Testing

Each major algorithm has its own test:

TarjanSCCTest → checks SCC correctness

TopologicalSortTest → verifies topological order

DAGPathsTest → verifies shortest & longest paths

GraphGeneratorTest → checks random graph creation

Run:
```
mvn test
```

#### Future Improvements

Integrate Metrics directly inside TarjanSCC and DAGPaths

Export all results automatically to CSV

Add a visualizer for SCCs and DAG (optional GUI)

### Summary

This project combines analysis and implementation of several classical graph algorithms in a single Maven-based Java project.
It’s lightweight, clean, and easy to extend for deeper performance or complexity analysis.
