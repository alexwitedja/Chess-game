package mvc.model.game;
//need function in board class to check that x and y movements do not go ouside the board or exceed 2
public abstract class Piece {

		public char colour;
		public boolean isAlive;
		public int xCoord;
		public int yCoord;
		//Instance of the gameBoard
		public ChessBoard board;
		public char symbol; //used to print to board, needed for text GUI
		
		public Piece(char playerColour, ChessBoard chessBoard,int setyCoord,int setxCoord){
			colour=playerColour;
			board =chessBoard;
			yCoord=setyCoord;
			xCoord=setxCoord;
			isAlive=true;

			
		}
		
		
		//Useful for calculating score
		public boolean isAlive() {
			return isAlive;
		}
		//returns w or b
		public char getPlayerColour() {
			return colour;
		}
			
		//use this to print to board
		public int getSymbol() {
			return symbol;
		}

		public abstract boolean checkMove(int toyCoord, int toxCoord);
		
}
