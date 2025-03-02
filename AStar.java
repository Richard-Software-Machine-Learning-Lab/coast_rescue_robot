import java.util.ArrayList;
import java.util.PriorityQueue;


public class AStar extends InformedSearch{

	@Override
	public ArrayList<Node> expand(Node theNode, Problem problem, PriorityQueue<Node> frontier, ArrayList<Node> explored) {
		ArrayList<State> nextStates = successor(theNode.getState(), problem);
		ArrayList<Node> successors = new ArrayList<>();
		Node newNode;
		for (State eachState: nextStates) {
			 newNode = makeNode(theNode, eachState, problem);
		       if (!theNodeIsInTheFrontier(frontier, eachState) && !theNodeIsInTheExplored(explored, eachState)) {
		    	   successors.add(newNode);
		       }
		       else if (theNodeIsInTheFrontier(frontier, eachState)) {
		    	   for (Node oldNode: frontier) {
		    		    if (newNode.getState().getCoordinates().equals(oldNode.getState().getCoordinates())) {
		    		    	 if ((newNode.getPathCost() < oldNode.getPathCost())) {
				    		    	frontier.remove(oldNode);
				    		    	frontier.add(newNode);
				    		    	break;
				    		}
		    		    }
		    	   }
		       }
		}
		return successors;
	}
}
