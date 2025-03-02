## Tech stack
* Java 11

## Running the application
```shell script
   javac src/*.java
   java Main <DFS|BFS|AStar|BestF|...> <ConfID>
```
## Examples
```shell script
   java Main BFS TCONF00
   java Main DFS TCONF00
   java Main BestF TCONF00
   java Main AStar TCONF00
```

##Features
## Grid Representation
   * 2D Rhombus Grid: The environment is represented as a 2D rhombus grid with coordinates (row, column) for robot positioning and obstacle placement.
   * Customizable Grid Size: Allows for easy configuration of the grid size and specific obstacle locations.
## Robot Movement & Navigation
   * Movement Between Adjacent Coordinates: The robot moves to adjacent coordinates (up, down, left, right) within the grid.
   * Valid Movement Function: Ensures robot movement respects grid boundaries and obstacles to prevent invalid moves.
## Obstacle Detection
   * Obstacle Recognition: The robot uses sensors to detect obstacles in its path and avoids invalid or blocked positions.
   * Boundary Detection: The robot can detect when it is near or at grid boundaries and prevents out-of-bound movements.
## Pathfinding Algorithms
   * Breadth-First Search (BFS): Implements the BFS algorithm to explore the environment in layers, ensuring the shortest path is found in unweighted graphs.
   * Depth-First Search (DFS): Implements the DFS algorithm to explore the environment by diving deep into each branch before backtracking.
   * Best-First Search (BestF): A heuristic-based search algorithm that selects the path based on the cost function, focusing on the most promising paths first.
   * A Search*: Implements the A* algorithm, combining the advantages of BFS and Best-First Search by using both the actual cost and heuristic estimates to find the most optimal path. 
## State Space Representation
   * State Representation Using Coordinates: The robot's position is represented as coordinates (row, column) in the 2D grid.
   * Initial and Goal States: The robot starts at a predefined position (P) and aims to reach the goal position (S). 
## Sensors and Recognition
   * Obstacle and Boundary Sensors: The robot has sensors to detect obstacles and boundaries, ensuring safe movement within the environment.
   * Real-Time Sensor Feedback: Continuously updates robot movements based on dynamic obstacle and boundary detection. 
## Goal State Achievement
   * Goal State Detection: The robot detects when it reaches the goal position (S), completing the task.
   * Task Completion: Once the robot reaches its goal, the task is considered complete (e.g., assisting the person in an emergency).
## Movement Simulation
   * Real-Time Movement Updates: The robot’s movements are simulated and updated in real-time as it progresses from its starting position to the goal.
   * Interactive Search Loop: Continuously checks the current state and uses the chosen search algorithm to find the optimal path to the goal.
## Error Handling and Edge Case Handling
   * Boundary and Obstacle Handling: The software handles edge cases where the robot cannot move due to obstacles or boundaries, ensuring that the robot only moves in valid spaces.
   * Movement Restrictions: Prevents the robot from making invalid moves, including moving into obstacles or outside of the grid.
## Configurability
   * Map Configuration: Customize the layout of obstacles, the robot’s start position, and the goal position.
   * Flexible Initialization: Easily modify the robot’s initial position, goal state, and obstacles within the grid.