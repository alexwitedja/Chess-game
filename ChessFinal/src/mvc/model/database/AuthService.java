package mvc.model.database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AuthService {
	
	private PlayerDAO playerDAO;
	
	// Setting up Player 1 and Player 2. These should be relocated to Game Class
	
	private Player player1;
	private Player player2;
	private int numPlayers = 0;
	
	public AuthService()
	{
		this.playerDAO = new PlayerDAO();	
	}
	
	public AuthService(PlayerDAO playerDAO)
	{
		this.playerDAO = playerDAO;
	}
	
	public boolean registerPlayer(String username, String email, String password, String nickname) throws PlayerException
	{
		if (playerDAO.validatePlayerByUsername(username)) 
		{
			/*
			 * 			System.out.println(String.format("Username already taken!"));
						return false;
			 */
			throw new PlayerException("Username already taken");
		}
		else
		{
			int id = playerDAO.getAllPlayers().size() + 1;
			String passwordHash = getHashedPassword(password);
			
			Player newPlayer = new Player(id, username, email, passwordHash, nickname, true);
			
			playerDAO.addNewPlayer(newPlayer);
			return true;

		}
	}
	
	public boolean authenticatePlayer(String username, String password) throws PlayerException
	{
		if (playerDAO.validatePlayerByUsername(username))
		{
			Player loginPlayer = playerDAO.getPlayerByUsername(username);

			if (loginPlayer.getPassword().equals(getHashedPassword(password)))
			{
				if(numPlayers == 0)
				{
					player1 = loginPlayer;
					numPlayers ++;
					System.out.println(String.format("%s succesfully logged in", loginPlayer.getNickname()));
				}
				else if (numPlayers == 1)
				{
					if (player1.getUsername().equals(loginPlayer.getUsername()))
					{
						System.out.println(String.format("%s already logged in!", loginPlayer.getNickname()));
						throw new PlayerException("Already Logged in");
					}
					player2 = loginPlayer;
					numPlayers ++;
					System.out.println(String.format("%s succesfully logged in", loginPlayer.getNickname()));
				}
				else
				{
					System.out.println(String.format("Too many players!"));
					throw new PlayerException("Too Many Players");
				}
	
				//Add Player to Game Class
				return true;
			}
			else
			{
				System.out.println(String.format("Invalid Login"));
				return false;
			}
		}
		System.out.println(String.format("Invalid Login"));
		throw new PlayerException("Invalid Username");
	}
	
	
	private static String getHashedPassword(String password)
	{
		String hashedPassword = null;
		try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
		return hashedPassword;
	}
	
	public void logoutPlayer (Player logoutPlayer) throws PlayerException
	{
		if (numPlayers == 1)
		{
			if (player1.getUsername() == logoutPlayer.getUsername())
			{
				player1 = player2;
				player2 = null;
				numPlayers --;
			}
		}
		else if (numPlayers == 2)
		{
			if (player1.getUsername() == logoutPlayer.getUsername())
			{
				player1 = player2;
				player2 = null;
				numPlayers --;
			}
			else if (player2.getUsername() == logoutPlayer.getUsername())
			{
				player2 = null;
				numPlayers --;
			}
		}

	}
	
	public void logoutAll ()
	{
		player1 = null;
		player2 = null;
		numPlayers = 0;
	}
	//registerUser
	//login return Player
	public boolean checkLogin() {
		if (player1 != null && player2 != null) {
			return true;
		}
		else {
			return false;
		}
	}
	public Player getPlayer1()
	{
		return this.player1;
	}
	
	public Player getPlayer2()
	{
		return this.player2;
	}

}
