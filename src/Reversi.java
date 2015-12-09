import java.util.ArrayList;
import java.util.Stack;

public class Reversi implements BoardGame {
	
	Board gameBoard = new Board();
	
	Stack<Board> stackedBoard = new Stack<Board>();
	
	static final int N = 1;
	static final int NE = 2;
	static final int E = 3;
	static final int SE = 4;
	static final int S = 5;
	static final int SW = 6;
	static final int W = 7;
	static final int NW = 8;
	
	public void moveDir(Position p, int dir){
		switch (dir) {
			case N:
				p.setRow(p.getRow() - 1);
				break;
			case NE:
				p.setRow(p.getRow() - 1);
				p.setCol(p.getCol() + 1);
				break;
			case E:
				p.setCol(p.getCol() + 1);
				break;
			case SE:
				p.setRow(p.getRow() + 1);
				p.setCol(p.getCol() + 1);
				break;	
			case S:
				p.setRow(p.getRow() + 1);
				break;
			case SW:
				p.setRow(p.getRow() + 1);
				p.setCol(p.getCol() - 1);
				break;
			case W:
				p.setCol(p.getCol() - 1);
				break;
			case NW:
				p.setRow(p.getRow() - 1);
				p.setCol(p.getCol() - 1);
				break;	
		}
	}
	

	String horz = " 12345678";

