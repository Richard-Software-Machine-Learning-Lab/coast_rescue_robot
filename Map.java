public enum Map{
	TESTMAP00(new int [][] {
		     {0,0,0,0,0,0},
		    {0,0,2,0,0,0},
		   {0,0,2,0,0,0},
		  {0,1,1,1,1,0},
		 {0,0,0,0,0,0},
		{0,0,0,1,0,0}
	}),

	TESTMAP01(new int [][] {
		   {0,0,0,0},
		  {0,0,0,0},
		 {0,0,0,0},
		{0,0,0,0},
	}),

	MAP0(new int [][] {
		     {0,0,0,0,0,0},
		    {0,0,0,0,0,0},
		   {0,0,0,0,0,0},
		  {0,0,0,0,0,0},
		 {0,0,0,0,0,0},
		{0,0,0,0,0,0}
	}),

	MAP1(new int [][] {
		    {0,0,0,0,0},
		   {0,0,1,0,0},
		  {0,0,2,0,0},
		 {0,0,1,0,0},
		{0,0,0,0,0},
	}),

	MAP2(new int [][] {
		     {0,0,0,0,0,0},
		    {0,1,1,1,0,0},
		   {0,1,0,1,0,0},
		  {0,1,1,1,0,0},
		 {0,0,0,0,0,0},
		{0,0,2,2,2,0}
	}),

	MAP3(new int [][] {
		     {0,0,0,0,0,0},
		    {0,2,0,0,1,0},
		   {0,0,1,1,1,0},
		  {0,0,0,0,1,0},
		 {0,0,0,0,1,0},
		{0,0,0,0,1,0}
	}),

	MAP4(new int [][] {
		     {0,0,0,0,0,0,0},
		    {0,1,1,1,1,1,1},
		   {0,0,0,0,0,0,0},
		  {0,0,0,0,1,1,0},
		 {0,0,0,0,0,1,0},
		{0,2,2,2,2,2,0},
       {0,0,0,0,0,0,0}
	});

	private final int[][] map; 

	Map(int[][] map){
		this.map=map;

	}

	public int[][] getMap() {
		return map;
	}

}
