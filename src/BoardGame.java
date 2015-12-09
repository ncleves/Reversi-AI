import java.util.ArrayList;

// Reversi, BoardGame Interface 2/23/15

public interface BoardGame {

	/**
	 * makes move specified by row and col 
	 */ 
	public void makeMove(int row, int col);
	
	/**
	 *  undoes the move specified by row and col. 
	 */
	public void undoMove(); 

	/**
	 * @return a 2-d character array representation of the board
	 */ 
    //public char[][] getBoard();
    
	/**
	 * 
	 *
	 * @return true if the game is over, false otherwise 
	 *
	 */
	public boolean gameOver();  

	/**
	 * 
	 * @return true if the game is draw, false otherwise 
	 *
	 */ 
    public boolean isDraw();

	/**
	 * @return character indicating the winner
	 */ 
	public char getWinner();

    /**
     * @return
     */
    public double getBoardValue();
    
    public boolean legalMove(int row, int col);
    
    public ArrayList<Position> checkMove(int row, int col);
    
}
