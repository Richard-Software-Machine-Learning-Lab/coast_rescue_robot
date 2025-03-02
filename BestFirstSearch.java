import java.util.ArrayList;
import java.util.PriorityQueue;

public class BestFirstSearch extends InformedSearch{

	@Override
	public ArrayList<Node> expand(Node theNode, Problem problem, PriorityQueue<Node> frontier,
			ArrayList<Node> explored) {
		ArrayList<State> nextStates = successor(theNode.getState(), problem);
		ArrayList<Node> successors = new ArrayList<>();
		Node newNode;
		for (State eachState: nextStates) {
		       if (!theNodeIsInTheFrontier(frontier, eachState) && !theNodeIsInTheExplored(explored, eachState)) {
		    	   newNode = makeNode(theNode, eachState, problem);
		    	   successors.add(newNode);
		       }	
		}
		return successors;
	}
}
