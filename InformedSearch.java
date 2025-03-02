import java.util.ArrayList;
import java.util.PriorityQueue;


public abstract class InformedSearch {
	private int numberOfNodesVisited;

	public Node makeNode(Node parentNode, State stateNewNode, Problem problem) {
		Node node = new Node();
		
		node.setState(stateNewNode);	
		try {
			node.setParentNode(parentNode);
		} catch (NullPointerException e) {
			node.setParentNode(null);
		}
		
		try {
			node.setPathCost(parentNode.getPathCost() + 1);
		} catch (NullPointerException e) {
			node.setPathCost(0);
		}
		
		try {
			double hCost = estimationCostFromTheStateNodeToTheGoal(node.getState().getCoordinates(), problem.getGoal());
			double fCost;
			if (problem.getTypeOfSearch().equals("AStar")) {
				double pathCost = node.getPathCost();
				fCost = hCost + pathCost;
				node.setFCost(fCost);
			} else {
				fCost = hCost;
				node.setFCost(fCost);
			}
		} catch (NullPointerException e) {
			node.setFCost(0);
		}
		
		try {
			node.setDepth(parentNode.getDepth() + 1);
		} catch (NullPointerException e) {
			node.setDepth(0);
		}
		
		return node;
	}

	public double estimationCostFromTheStateNodeToTheGoal(Coordinate node, Coordinate goal) {
		double estimationH = 0;
		double rowNode = -node.getRow();
		double rowGoal = -goal.getRow();
		double columDifference = node.getColumn() - goal.getColumn();
		double rowDifference = rowNode - rowGoal;
		if ((columDifference * rowDifference) >= 0) {
			estimationH = Math.abs(rowDifference) + Math.abs(columDifference);
		} else {
			columDifference = Math.abs(columDifference);
			rowDifference = Math.abs(rowDifference);
			estimationH = Math.max(columDifference, rowDifference);
		}
		return estimationH;
	}

	public Node treeSearch(Problem problem, PriorityQueue<Node> frontier) {
		ArrayList<Node> explored = new ArrayList<>();
		frontier.add(makeNode(null, new State(problem.getStart()), problem));
		
		while (!frontier.isEmpty()) {
			printFrontierEachStep(frontier);
			Node nodeND = removeFromTheFrontier(frontier);
			explored.add(nodeND);
			numberOfNodesVisited = explored.size();
			if (goalTest(nodeND.getState(), problem.getGoal())) {
				return nodeND;
			}
			else {
				frontier.addAll(expand(nodeND, problem, frontier, explored));
			}
		}
		
		return null;
	}

	public void printFrontierEachStep(PriorityQueue<Node> frontier) {
		int counterSizeFrontier = 0;
		System.out.print("[");
		for (Node node: frontier) {
			if (counterSizeFrontier < frontier.size() - 1) {
				System.out.print(node.getState().getCoordinates().toString()+":"+node.getFCost()+", ");
			} else {
				System.out.print(node.getState().getCoordinates().toString()+":"+node.getFCost());
			}
			counterSizeFrontier = counterSizeFrontier + 1;
		}
		System.out.print("]");
		System.out.println();
	}

	public boolean goalTest(State currentState, Coordinate goalEndCoordinate) {
		if (currentState.getCoordinates().equals(goalEndCoordinate)) {
			return true;
		}
		return false;
	}

	public Node removeFromTheFrontier(PriorityQueue<Node> frontier) {
		return frontier.poll();
	}

	abstract public ArrayList<Node> expand(Node theNode, Problem problem, PriorityQueue<Node> frontier, ArrayList<Node> explored);

	public boolean theNodeIsInTheFrontier(PriorityQueue<Node> frontier, State state) {
		for (Node eachNode: frontier) {
			 if (eachNode.getState().getCoordinates().equals(state.getCoordinates())) {
				 return true;
			 }
		}
		return false;
	}

	public boolean theNodeIsInTheExplored(ArrayList<Node> explored, State state) {
		for (Node eachNode: explored) {
			 if (eachNode.getState().getCoordinates().equals(state.getCoordinates())) {
				 return true;
			 }
		}
		return false;
	}

