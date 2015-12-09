import java.io.IOException;
import java.util.Scanner;


public class TestReversi {
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Scanner console = new Scanner(System.in);

		//***Uncomment for Minimax opponent***
		//MiniMax opponent = new MiniMax();

		Reversi game = new Reversi();

		game.initBoard();

		int row, col;

		System.out.println("Reversi");

		QLearner learner = new QLearner();
		learner.initWeights();

		//TRAINING
		
		//		int numgames = 50000;
		//		for(int i = 1; i <= numgames; i++){
		//			Reversi g = new Reversi();
		//			g.initBoard();
		//			while(!g.gameOver()){
		//				learner.maxTraining(g);
		//				learner.minTraining(g);
		//			}
		//			if(i%10000 == 0){
		//				System.out.println("game number: " + i);
		//				System.out.println("WEIGHTS FOR X PLAYER\n");
		//				for(int j=0; j<learner.wiX.length; j++){
		//					System.out.println("\n wiX[" + j + "] = " + learner.wiX[j] + ";");
		//				}
		//				System.out.println("WEIGHTS FOR O PLAYER\n");
		//				for(int k=0; k<learner.wiO.length; k++){
		//					System.out.println("\n wiO[" + k + "] = " + learner.wiO[k] + ";");
		//				}
		//
		//			}
		//
		//		}
		
		//END OF TRAINING

		//PLAYING
		
		while (!game.gameOver()) {
			System.out.println("Player " + game.gameBoard.getWhoseMove()
					+" make a move: pick a row and a column");
			
			//USER INPUT
			
						while(true){
							System.out.println(game);
			
							System.out.println(game.gameBoard.getWhoseMove() + " player pick a move");
			
							String line = console.nextLine();
							
							@SuppressWarnings("resource")
							Scanner lineScan = new Scanner(line);
							row = lineScan.nextInt();
							col = lineScan.nextInt();
			
							if(game.checkMove(row-1, col-1).size() > 0) break;
							System.out.println("Invalid move " + row + "," + col);
							System.out.println("Please re-enter");
			
			
						}
						game.makeMove(row-1, col-1); 
			
			//END OF USER INPUT
			
			//AI

			if(!game.gameOver()){
				if(game.gameBoard.getWhoseMove() == 'O'){ 
					System.out.println("Please wait while I think of a move...");
					
					learner.playMin(game); //play min for RL Player
					
					//play Min for Minimax
					//opponent.makeBestMoveForMin(game, 6, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
					
				}else if(game.gameBoard.getWhoseMove() == 'X'){
					
					//play Max for Minimax
					//opponent.makeBestMoveForMax(game, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
					//learner.playMax(game); //play max for RL player
				}

			}
			
			//END OF AI

			System.out.println(game.toString());
			if(game.gameOver()){
				System.out.println("Winner is " + game.getWinner());
			}else if(game.isDraw()){
				System.out.println("Game is a tie.");
			}

		}
		//END OF PLAYING
		
	}



}