	public Reversi(){
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				gameBoard.setBoardCoor(x, y, '_');
			}
		}
	}

	public String toString(){
		String result = "";
		result += (horz);
		result += "\n";
		for(int x = 0; x < 8; x++){
			result += (x+1);
			for(int y = 0; y < 8; y++){
				result += gameBoard.getBoardCoor(x, y);	
			}
			result += "\n";
		}
		result += "Score of X: " + gameBoard.getScoreForX() + " ";
		result += "Score of O: " + gameBoard.getScoreForO();
		return result;
	}

	public void initBoard(){
		//realistic endgame
//		board[0][0] = '_';
//		board[0][1] = '_';
//		board[0][2] = 'O';
//		board[0][3] = 'X';
//		board[0][4] = 'X';
//		board[0][5] = 'O';
//		board[0][6] = 'X';
//		board[0][7] = '_';
//		
//		board[1][0] = 'X';
//		board[1][1] = 'O';
//		board[1][2] = 'X';
//		board[1][3] = 'X';
//		board[1][4] = 'O';
//		board[1][5] = 'O';
//		board[1][6] = 'O';
//		board[1][7] = '_';
//		
//		board[2][0] = 'X';
//		board[2][1] = 'O';
//		board[2][2] = 'O';
//		board[2][3] = 'O';
//		board[2][4] = 'O';
//		board[2][5] = 'O';
//		board[2][6] = 'O';
//		board[2][7] = 'X';
//		
//		board[3][0] = 'O';
//		board[3][1] = 'O';
//		board[3][2] = 'O';
//		board[3][3] = 'X';
//		board[3][4] = 'O';
//		board[3][5] = 'O';
//		board[3][6] = 'O';
//		board[3][7] = 'O';
//		
//		board[4][0] = 'X';
//		board[4][1] = 'O';
//		board[4][2] = 'O';
//		board[4][3] = 'O';
//		board[4][4] = 'X';
//		board[4][5] = 'O';
//		board[4][6] = 'O';
//		board[4][7] = 'O';
//		
//		board[5][0] = 'X';
//		board[5][1] = 'O';
//		board[5][2] = 'O';
//		board[5][3] = 'O';
//		board[5][4] = 'O';
//		board[5][5] = 'X';
//		board[5][6] = 'O';
//		board[5][7] = 'O';
//		
//		board[6][0] = 'X';
//		board[6][1] = 'X';
//		board[6][2] = 'X';
//		board[6][3] = 'X';
//		board[6][4] = 'X';
//		board[6][5] = 'X';
//		board[6][6] = 'X';
//		board[6][7] = 'O';
//		
//		board[7][0] = 'X';
//		board[7][1] = 'O';
//		board[7][2] = 'O';
//		board[7][3] = 'O';
//		board[7][4] = 'O';
//		board[7][5] = 'O';
//		board[7][6] = 'O';
//		board[7][7] = 'O';
		
		//Pass move scenario			
//		board[0][0] = 'O';
//		board[0][1] = 'O';
//		board[0][2] = 'O';
//		board[0][3] = 'O';
//		board[0][4] = 'X';
//		
//		board[1][0] = 'O';
//		board[1][1] = 'O';
//		board[1][2] = 'O';
//		board[1][3] = 'X';
//		board[1][4] = 'X';
//
//		
//		board[2][0] = 'O';
//		board[2][1] = 'O';
//		board[2][2] = 'O';
//		board[2][3] = 'X';
//		board[2][4] = 'X';
//
//		
//		board[3][0] = 'O';
//		board[3][1] = 'O';
//		board[3][2] = 'O';
//		board[3][3] = 'X';
//		board[3][4] = 'X';
//
//		board[4][0] = 'O';
//		board[4][1] = 'O';
//		board[4][2] = 'O';
//		board[4][3] = 'X';
//		board[4][4] = 'X';
//
//		
//		board[5][0] = 'O';
//		board[5][1] = 'O';
//		board[5][2] = 'X';
//		board[5][3] = 'X';
//		board[5][4] = 'X';
//
//		
//		board[6][0] = 'O';
//		board[6][1] = 'X';
//		board[6][2] = 'X';
//		board[6][3] = 'X';
//		board[6][4] = '_';
//
//		
//		board[7][0] = 'X';
//		board[7][1] = '_';
//		board[7][2] = 'X';
//		board[7][3] = 'X';
//		board[7][4] = '_';

		
		//initialization for standard game
		gameBoard.setBoardCoor(3, 3, 'O');
		gameBoard.setBoardCoor(3, 4, 'X');
		gameBoard.setBoardCoor(4, 3, 'X');
		gameBoard.setBoardCoor(4, 4, 'O');
		
		gameBoard.setScoreForX(2);
		gameBoard.setScoreForO(2);
	}
	
	public String toString2(char [][]board){
		String result = "";
		result += (horz);
		result += "\n";
		for(int x = 0; x < 8; x++){
			result += (x+1);
			for(int y = 0; y < 8; y++){
				result += gameBoard.getBoardCoor(x, y);	
			}
			result += "\n";
		}
		return result;
	}
	
	public Board copyBoard(){
		Board copiedBoard = new Board();
		for(int i = 0; i < 8; i++){
			System.arraycopy(gameBoard.getBoard()[i], 0, copiedBoard.getBoard()[i], 0, 8);
			
		}
		copiedBoard.setScoreForX(gameBoard.getScoreForX());
		copiedBoard.setScoreForO(gameBoard.getScoreForO());
		copiedBoard.setWhoseMove(gameBoard.getWhoseMove());
		copiedBoard.setNoMovesLeft(gameBoard.getNoMovesLeft());
		return copiedBoard;
	}

	public void makeMove(int row, int col) {
		
		//System.out.println(toString());

		Board stacked = copyBoard();
		stackedBoard.push(stacked);
		
		char player = gameBoard.getWhoseMove();
		
		if(gameBoard.getBoardCoor(row, col) == '_' && checkMove(row, col).size() > 0){ //checks if the specified row and column are empty and if any legal moves remain
			gameBoard.setBoardCoor(row, col, player);	//sets the place on the board to globalpiece (X by default)
			
			ArrayList<Position> toflip = checkMove(row, col);
			
			for(int i = 0; i < toflip.size(); i++){ //for the number of pieces to flip
				int x = toflip.get(i).getRow();
				int y = toflip.get(i).getCol(); //flip row&col
				gameBoard.setBoardCoor(x, y, player); //set those spaces to 'X'
			}
			
			if(player == 'X'){
				gameBoard.setScoreForX(gameBoard.getScoreForX()+toflip.size()+1); //increment space 0 in the counter array for scoring the game 0 is 'X' (+1 for the placed piece)
				gameBoard.setScoreForO(gameBoard.getScoreForO()-toflip.size()); 
			}else{
				gameBoard.setScoreForO(gameBoard.getScoreForO()+toflip.size()+1); //increment space 1 in the counter array for scoring the game 1 is 'O' (+1 for the placed piece)
				gameBoard.setScoreForX(gameBoard.getScoreForX()-toflip.size());
			}
			
			gameBoard.setWhoseMove(opponent());
			
			if(!legalMovesRemain()){
				gameBoard.setWhoseMove(player);
				//System.out.println("NO MOVES FOUND FOR: " + opponent());
			}
			
			if(!legalMovesRemain()){
				gameBoard.setNoMovesLeft(1);
				//System.out.println("NO MOVES FOUND FOR ANYONE");
				//System.out.println(toString());
			}

			//System.out.println("Legal moves remain: " + legalMovesRemain());
		}
	}
						
	public boolean legalMovesRemain(){
		boolean result = false;
		for (int r = 0; r < 8 && r >= 0; r++){
			for(int c = 0; c < 8 && c >= 0; c++){
				if(checkMove(r, c).size() != 0 && gameBoard.getBoardCoor(r, c) == '_'){
					result = true;
				}
			}
		}
		return result;
	}
	
	
	
	public void undoMove() {
		if(!stackedBoard.empty()){
			gameBoard = stackedBoard.pop();
		}
	}

	public char opponent(){
		char opponent;
		if(gameBoard.getWhoseMove() == 'X'){
			opponent = 'O';
		}else{
			opponent = 'X';
		}
		return opponent;
	}
	


	public boolean gameOver() {
		boolean result = false;
		if(gameBoard.getScoreForX() + gameBoard.getScoreForO() == 64 || gameBoard.getScoreForX() == 0 || gameBoard.getScoreForO() == 0 || gameBoard.getNoMovesLeft() == 1){
			//System.out.println("GAME OVER MAN GAME OVER!!!");
			result = true;
		}
	
		return result;
	}
	

	public char getWinner() {
		char result = 'X';
		if(gameOver() && gameBoard.getScoreForX() > gameBoard.getScoreForO()){
			result = 'X';
		}else if(gameBoard.getScoreForO() > gameBoard.getScoreForX()){
			result = 'O';
		}
		return result;
	}
	
	public boolean isDraw() {
		boolean result = false;
		if(gameBoard.getScoreForX() == gameBoard.getScoreForO() && gameBoard.getScoreForX() + gameBoard.getScoreForO() == 64){
			result = true;
		}
		return result;	
	}


	public double getBoardValue() {
		double value = 0.0;
		value = gameBoard.getScoreForX() - gameBoard.getScoreForO();
		return value;
	}
	

	public boolean onBoard(int row, int col){
		boolean result = false;
		if(row <= 7 && col <= 7 && row >=0 && col >=0){
			result = true;
		}
		return result;
	}


	public ArrayList<Position> checkMove(int row, int col){	
		ArrayList<Position> allflip = new ArrayList<Position>();
		int x = row, y = col;
		
		ArrayList<Integer> directionsToLook = new ArrayList<Integer>();
		if(x == 0 && y >= 1 && y <= 6){ //top row when x is 0
			directionsToLook.add(E);
			directionsToLook.add(SE);
			directionsToLook.add(S);
			directionsToLook.add(SW);
			directionsToLook.add(W);
		}else if(y == 0 && x == 0){ //top left corner 0,0  
			directionsToLook.add(E);
			directionsToLook.add(SE);
			directionsToLook.add(S);
		}else if(x == 0 && y == 7){ //top right corner 0,7
			directionsToLook.add(S);
			directionsToLook.add(SW);
			directionsToLook.add(W);
		}else if(y == 0 && x >= 1 && x <= 6){ //left column when y is 0
			directionsToLook.add(N);
			directionsToLook.add(NE);
			directionsToLook.add(E);
			directionsToLook.add(SE);
			directionsToLook.add(S);
		}else if(x == 7 && y == 0){ // bottom left corner 7,0
			directionsToLook.add(N);
			directionsToLook.add(NE);
			directionsToLook.add(E);
		}else if(x == 7 && y >= 1 && y <= 6){ // bottom row when x is 7
			directionsToLook.add(N);
			directionsToLook.add(NE);
			directionsToLook.add(E);
			directionsToLook.add(W);
			directionsToLook.add(NW);
		}else if(x == 7 && y == 7){ // bottom right corner 7,7
			directionsToLook.add(N);
			directionsToLook.add(W);
			directionsToLook.add(NW);
		}else if(y == 7 && x >= 1 && x <= 6){ // right column when y is 7
			directionsToLook.add(N);
			directionsToLook.add(S);
			directionsToLook.add(SW);
			directionsToLook.add(W);
			directionsToLook.add(NW);
		}else{ // check regular directions
			directionsToLook.add(N);
			directionsToLook.add(NE);
			directionsToLook.add(E);
			directionsToLook.add(SE);
			directionsToLook.add(S);
			directionsToLook.add(SW);
			directionsToLook.add(W);
			directionsToLook.add(NW);
		}
		
		for (int currentDirection : directionsToLook) {

			Position currentPos = new Position(x, y);
			ArrayList<Position> toflip = new ArrayList<Position>();
			
			moveDir(currentPos, currentDirection);
			
			while ((currentPos.getRow()<8) && (currentPos.getRow()>=0) && (currentPos.getCol()<8) && (currentPos.getCol()>=0)) {

				char currentPiece = gameBoard.getBoardCoor(currentPos.getRow(),currentPos.getCol());
				
				if(currentPiece == opponent()) {
					
					toflip.add(currentPos.asPiece(opponent()));
					moveDir(currentPos, currentDirection);
					
				} else if (currentPiece != gameBoard.getWhoseMove()) {
					break;
				} else {
					allflip.addAll(toflip);
					break;
				}
			}
			
		}
		return allflip;
	}
	
	public boolean legalMove(int row, int col) {
		return checkMove(row, col).size() > 0 && gameBoard.getBoardCoor(row, col) == '_';
	}

}