	public ArrayList<State> successor(State state, Problem problem) {
		ArrayList<State> nextStates = new ArrayList<>();
		int row = state.getCoordinates().getRow();
		int column = state.getCoordinates().getColumn();
		int[] obstacles = problem.getTheObstacles();
		int obstacleOne = obstacles[0];
		int obstacleTwo = obstacles[1];
		int stateValue = problem.getMap()[row][column];
		int [][] map = problem.getMap(); 
		int[] rowState = new int[] {row + 1, row, row - 1, row - 1, row, row + 1};
		int[] columnState = new int[] {column + 1, column + 1, column, column - 1, column - 1, column};
		
		if (stateValue == 0) {
			 if (checkInsideBoundaries(rowState[0], columnState[0], problem) && (differentToASpecificNumber(map[rowState[0]][columnState[0]], obstacleOne)) 
					 && (differentToASpecificNumber(map[rowState[0]][columnState[0]], obstacleTwo))) {
				 nextStates.add(new State(new Coordinate(rowState[0], columnState[0])));
			 }
			 if (checkInsideBoundaries(rowState[1], columnState[1], problem) && (differentToASpecificNumber(map[rowState[1]][columnState[1]], obstacleOne))) {
				 nextStates.add(new State(new Coordinate(rowState[1], columnState[1])));
			 }
			 if (checkInsideBoundaries(rowState[2], columnState[2], problem) && (differentToASpecificNumber(map[rowState[2]][columnState[2]], obstacleOne)) 
					 && (differentToASpecificNumber(map[rowState[2]][columnState[2]], obstacleTwo))) {
				 nextStates.add(new State(new Coordinate(rowState[2], columnState[2])));
			 }
			 if (checkInsideBoundaries(rowState[3], columnState[3], problem) && (differentToASpecificNumber(map[rowState[3]][columnState[3]], obstacleOne)) 
					 && (differentToASpecificNumber(map[rowState[3]][columnState[3]], obstacleTwo))) {
				 nextStates.add(new State(new Coordinate(rowState[3], columnState[3])));
			 }
			 if (checkInsideBoundaries(rowState[4], columnState[4], problem) && (differentToASpecificNumber(map[rowState[4]][columnState[4]], obstacleOne))) {
				 nextStates.add(new State(new Coordinate(rowState[4], columnState[4])));
			 }
			 if (checkInsideBoundaries(rowState[5], columnState[5], problem) && (differentToASpecificNumber(map[rowState[5]][columnState[5]], obstacleOne)) 
					 && (differentToASpecificNumber(map[rowState[5]][columnState[5]], obstacleTwo))) {
				 nextStates.add(new State(new Coordinate(rowState[5], columnState[5])));
			 }
		}
		else if (stateValue == 2) {
			 if (checkInsideBoundaries(rowState[1], columnState[1], problem) && (differentToASpecificNumber(map[rowState[1]][columnState[1]], obstacleOne))) {
				 nextStates.add(new State(new Coordinate(rowState[1], columnState[1])));
			 }
			 if (checkInsideBoundaries(rowState[4], columnState[4], problem) && (differentToASpecificNumber(map[rowState[4]][columnState[4]], obstacleOne))) {
				 nextStates.add(new State(new Coordinate(rowState[4], columnState[4])));
			 }	 
		}
		else {
			throw new IllegalArgumentException("The state value should not have this value");
		}
		
		return nextStates;
	}

	public boolean checkInsideBoundaries(int coordinateRow, int coordinateColumn, Problem problem) {
		int rowLenght = problem.getMap().length;
		int columnLength = problem.getMap()[0].length;
		
		if (coordinateRow >= 0 && coordinateRow < rowLenght && coordinateColumn >=0 && coordinateColumn < columnLength) {
			return true;
		} else {
			return false;
		}
	}

	public boolean differentToASpecificNumber (int numberToTest, int numberObstacle) {
		if (numberToTest != numberObstacle) {
			return true;
		}
		return false;
	}

	public ArrayList<String> pathCompleteSolution(Node theGoalNode) {
		ArrayList<String> pathSolution = new ArrayList<>();
		pathSolution.add(theGoalNode.getState().getCoordinates().toString());
		Node node = theGoalNode;
		while (!(node.getParentNode() == null)) {
			pathSolution.add(0, node.getParentNode().getState().getCoordinates().toString());
			node = node.getParentNode();
		}
		return pathSolution;
	}

	public void printSolutionPath(Node goalNode) {
    	ArrayList<String> solutionPath = pathCompleteSolution(goalNode);
    	for(String eachCoordinate: solutionPath) {
    		System.out.print(eachCoordinate);
    	}
    	System.out.println();
    }

	public double pathCostFinding(Node goalNode) {
		double pathCost = 0.0;
		Node node = goalNode;
		while (!(node.getParentNode() == null)) {
			pathCost = pathCost + 1;
			node = node.getParentNode();
		}
		return pathCost;
	}

	public int getNumberOfNodesVisited1() {
		return numberOfNodesVisited;
	}

	public void printOutputWithPathTheCostAndTheNumberOfNodesVisited (Node goalNode) {
		if (goalNode == null) {
			System.out.println("fail");
			System.out.println(getNumberOfNodesVisited1());
		} else {
			printSolutionPath(goalNode);
			System.out.println(goalNode.getPathCost());
			System.out.println(getNumberOfNodesVisited1());
		}
	}
}
