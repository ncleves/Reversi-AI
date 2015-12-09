import java.util.ArrayList;
import java.util.Random;

public class QLearner{
	
	int[] fiX = new int[30];// make a total features X
	int[] fiO = new int[30];// make a total features O
	
	double[] wiX = new double[30];//weights for X player
	
	double[] wiO = new double[30];//weights for O player
	
	int[] saveStateX = new int [30];
	int[] saveStateO = new int [30];
	
	public void maxTraining(Reversi g){
		double bestValue = Double.NEGATIVE_INFINITY;
		int bestRow = 0;
		int bestCol = 0;
		if(!g.gameOver()){
			double epsilon = new Random().nextDouble();
			if(epsilon <= .9){ //epsilon is 90% make the best move 90% of the time
				for (int r = 0; r < 8 && r >= 0; r++){
					for(int c = 0; c < 8 && c >= 0; c++){
						if(g.legalMove(r,c)){
							g.makeMove(r, c);
							totalFeaturesX(g);
							double v = valueX(fiX, g);
							if(v > bestValue){
								bestValue = v;
								bestRow = r;
								bestCol = c;
							}
							g.undoMove();

						}
					}
				}
				g.makeMove(bestRow, bestCol);
				totalFeaturesX(g);
				
			}else{ //when epsilon is 10%
				ArrayList<Position> randLegalMoves = new ArrayList<Position>();
				for(int r = 0; r < 8; r++){
					for(int c = 0; c < 8; c++){
						if(g.legalMove(r, c)){
							randLegalMoves.add(new Position(r, c));
						}
					}
				}
				int randomChoice = new Random().nextInt(randLegalMoves.size());
				Position randPos = randLegalMoves.get(randomChoice);
				g.makeMove(randPos.getRow(), randPos.getCol());
				totalFeaturesX(g);
				
			}
			for(int i = 0; i < fiX.length; i++){
				wiX[i] = wiX[i] + .0001*(reward(g) + (valueX(fiX, g) - valueX(saveStateX, g))) * fiX[i];
				//wi = wi + alpha[r + Q(s,'b) - Q(s,a)] * fi(s)
			}                      	
			
		}
		saveStateX = stateX(g);
	}
		
	public void minTraining(Reversi g){
		double bestValue = Double.POSITIVE_INFINITY;
		int bestRow = 0;
		int bestCol = 0;
		if(!g.gameOver()){
			double epsilon = new Random().nextDouble();
			if(epsilon <= .9){ //epsilon is 90% make the best move 90% of the time
				for (int r = 0; r < 8 && r >= 0; r++){
					for(int c = 0; c < 8 && c >= 0; c++){
						if(g.legalMove(r,c)){
							g.makeMove(r, c);
							totalFeaturesO(g);
							double v = valueO(fiO, g);
							if(v < bestValue){
								bestValue = v;
								bestRow = r;
								bestCol = c;
							}
							g.undoMove();
						}
					}
				}
				g.makeMove(bestRow, bestCol);
				totalFeaturesO(g);

			}else{ //when epsilon is 10%
				ArrayList<Position> randLegalMoves = new ArrayList<Position>();
				for(int r = 0; r < 8; r++){
					for(int c = 0; c < 8; c++){
						if(g.legalMove(r, c)){
							randLegalMoves.add(new Position(r, c));
						}
					}
				}
				int randomChoice = new Random().nextInt(randLegalMoves.size());
				Position randPos = randLegalMoves.get(randomChoice);
				g.makeMove(randPos.getRow(), randPos.getCol());     
				totalFeaturesO(g);
			}
			for(int i = 0; i < fiO.length; i++){
				wiO[i] = wiO[i] + .0001*(reward(g) + (valueO(fiO, g) - valueO(saveStateO, g))) * fiO[i];
			//wi = wi + alpha[r + Q(s,'b) - Q(s,a)] * fi(s)
			}     
			
		}
		saveStateO = stateO(g);
	}
	
	public void playMax(Reversi g){
		double bestValue = Double.NEGATIVE_INFINITY;
		int bestRow = 0;
		int bestCol = 0;
		if(!g.gameOver()){
			for (int r = 0; r < 8 && r >= 0; r++){
				for(int c = 0; c < 8 && c >= 0; c++){
					if(g.legalMove(r,c)){
						g.makeMove(r, c);
						totalFeaturesX(g);
						double v = valueX(fiX, g);
						//System.out.println("THIS IS V: " + v);
						if(v > bestValue){
							bestValue = v;
							bestRow = r;
							bestCol = c;
						}
						g.undoMove();

					}
				}
			}
			g.makeMove(bestRow, bestCol);
			totalFeaturesX(g);
		}
	}
	
