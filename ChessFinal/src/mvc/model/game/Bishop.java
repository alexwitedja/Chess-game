package mvc.model.game;
public class Bishop extends Piece{

	public Bishop(char playerColour,ChessBoard board,int setyCoord,int setxCoord) {
        super(playerColour,board, setyCoord, setxCoord);
        if (playerColour=='w') {
            symbol = 'B';
        } else if (playerColour=='b') {
            symbol= 'b';
        }
    }
	
	 public boolean checkMove(int toyCoord, int toxCoord) {
	        if (Math.abs(this.xCoord - toxCoord) == Math.abs(this.yCoord - toyCoord)) 
	        	if(Math.abs(this.xCoord - toxCoord)<3)
	        		return checkCollision(toyCoord,toxCoord); //limit to 2 squares
	        //move is invalid so 
			 System.out.println("Illegal Bishop Move");
	        return false;
	    }

	 private boolean checkCollision(int toyCoord, int toxCoord){
		 if(Math.abs(toxCoord-this.xCoord)>2||Math.abs(toyCoord-this.yCoord)>2) {
			 System.out.println("Movement exceeds 2 squares");
			 return false;
		 }
		 //only need to check move of 2 spaces
		 if((Math.abs(toxCoord-this.xCoord)==2)){
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
