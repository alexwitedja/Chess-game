package mvc.model.game;
public class Knight extends Piece{

	public Knight(char playerColour,ChessBoard board,int setyCoord,int setxCoord) {
        super(playerColour,board, setyCoord, setxCoord);
        if (playerColour=='w') {
            symbol = 'K';
        } else if (playerColour=='b') {
            symbol= 'k';
        }
    }
	
	 public boolean checkMove(int toyCoord, int toxCoord) {
		 //difference between x and y is 1
		 if (Math.abs(Math.abs(this.xCoord - toxCoord) - Math.abs(this.yCoord - toyCoord))==1)
			//combined x and y translation should equal 3
			 return Math.abs(this.xCoord - toxCoord)+Math.abs(this.yCoord - toyCoord)==3;
	        //move is invalid so
		 	System.out.println("Illegal Knight Move");
	        return false;
	    }
	 
	
}