package mvc.model.database;

import java.io.*;
import java.time.*;
import java.util.*;
import mvc.model.game.*;

public class GameDAO {
	private ArrayList<Game> games;
	private int gameId;
	private PlayerDAO playerDAO;

	private int currentGame;

	private static final String GAMESFILE = "src/mvc/model/database/Games.csv";

	public GameDAO(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(GAMESFILE));

			String line;
			games = new ArrayList();

			while ((line = br.readLine()) != null) {
				String[] gameLine = line.split(",");

				Game game = new Game(playerDAO.getPlayerByUsername(gameLine[1]),
						playerDAO.getPlayerByUsername(gameLine[2]), Integer.parseInt(gameLine[3]),
						gameLine[4].charAt(0));
				ChessBoard board = new ChessBoard();
				game.cb = board;
				gameId = Integer.parseInt(gameLine[0]);
				games.add(game);
			}

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Game> getAllGames() {
		return this.games;
	}
	
	public ArrayList<Game> getPlayersGames(Player playerWhite, Player playerBlack)
	{
		final ArrayList<Game> playersGames = new ArrayList();
		
		for (int i=0; i<this.games.size(); i++)
		{
			if (games.get(i).playerWhite.getUsername() == playerWhite.getUsername() &&
					games.get(i).playerBlack.getUsername() == playerBlack.getUsername())
			{
				playersGames.add(games.get(i));
			}
			else if (games.get(i).playerWhite.getUsername() == playerBlack.getUsername() &&
					games.get(i).playerBlack.getUsername() == playerWhite.getUsername())
			{
				playersGames.add(games.get(i));
			} 
		}
		return playersGames;
	}

	public void addNewGame(Game game) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(GAMESFILE, true));
			game.gameID = gameId + 1;
			String gameLine = game.gameID + "," + game.playerWhite.getUsername() + "," + game.playerBlack.getUsername()
					+ "," + game.playerTurn + "," + game.getMovesRemain();

			bw.write(gameLine);
			bw.newLine();

			bw.close();

			games.add(game);

			gameId++;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveGame(Game game) {
		BufferedReader br;

		try {
			br = new BufferedReader(new FileReader(GAMESFILE));

			String line;
			String output = "";
			while ((line = br.readLine()) != null) {
				String[] gameLine = line.split(",");
				if (Integer.parseInt(gameLine[0]) != gameId) {
					output += line + "\n";

				}
				br.close();
			}

			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(GAMESFILE, true));

				bw.write(output);

				String gameLine = game.gameID + "," + game.playerWhite.getUsername() + ","
						+ game.playerBlack.getUsername() + "," + game.playerTurn + "," + game.getMovesRemain();
				bw.write(gameLine);
				bw.newLine();

				bw.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
