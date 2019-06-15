/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.database;

import java.util.ArrayList;

import mvc.model.database.GameDAO;
import mvc.model.database.PlayerDAO;
import mvc.model.game.Game;
import mvc.view.board.GameEngineCallback;

public class GameService {
    
	private GameDAO gameDAO;
	private PlayerDAO playerDAO;
	private PieceDAO pieceDAO;
	private AuthService authService;
    
    
	public GameService()
	{
		playerDAO = new PlayerDAO();
		gameDAO = new GameDAO(playerDAO);
		pieceDAO = new PieceDAO();
		
		authService = new AuthService(playerDAO);
	}
	
	public void resumeGame(Game game, GameEngineCallback callback)
	{
		pieceDAO.populateGameWithPieces(game);
		game.addGameEngineCallback(callback);
		game.resumeGame();
	}
	
	public void startNewGame(GameEngineCallback callback, int moves)
	{
		final Game game = new Game();
		game.addGameEngineCallback(callback);
		game.playerWhite = authService.getPlayer1();
		game.playerBlack = authService.getPlayer2();
		game.movesRemain = moves;
		gameDAO.addNewGame(game);
		game.playGame(moves);
	}
	
	public ArrayList<Game> getSaveGameList()
	{
		return gameDAO.getPlayersGames(authService.getPlayer1(), authService.getPlayer2());
	}
	
}
