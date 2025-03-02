public class Problem {
      private int[][] map;
      private Coordinate start;
      private Coordinate goal;
      private int[] obstacles;
      private String typeOfSearch;
      
      public Problem (int[][] map, Coordinate start, Coordinate goal, String typeOfSearch) {
    	  this.map = map;
    	  this.start = start;
    	  this.goal = goal;
    	  obstacles = new int[]{1,2};
    	  this.typeOfSearch = typeOfSearch;
      }
      
      public int[][] getMap() {
    	  return this.map;
      }
      
      public Coordinate getStart() {
    	  return this.start;
      }
      
      public Coordinate getGoal() {
    	  return this.goal;
      }
      
      public int[] getTheObstacles() {
    	  return this.obstacles;
      }
      
      public String getTypeOfSearch() {
    	  return typeOfSearch;
      }
}