	public void playMin(Reversi g){
		double bestValue = Double.POSITIVE_INFINITY;
		int bestRow = 0;
		int bestCol = 0;
		if(!g.gameOver()){
			for (int r = 0; r < 8 && r >= 0; r++){
				for(int c = 0; c < 8 && c >= 0; c++){
					if(g.legalMove(r,c)){
						g.makeMove(r, c);
						totalFeaturesO(g);
						double v = valueO(fiO, g);
						//System.out.println("THIS IS V: "+ v);
						if(v < bestValue){
							bestValue = v;
							bestRow = r;
							bestCol = c;
						}
						g.undoMove();
					}
				}
			}
			g.makeMove(bestRow, bestCol);
			totalFeaturesO(g);
		}
	}
	
	//***FEATURES LIST***
	//**CORNER FEATURES**
	public void cornerFeaturesX(Reversi g){
		if(g.gameBoard.getBoardCoor(0, 0) == 'X'){
			fiX[0] = 1; 
		}else{
			fiX[0] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(0, 7) == 'X'){
			fiX[1] = 1; 
		}else{
			fiX[1] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(7, 0) == 'X'){
			fiX[2] = 1; 
		}else{
			fiX[2] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(7, 7) == 'X'){
			fiX[3] = 1; 
		}else{
			fiX[3] = 0;
		}
		
	}
	
	public void cornerFeaturesO(Reversi g){
		if(g.gameBoard.getBoardCoor(0, 0) == 'O'){
			fiO[0] = 1; 
		}else{
			fiO[0] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(0, 7) == 'O'){
			fiO[1] = 1; 
		}else{
			fiO[1] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(7, 0) == 'O'){
			fiO[2] = 1; 
		}else{
			fiO[2] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(7, 7) == 'O'){
			fiO[3] = 1; 
		}else{
			fiO[3] = 0;
		}
	}
	//**END OF CORNER FEATURES**
	
	//**EDGE FEATURES FOR X**
	public void topEdgeFeaturesX(Reversi g){
		if(g.gameBoard.getBoardCoor(0, 1)== 'X'){
			fiX[4] = 1; 
		}else{
			fiX[4] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(0, 2)== 'X'){
			fiX[5] = 1; 
		}else{
			fiX[5] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(0, 3)== 'X'){
			fiX[6] = 1; 
		}else{
			fiX[6] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(0, 4)== 'X'){
			fiX[7] = 1; 
		}else{
			fiX[7] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(0, 5)== 'X'){
			fiX[8] = 1; 
		}else{
			fiX[8] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(0, 6)== 'X'){
			fiX[9] = 1; 
		}else{
			fiX[9] = 0;
		}
		
	}
	
	public void botEdgeFeaturesX(Reversi g){
		if(g.gameBoard.getBoardCoor(7, 1)== 'X'){
			fiX[10] = 1; 
		}else{
			fiX[10] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(7, 2)== 'X'){
			fiX[11] = 1; 
		}else{
			fiX[11] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(7, 3)== 'X'){
			fiX[12] = 1; 
		}else{
			fiX[12] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(7, 4)== 'X'){
			fiX[13] = 1; 
		}else{
			fiX[13] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(7, 5)== 'X'){
			fiX[14] = 1; 
		}else{
			fiX[14] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(7, 6)== 'X'){
			fiX[15] = 1; 
		}else{
			fiX[15] = 0;
		}

	}
	
	public void leftEdgeFeaturesX(Reversi g){
		if(g.gameBoard.getBoardCoor(1, 0)== 'X'){
			fiX[16] = 1; 
		}else{
			fiX[16] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(2, 0)== 'X'){
			fiX[17] = 1; 
		}else{
			fiX[17] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(3, 0)== 'X'){
			fiX[18] = 1; 
		}else{
			fiX[18] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(4, 0)== 'X'){
			fiX[19] = 1; 
		}else{
			fiX[19] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(5, 0)== 'X'){
			fiX[20] = 1; 
		}else{
			fiX[20] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(6, 0)== 'X'){
			fiX[21] = 1; 
		}else{
			fiX[21] = 0;
		}
		
	}
	
	public void rightEdgeFeaturesX(Reversi g){
		if(g.gameBoard.getBoardCoor(1, 7)== 'X'){
			fiX[22] = 1; 
		}else{
			fiX[22] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(2, 7)== 'X'){
			fiX[23] = 1; 
		}else{
			fiX[23] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(3, 7)== 'X'){
			fiX[24] = 1; 
		}else{
			fiX[24] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(4, 7)== 'X'){
			fiX[25] = 1; 
		}else{
			fiX[25] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(5, 7)== 'X'){
			fiX[26] = 1; 
		}else{
			fiX[26] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(6, 7)== 'X'){
			fiX[27] = 1; 
		}else{
			fiX[27] = 0;
		}
	}
	//**END OF EDGE FEATURES FOR X**
	
