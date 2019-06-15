package mvc.model.game;

public class UnitTests {

	public static void main(String[] args) {
		ChessBoard cb = new ChessBoard();
		cb.loadBoard();
		cb.board[3][3] = new Bishop('w', cb, 3, 3);
		cb.board[3][2] = new Bishop('b', cb, 3, 2);
		cb.board[3][4] = new Bishop('b', cb, 3, 4);
		cb.board[4][4] = new Bishop('b', cb, 4, 4);
		cb.board[4][3] = new Bishop('b', cb, 4, 3);
		cb.board[4][2] = new Bishop('b', cb, 4, 2);
		cb.board[2][4] = new Bishop('b', cb, 2, 4);
		cb.board[2][3] = new Bishop('b', cb, 2, 3);
		cb.board[2][2] = new Bishop('b', cb, 2, 2);
		
		
		cb.displayBoard();
		cb.movePiece(cb.board[3][3], 1, 3);
		cb.movePiece(cb.board[3][3], 1, 1);
		cb.movePiece(cb.board[3][3], 2, 2);


		cb.board[3][3] = new Rook('w', cb, 3, 3);
		cb.displayBoard();
		cb.movePiece(cb.board[3][3], 1, 2);
		cb.movePiece(cb.board[3][3], 1, 3);
		cb.movePiece(cb.board[3][3], 2, 3);
		
		cb.displayBoard();
		cb.board[3][3] = new Knight('w', cb, 3, 3);
		cb.board[1][2] = new Knight('b', cb, 1, 2);
		cb.displayBoard();
		cb.movePiece(cb.board[3][3], 0, 0);
		cb.movePiece(cb.board[3][3], 1, 2);
		cb.displayBoard();

		
	}

}
