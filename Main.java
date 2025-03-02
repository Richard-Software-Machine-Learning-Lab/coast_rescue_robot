import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) {
		Configuration configuration = Configuration.valueOf(args[1]);
		Problem theProblem = new Problem (configuration.getMap().getMap(), configuration.getPerson(), configuration.getSafety(), args[0]);
	    runSearch(theProblem);
	}

	private static void runSearch(Problem theProblem) {
		 LinkedList<Node> uninformedFrontier;
		 PriorityQueue<Node> informedFrontier;
         if (theProblem.getTypeOfSearch().equals("BFS")) {

        	    uninformedFrontier = new LinkedList<>();
		        BreadthFirstSearch bestFirstSearch = new BreadthFirstSearch();
		        Node goalNode = bestFirstSearch.treeSearch(theProblem, uninformedFrontier); 
		        bestFirstSearch.printOutputWithPathTheCostAndTheNumberOfNodesVisited(goalNode);
		        
         } else if (theProblem.getTypeOfSearch().equals("DFS")) {

        	    uninformedFrontier = new LinkedList<>();
		        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
		        Node goalNode = depthFirstSearch.treeSearch(theProblem, uninformedFrontier); 
		        depthFirstSearch.printOutputWithPathTheCostAndTheNumberOfNodesVisited(goalNode);
		        
         } else if (theProblem.getTypeOfSearch().equals("BestF")) {
        	 
        	    informedFrontier = new PriorityQueue<>();
        	    BestFirstSearch bestFirstSearch = new BestFirstSearch();
                Node goalNode = bestFirstSearch.treeSearch(theProblem, informedFrontier);
                bestFirstSearch.printOutputWithPathTheCostAndTheNumberOfNodesVisited(goalNode);
                
         } else if (theProblem.getTypeOfSearch().equals("AStar")) {
        	 
        	    informedFrontier = new PriorityQueue<>();
        	    AStar aStar = new AStar();
                Node goalNode = aStar.treeSearch(theProblem, informedFrontier);
                aStar.printOutputWithPathTheCostAndTheNumberOfNodesVisited(goalNode);  
                
         }	
	}

}
