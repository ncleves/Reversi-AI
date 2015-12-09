public class Board{
	
	char [][]board = new char[8][8];
	int scoreX = 0;
	int scoreO = 0;
	char whoseMove = 'X';
	int noMovesLeft = 0;
	
	
	/**
	 * @return the noMovesLeft
	 */
	public int getNoMovesLeft() {
		return noMovesLeft;
	}

	/**
	 * @param noMovesLeft the noMovesLeft to set
	 */
	public void setNoMovesLeft(int noMovesLeft) {
		this.noMovesLeft = noMovesLeft;
	}

	/**
	 * @return the board
	 */
	public char[][] getBoard() {
		return board;
	}
	
	public char getBoardCoor(int row, int col){
		return board[row][col];
	}
	
	/**
	 * @param board the board to set
	 */
	public void setBoard(char[][] board) {
		this.board = board;
	}
	
	public void setBoardCoor(int row, int col, char c){
		this.board[row][col] = c;
	}
	
	public int getScoreForX(){
		return scoreX;
	}
	
	public void setScoreForX(int val){
		this.scoreX = val;
	}
	
	public int getScoreForO(){
		return scoreO;
	}
	
	public void setScoreForO(int val){
		this.scoreO = val;
	}

	public char getWhoseMove() {
		return whoseMove;
	}

	public void setWhoseMove(char whoseMove) {
		this.whoseMove = whoseMove;
	}

}