	//**EDGE FEATURES FOR O**
	public void topEdgeFeaturesO(Reversi g){
		if(g.gameBoard.getBoardCoor(0, 1)== 'O'){
			fiO[4] = 1; 
		}else{
			fiO[4] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(0, 2)== 'O'){
			fiO[5] = 1; 
		}else{
			fiO[5] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(0, 3)== 'O'){
			fiO[6] = 1; 
		}else{
			fiO[6] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(0, 4)== 'O'){
			fiO[7] = 1; 
		}else{
			fiO[7] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(0, 5)== 'O'){
			fiO[8] = 1; 
		}else{
			fiO[8] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(0, 6)== 'O'){
			fiO[9] = 1; 
		}else{
			fiO[9] = 0;
		}
	}
	
	public void botEdgeFeaturesO(Reversi g){
		if(g.gameBoard.getBoardCoor(7, 1)== 'O'){
			fiO[10] = 1; 
		}else{
			fiO[10] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(7, 2)== 'O'){
			fiO[11] = 1; 
		}else{
			fiO[11] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(7, 3)== 'O'){
			fiO[12] = 1; 
		}else{
			fiO[12] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(7, 4)== 'O'){
			fiO[13] = 1; 
		}else{
			fiO[13] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(7, 5)== 'O'){
			fiO[14] = 1; 
		}else{
			fiO[14] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(7, 6)== 'O'){
			fiO[15] = 1; 
		}else{
			fiO[15] = 0;
		}
		
	}
	
	public void leftEdgeFeaturesO(Reversi g){
		if(g.gameBoard.getBoardCoor(1, 0)== 'O'){
			fiO[16] = 1; 
		}else{
			fiO[16] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(2, 0)== 'O'){
			fiO[17] = 1; 
		}else{
			fiO[17] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(3, 0)== 'O'){
			fiO[18] = 1; 
		}else{
			fiO[18] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(4, 0)== 'O'){
			fiO[19] = 1; 
		}else{
			fiO[19] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(5, 0)== 'O'){
			fiO[20] = 1; 
		}else{
			fiO[20] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(6, 0)== 'O'){
			fiO[21] = 1; 
		}else{
			fiO[21] = 0;
		}
	}
	
	public void rightEdgeFeaturesO(Reversi g){
		if(g.gameBoard.getBoardCoor(1, 7)== 'O'){
			fiO[22] = 1; 
		}else{
			fiO[22] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(2, 7)== 'O'){
			fiO[23] = 1; 
		}else{
			fiO[23] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(3, 7)== 'O'){
			fiO[24] = 1; 
		}else{
			fiO[24] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(4, 7)== 'O'){
			fiO[25] = 1; 
		}else{
			fiO[25] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(5, 7)== 'O'){
			fiO[26] = 1; 
		}else{
			fiO[26] = 0;
		}
		
		if(g.gameBoard.getBoardCoor(6, 7)== 'O'){
			fiO[27] = 1; 
		}else{
			fiO[27] = 0;
		}
	}
	//**END OF EDGE FEATURES FOR O**
	
	//**SCORE FEATURES**
	public void scoreFeatureX(Reversi g){
		if(g.gameBoard.getScoreForX() > g.gameBoard.getScoreForO()){
			fiX[28] = 1; 
		}else{
			fiX[28] = 0;
		}
	}
	
	public void scoreFeatureO(Reversi g){
		if(g.gameBoard.getScoreForO() > g.gameBoard.getScoreForX()){
			fiO[28] = 1; 
		}else{
			fiO[28] = 0;
		}
	}
	//END OF SCORE FEATURES
	
	
	public void totalFeaturesX(Reversi g){
		cornerFeaturesX(g);
		topEdgeFeaturesX(g);
		botEdgeFeaturesX(g);
		rightEdgeFeaturesX(g);
		leftEdgeFeaturesX(g);
		scoreFeatureX(g);
		//System.out.println("Total num features: "+totalNumFeatures);
	}
	
	public void totalFeaturesO(Reversi g){
		cornerFeaturesO(g);
		topEdgeFeaturesO(g);
		botEdgeFeaturesO(g);
		rightEdgeFeaturesO(g);
		leftEdgeFeaturesO(g);
		scoreFeatureO(g);
		//System.out.println("Total num features: "+totalNumFeatures);
	}
	
	public double valueX(int[] fiX, Reversi g){ //Q(s,a) for X
		double value = 0;
		for(int i = 0; i < fiX.length; i++){
			value += wiX[i]*fiX[i];
		}
		return value;
	}
	
