//Reversi, MiniMax Class 2/23/15
public class MiniMax {
	
	public double minValue(BoardGame g, int depth, double alpha, double beta){
		int legalMoveCounter = 0;
		if(g.gameOver() || depth == 0){
			return g.getBoardValue();
		}else{
			for (int r = 0; r < 8 && r >= 0; r++){
				for(int c = 0; c < 8 && c >= 0; c++){
					if(g.legalMove(r,c)){
						legalMoveCounter++;
						g.makeMove(r, c);
						double v = maxValue(g, depth - 1, alpha, beta);
						g.undoMove();
						if(v <= alpha){
							return v;
						}
						if(v < beta){
							beta = v;
						}
					}
				}	
			}
			if(legalMoveCounter == 0){
				alpha = maxValue(g, depth - 1, alpha, beta);
			}
			return beta;
		}
	}
	
	public double maxValue(BoardGame g, int depth, double alpha, double beta){
		int legalMoveCounter = 0;
		if(g.gameOver() || depth == 0){
			return g.getBoardValue();
		}else{
			for(int r = 0; r < 8 && r >= 0; r++){
				for(int c = 0; c < 8 && c >= 0; c++){
					if(g.legalMove(r,c)){
						legalMoveCounter++;
						g.makeMove(r, c);
						double v = minValue(g, depth - 1, alpha, beta);
						g.undoMove();
						if(v >= beta){
							return v;
						}
						if(v > alpha){
							alpha = v;
						}
					}
				}
			}
			if(legalMoveCounter == 0){
				beta = minValue(g, depth - 1, alpha, beta);
			}
			return alpha;	
		}
	}
	
    public void makeBestMoveForMin(BoardGame g, int depth, double alpha, double beta) {
    	int bestRow = -1;
    	int bestCol = -1;
    	for (int r = 0; r < 8 && r >= 0; r++){
			for(int c = 0; c < 8 && c >= 0; c++){
				if(g.legalMove(r,c)){
					g.makeMove(r, c);
					double v = maxValue(g, depth - 1, alpha, beta);
					g.undoMove();
					if(v < beta){
						bestRow = r;
						bestCol = c;
					}
				}
			}
		}
    	if(!(bestRow == -1 || bestCol == -1)){
    		g.makeMove(bestRow, bestCol);
    	}
    } // end makeBestMoveForMin()
    
    public void makeBestMoveForMax(BoardGame g, int depth, double alpha, double beta) {
    	int bestRow = -1;
    	int bestCol = -1;
    	for(int r = 0; r < 8 && r >= 0; r++){
    		for(int c = 0; c < 8 && c >= 0; c++){
    			if(g.legalMove(r,c)){
    				g.makeMove(r, c);
    				double v = minValue(g, depth - 1, alpha, beta);
    				g.undoMove();
    				if(v > alpha){
    					bestRow = r;
    					bestCol = c;
    				}
    			}
    		}
    	}
    	if(!(bestRow == -1 || bestCol == -1)){
    		g.makeMove(bestRow, bestCol);
    	}
    } // end makeBestMoveForMax()


}