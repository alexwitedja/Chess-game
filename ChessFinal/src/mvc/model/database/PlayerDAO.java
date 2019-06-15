package mvc.model.database;

import java.io.*;
import java.util.*;

public class PlayerDAO
{
    private ArrayList<Player> players;

    private static final String PLAYERFILE = "src/mvc/model/database/Players.csv";
    
    private String playerFile;

    public PlayerDAO()
    {
    	
    	playerFile = PLAYERFILE;
	BufferedReader br;
	
	try
	{
	    br = new BufferedReader(new FileReader(playerFile));
	    
	    String line;
	    players = new ArrayList();
	    
	    while ((line = br.readLine()) != null)
	    {
		String[] playerLine = line.split(",");
		
		Player player = new Player(Integer.parseInt(playerLine[0]), playerLine[1], playerLine[2], 
			playerLine[3], playerLine[4], Boolean.parseBoolean(playerLine[5]));
		
		players.add(player);
	    }
	    
	    br.close();
	    
	}
	catch(Exception e)
	{
                e.printStackTrace();
	}
    }
    
    public PlayerDAO(String InputPlayerFile)
    {
    	playerFile = InputPlayerFile;
	BufferedReader br;
	
	try
	{
	    br = new BufferedReader(new FileReader(playerFile));
	    
	    String line;
	    players = new ArrayList();
	    
	    while ((line = br.readLine()) != null)
	    {
		String[] playerLine = line.split(",");
		
		Player player = new Player(Integer.parseInt(playerLine[0]), playerLine[1], playerLine[2], 
			playerLine[3], playerLine[4], Boolean.parseBoolean(playerLine[5]));
		
		players.add(player);
	    }
	    
	    br.close();
	    
	}
	catch(Exception e)
	{
                e.printStackTrace();
	}
    }


    public ArrayList<Player> getAllPlayers()
    {
	    return this.players;
    }
    
    public boolean validatePlayerByEmail(String email)
    {
    	return players.stream().anyMatch((player) -> (player.getEmail().equals(email)));
    }
    
    public boolean validatePlayerByUsername(String username)
    {
    	return players.stream().anyMatch((player) -> (player.getUsername().equals(username)));
    }

    public Player getPlayerByEmail(String email)
    {
		return players.stream()
			.filter((player) -> (player.getEmail().equals(email)))
			.findAny()
			.orElse(null);
    }
    
    public Player getPlayerByUsername(String username)
    {
		return players.stream()
			.filter((player) -> (player.getUsername().equals(username)))
			.findAny()
			.orElse(null);
    }
    
    public Player getPlayerById(int id)
    {
		return players.stream()
			.filter((player) -> (player.getId() == id))
			.findAny()
			.orElse(null);
    }

    public void addNewPlayer(Player player)
    {
	BufferedWriter bw;
	try
	{
	    bw = new BufferedWriter(new FileWriter(playerFile, true));
	    
	    String playerLine = player.getId() + "," + player.getUsername() + "," + player.getEmail()
		    + "," + player.getPassword() + "," + player.getNickname() + "," + player.getActive();

	    bw.write(playerLine);
	    bw.newLine();
	    
	    bw.close();
	    
	    players.add(player);
	    
	}
	catch(Exception e)
	{
                e.printStackTrace();
	}
    }
    
    public void removeLastPlayerLine()
    {
    	
    }
}

