package mvc.model.game;
import java.util.Scanner;

import mvc.model.database.*;

import mvc.model.game.ChessBoard;
import mvc.view.board.GameEngineCallback;
public class Game {

	public char playerTurn='w';
	public ChessBoard cb;
	private Piece selectedPiece;
	public int movesRemain;
	GameEngineCallback callback;
	private Boolean gameOver;

	public Player playerWhite;
	public Player playerBlack;
	public Player activePlayer = playerWhite;
	public int gameID;
	
	public boolean[][] whiteMoves;
	public boolean[][] blackMoves;

	//Still need option to quit
	//Still need test to see if game is still running i.e win condition
	//Still need a way to tally score
	
	private AuthService AS = new AuthService();
	
	public Game()
	{
		
	}
	
	public Game(Player white, Player black, int movesRemain, char playerTurn)
	{
		this.playerWhite = white;
		this.playerBlack = black;	
		this.playerTurn = playerTurn;
		
		if (playerTurn == 'b')
		{
			this.activePlayer = this.playerBlack;
		}
		else
		{
			this.activePlayer = this.playerWhite;
		}
		
		this.movesRemain = movesRemain;
	}
	
	public void resumeGame()
	{
		this.gameOver = false;
		callback.move(getScore('w', cb), getScore('b', cb), movesRemain);
	}
	
	public void playGame(int moves) {
		playerTurn = 'w';
		cb = new ChessBoard();
		cb.loadBoard();
		setMovesRemain(moves);
		gameOver = false;
		callback.move(0, 0, movesRemain);
		
	}
	
	public boolean checkPlayers() {
		return AS.checkLogin();
	}
	
	public boolean register(String username, String password) throws PlayerException {
		String email = "dummy@dummy.com";
		
		return AS.registerPlayer(username, email, password, username);
	}
	
	public boolean login(String username, String password) throws PlayerException {
		
		return AS.authenticatePlayer(username, password);
	
	}
	
	


	private void setMovesRemain(int n){
        movesRemain=n;//get this from the player inputs
    }


	public void addGameEngineCallback(GameEngineCallback callback) {
		this.callback = callback;
	}

	public void selectPiece(int y, int x) {
		Piece[][] pgroup= cb.board;
		if(x>5||y>5||x<0||y<0) {
			System.out.println("Invalid Coordinates");
		}else if(pgroup[y][x]!=null&&pgroup[y][x].colour!=playerTurn) {
            System.out.printf("You must select a %c piece\n",playerTurn);
            selectedPiece = null;
            return;
		}else {
		   	if(pgroup[y][x] != null) {
				selectedPiece = pgroup[y][x];
				System.out.printf("You have selected %s currently in position "
						+ "%d, %d\n", selectedPiece.getClass(),
						selectedPiece.yCoord, selectedPiece.xCoord);
				return;

			}
		}
		selectedPiece = null;
		System.out.println("Failure to find piece");
	}

	public void movePiece(int y, int x) {

		if(cb.movePiece(selectedPiece, y ,x)) {
			cb.displayBoard();
			int whiteScore = getScore('w', cb);
			int blackScore = getScore('b', cb);
            movesRemain-=1;
			callback.move(whiteScore, blackScore, movesRemain);
			System.out.printf("%s Piece moved successfully to %d %d\n", selectedPiece.getClass(),
					selectedPiece.yCoord, selectedPiece.xCoord );
            if(playerTurn == 'b' ) {
                playerTurn = 'w';
            }
            else {
                playerTurn = 'b';
            }

            if(checkWin())
            {
                System.out.printf("Game Finished!");
                doTheWin();
            }
			selectedPiece = null;
		}
		else {
			selectedPiece = null;
		}
	}

	public boolean checkSelected() {
		if(selectedPiece != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public int getMovesRemain() {
		return movesRemain;//debug
		//return player1.moves+ player2.moves
	}

	private int getScore(char playerColour,ChessBoard cb) {
		int score = 30;
		for(int i =0;i<6;i++) {
			for(int j =0;j<6;j++) {
				if(cb.board[j][i]!=null&&cb.board[j][i].colour!=playerColour)
					score-=5;
			}
		}
		return score;
	}

	//still under construction
	public boolean[][] possibleMoves(Piece piece,int y,int x){
		boolean[][] moveGrid = new boolean[6][6];
		for(int j=0;j<6;j++){
			for(int i=0;i<6;i++){
				moveGrid[j][i]=piece.checkMove(j,i);
			}
		}
		return moveGrid;
	}

	private boolean checkWin() {
		return (getMovesRemain()==0)||getScore('w', cb)==30||getScore('b', cb)==30;
	}

	private char doTheWin(){
        //clear the board and display details
	    if(getScore('w', cb)>getScore('b', cb)){
	        int playerWhite = 1;
            int playerBlack = -1;
            return 'w';
        }else if(getScore('w', cb)<getScore('b', cb)){
            int playerBlack = 1;
            int playerWhite = -1;
            return 'b';
        } //else no one gets points
	    
	    
	    return 'd';
    }
	
	public boolean getGameOver() {
		
		return gameOver;
	}

	public void logout() {
		AS.logoutAll();
		
	}

}
