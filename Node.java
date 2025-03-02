public class Node  implements Comparable{
     private State state;
     private Node parentNode;
     private double pathCost;
     private int depth;
     public double fCost;
     
     public State getState() {
    	 return this.state;
     }
     
     public int getDepth() {
    	 return this.depth;
     }
     
     public Node getParentNode() {
    	 return this.parentNode;
     }
     
     public double getPathCost() {
    	 return this.pathCost;
     }

     public void setState(State state) {
    	 this.state = state;
     }
     
     public void setDepth(int depth) {
    	 this.depth = depth;
     }
     
     public void setParentNode(Node parentNode) {
    	 this.parentNode = parentNode;
     }
     
     public void setPathCost(double pathCost) {
    	 this.pathCost = pathCost;
     }
     public void setFCost(double fCost) {
    	 this.fCost = fCost;
     }
     
     public double getFCost() {
    	 return fCost;
     }

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Node node = (Node) o;
	 	 if (this.getFCost() > node.getFCost()) {
	   		 return 1;
	   	 }
	   	 else if (this.getFCost() < node.getFCost()) {
	   		 return -1;
	   	 }
	   	 return 0;
	}
}
