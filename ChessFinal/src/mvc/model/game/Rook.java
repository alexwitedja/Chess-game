package mvc.model.game;
public class Rook extends Piece{

	public Rook(char playerColour,ChessBoard board,int setyCoord,int setxCoord) {
        super(playerColour,board, setyCoord, setxCoord);
        if (playerColour=='w') {
            symbol = 'R';
        } else if (playerColour=='b') {
            symbol= 'r';
        }
    }
	
	 public boolean checkMove(int toyCoord, int toxCoord) {
		if ((xCoord==toxCoord)^(yCoord== toyCoord)) //either x or y needs to stay same but not both
			if(Math.abs(this.xCoord - toxCoord)<3&&Math.abs(this.yCoord - toyCoord)<3) {
				return checkCollision(toyCoord, toxCoord);//limit to 2 squares
			}
	        //move is invalid so 
		 	System.out.println("Illegal Rook Move");
	        return false;
	    }

	 private boolean checkCollision(int toyCoord, int toxCoord){
		//only need to check move of 2 spaces
		 if(Math.abs(toxCoord-this.xCoord)>2||Math.abs(toyCoord-this.yCoord)>2) {
			 System.out.println("Movement exceeds 2 squares");
			 return false;
		 }
		 if((Math.abs(toxCoord-this.xCoord)==2)||(Math.abs(toyCoord-this.yCoord)==2)){
			 //check adjacent square for collision
			 if(board.getPieceAt((toyCoord+yCoord)/2,(toxCoord+xCoord)/2)!=null){
				 //a collision has been detected
				 System.out.println("Collision detected");
				 return false;
			 }
		 }
		 return true;
	   }

	 
	
}