	public double valueO(int[] fiO, Reversi g){ //Q(s,a) for O
		double value = 0;
		for(int i = 0; i < fiO.length; i++){
			value += wiO[i]*fiO[i];
		}
		return value;
	}
	
	public int[] stateX(Reversi g){
		int[] state = new int[fiX.length];
		for(int i=0; i < fiX.length; i++){
			state[i] = fiX[i];	
		}
		return state;
	}
	
	
	public int[] stateO(Reversi g){
		int[] state = new int[fiO.length];
		for(int i=0; i < fiO.length; i++){
			state[i] = fiO[i];	
		}
		return state;
	}
	
	public int reward(Reversi g){
		int reward = 0;
		if(g.gameOver()){
			if(g.gameBoard.getScoreForX() > g.gameBoard.getScoreForO()){
				reward = 1;
			}else if(g.gameBoard.getScoreForX() < g.gameBoard.getScoreForO()){
				reward = 1;
			}else{
				reward = 0;
			}
		}
		return reward;
	}
	
	public String toString2(Reversi g){
		String result = "";
		result += "\n";
		for(int x = 0; x < 8; x++){
			result += (x+1);
			for(int y = 0; y < 8; y++){
				result += g.gameBoard.getBoardCoor(x, y);	
			}
			result += "\n";
		}
		return result;
	}
	
	public void initWeights(){
		//WEIGHTS FOR X PLAYER
		
		 wiX[0] = 1.5318489523887165E10;

		 wiX[1] = 7.124072294014204E9;

		 wiX[2] = 1.5237827293154234E10;

		 wiX[3] = 1.3211760326732934E9;

		 wiX[4] = 7.763501692876904E10;

		 wiX[5] = 4.08606456422502E10;

		 wiX[6] = 6.797742126478243E10;

		 wiX[7] = 2.8643727562651295E10;

		 wiX[8] = 2.448175798799518E10;

		 wiX[9] = 1.2308000388181356E10;

		 wiX[10] = 8.356917264241388E8;

		 wiX[11] = 8.904112772789394E9;

		 wiX[12] = 4.553866698217029E9;

		 wiX[13] = 2.9045702081749425E9;

		 wiX[14] = 5.994738244199442E9;

		 wiX[15] = 3.52617709107289E8;

		 wiX[16] = 2.65114674900788E10;

		 wiX[17] = 3.39342106047627E10;

		 wiX[18] = 3.0018355685437046E10;

		 wiX[19] = 3.8603566117435524E10;

		 wiX[20] = 3.0777837971366146E10;

		 wiX[21] = 2.0820059474743626E10;

		 wiX[22] = 4.3765012051637924E10;

		 wiX[23] = 1.4887773313886196E10;

		 wiX[24] = 2.4381790091675785E10;

		 wiX[25] = 1.1166685561100525E10;

		 wiX[26] = 1.4921167233898842E10;

		 wiX[27] = 5.305388604258232E9;

		 wiX[28] = 2.9754448875459516E8;

		 wiX[29] = 0.0;
		 
		 //WEIGHTS FOR O PLAYER

		 wiO[0] = 4269575.043719302;

		 wiO[1] = 3899260.0916649327;

		 wiO[2] = 1697219.0298467462;

		 wiO[3] = 2852570.523938082;

		 wiO[4] = 2544213.7771836664;

		 wiO[5] = 3177175.3104474423;

		 wiO[6] = 2418812.820560316;

		 wiO[7] = 2852678.6714954944;

		 wiO[8] = 2848276.6182612893;

		 wiO[9] = 3051277.8824176015;

		 wiO[10] = 3693879.9664225853;

		 wiO[11] = 3098629.4826842444;

		 wiO[12] = 3259791.0399538185;

		 wiO[13] = 3424952.7993138977;

		 wiO[14] = 2962276.5752986614;

		 wiO[15] = 3418567.9265862643;

		 wiO[16] = 2416142.633971766;

		 wiO[17] = 2269165.8820172586;

		 wiO[18] = 2267199.691480815;

		 wiO[19] = 1749229.4419204365;

		 wiO[20] = 1733581.8865974285;

		 wiO[21] = 1733701.8055421887;

		 wiO[22] = 2808070.5792013872;

		 wiO[23] = 3304385.4353823494;

		 wiO[24] = 2543888.081713335;

		 wiO[25] = 2931126.985979685;

		 wiO[26] = 2332947.0253292653;

		 wiO[27] = 2378317.5258751726;

		 wiO[28] = 2377302.2907450553;

		 wiO[29] = 0.0;

		 
	}

	
	
}
