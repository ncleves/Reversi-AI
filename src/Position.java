
public class Position {
	int row;
	int col;
	char piece;
	
	public Position(int row, int col) {
		super();
		this.row = row;
		this.col = col;
		
	}
	
	public Position asPiece(char piece) {
		return new Position(this.row, this.col);
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @return the piece
	 */
	public char getPiece() {
		return piece;
	}

	/**
	 * @param piece the piece to set
	 */
	public void setPiece(char piece) {
		this.piece = piece;
	}
	
	@Override
	public String toString(){
		String result = " Row: " + (this.getRow() + 1) +" Col: " + (this.getCol() + 1) + " Piece: " + this.getPiece();
		return result;
	}
}
