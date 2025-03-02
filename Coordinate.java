public class Coordinate {
	private int row;
	private int column;

	public Coordinate(int row, int column) {
		this.row =row;
		this.column =column;
	}

	public String toString() {
		return "("+ row +","+ column +")";
	}

	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}

	@Override
	public boolean equals(Object o) {

		Coordinate coordinate =(Coordinate) o;
		if(coordinate.row == row && coordinate.column == column) {
			return true;
		}
		return false; 

	}

}
