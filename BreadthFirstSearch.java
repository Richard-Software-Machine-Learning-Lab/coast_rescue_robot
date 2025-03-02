import java.util.ArrayList;
import java.util.LinkedList;

public class BreadthFirstSearch extends UninformedSearch {
   private int numberOfNodesVisited;

	@Override
	public Node treeSearch(Problem problem, LinkedList<Node> frontier) {
		ArrayList<Node> explored = new ArrayList<>();
		frontier.add(makeNode(null, new State(problem.getStart())));
		while(!frontier.isEmpty()) {
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

	@Override
	public int getNumberOfNodesVisited() {
		return numberOfNodesVisited;
	}
}
