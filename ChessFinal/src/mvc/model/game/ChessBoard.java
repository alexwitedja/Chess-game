package mvc.model.game;

public class ChessBoard 
{
	private final int BOARD_HEIGHT = 6;
	private final int BOARD_WIDTH = 6;
	public Piece[][] board = new Piece[BOARD_HEIGHT][BOARD_WIDTH];
	
	/* loadBoard()
	 * loads the required pieces onto the board
	 */
	public void loadBoard()
	{
		/*Load pieces for player 1 */
		board[5][0] = new Rook('w', this, 5, 0);
		board[5][1] = new Bishop('w', this, 5, 1);
		board[5][2] = new Knight('w', this, 5, 2);
		board[5][3] = new Knight('w', this, 5, 3);
		board[5][4] = new Bishop('w', this, 5, 4);
		board[5][5] = new Rook('w', this, 5, 5);

		/*Load pieces for player 2 */
		board[0][0]  = new Rook('b', this, 0, 0);
		board[0][1] = new Bishop('b', this, 0, 1);
		board[0][2] = new Knight('b', this, 0, 2);
		board[0][3] = new Knight('b', this, 0, 3);
		board[0][4] = new Bishop('b', this, 0, 4);
		board[0][5] = new Rook('b', this, 0, 5);
	}

	public void displayBoard()
	{		
		/*Print numbers on top of the board*/
		  System.out.print("  ");
		for(int i = 0; i < BOARD_HEIGHT; i++)
		{
			System.out.printf("   %d", i);
		}
			System.out.println("");
			System.out.println("   -------------------------");

		/*Print grid of the board and side numbers*/	
		for(int i = 0; i < BOARD_HEIGHT; i++)
		{
			System.out.print(i);
			System.out.print("  | ");
			for(int j = 0; j < BOARD_WIDTH; j++)
			{
				if(board[i][j] == null)
				{
					System.out.print(" ");
				}
				else 
				{
					System.out.print(board[i][j].symbol+" ");
				}
				System.out.printf(" | ");
			}
			
			System.out.println();
			System.out.println("   -------------------------");
		}
	}

	public boolean setPieceAt(Piece piece, int yCoord, int xCoord) {
		//if take a piece then set isalive
		if(piece.xCoord==xCoord&&piece.yCoord==yCoord) {
			System.out.println("Piece must move to a different square");
			return false;
		}
		if(getPieceAt(yCoord,xCoord)==null||getPieceAt(yCoord,xCoord).colour!=piece.colour) {
			if(getPieceAt(yCoord,xCoord)!=null) {
				board[yCoord][xCoord].isAlive=false;
				System.out.println("Piece taken");
			}
			board[yCoord][xCoord]=piece;
			//remove traces from source and reset
			board[piece.yCoord][piece.xCoord]=null;
			piece.yCoord=yCoord;
			piece.xCoord=xCoord;
			System.out.printf("Piece moved to [%d,%d]\n",yCoord,xCoord);
			return true;
		}
		System.out.println("Cannot take your own piece");
		return false;
	}

	public Piece getPieceAt(int yCoord, int xCoord) {
		return board[yCoord][xCoord];
	}
	
	public boolean movePiece(Piece piece, int toyCoord, int toxCoord)
	{
		if(toyCoord>=BOARD_HEIGHT||toyCoord<0||toxCoord>=BOARD_WIDTH||toxCoord<0) {
			System.out.println("Outside board");
			return false;
		}
		//check valid move and setPiece at location
		return piece.checkMove(toyCoord, toxCoord)&&setPieceAt(piece, toyCoord,toxCoord);
	}
	
	
	
}



