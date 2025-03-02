import java.util.ArrayList;
import java.util.LinkedList;

public abstract class UninformedSearch {

	public Node makeNode(Node parentNode, State stateNewNode) {
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
			node.setDepth(parentNode.getDepth() + 1);
		} catch (NullPointerException e) {
			node.setDepth(0);
		}
		return node;
	}

	abstract public Node treeSearch(Problem problem, LinkedList<Node> frontier);
	
	
	public void printFrontierEachStep(LinkedList<Node> frontier) {
		System.out.print("[");
		for (int position = 0; position < frontier.size() - 1; position ++) {
			System.out.print(frontier.get(position).getState().getCoordinates().toString()+", ");
		}
		System.out.print(frontier.get(frontier.size() - 1).getState().getCoordinates().toString()+"]");
		System.out.println();
	}

	public boolean goalTest(State currentState, Coordinate goalEndCoordinate) {
		if (currentState.getCoordinates().equals(goalEndCoordinate)) {
			return true;
		}
		return false;
	}

	public Node removeFromTheFrontier(LinkedList<Node> frontier) {
		return frontier.removeFirst();
	}

	public ArrayList<Node> expand(Node theNode, Problem problem, LinkedList<Node> frontier, ArrayList<Node> explored){
		ArrayList<State> nextStates = successor(theNode.getState(), problem);
		ArrayList<Node> successors = new ArrayList<>();
		Node newNode;
		for (State eachState: nextStates) {
		       if (!theNodeIsInTheFrontier(frontier, eachState) && !theNodeIsInTheExplored(explored, eachState)) {
		    	   newNode = makeNode(theNode, eachState);
		    	   successors.add(newNode);
		       }	
		}
		return successors;
	}

	public boolean theNodeIsInTheFrontier(LinkedList<Node> frontier, State state) {
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

	abstract public int getNumberOfNodesVisited();

	public void printOutputWithPathTheCostAndTheNumberOfNodesVisited (Node goalNode) {
		if (goalNode == null) {
			System.out.println("fail");
			System.out.println(getNumberOfNodesVisited());
		} else {
			printSolutionPath(goalNode);
			System.out.println(goalNode.getPathCost());
			System.out.println(getNumberOfNodesVisited());
		}
	}
}
