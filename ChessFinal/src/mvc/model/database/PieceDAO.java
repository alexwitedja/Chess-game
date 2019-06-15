package mvc.model.database;

import java.io.*;
import java.time.*;
import java.util.*;
import mvc.model.game.*;

public class PieceDAO
{

    private static final String PIECEFILE = "src/mvc/model/database/Pieces.csv";

    public PieceDAO()
    {
    	
    }
    
    public void populateGameWithPieces(Game game)
    {
    	BufferedReader br;
    	
    	try
    	{
    	    br = new BufferedReader(new FileReader(PIECEFILE));
    	    
    	    String line;
    	    
    	    while ((line = br.readLine()) != null)
    	    {
    	    	String[] pieceLine = line.split(",");
    	    	if(Integer.parseInt(pieceLine[0]) == game.gameID)
    	    	{
    	    		Piece piece;
        	    	if(pieceLine[5].charAt(0) == 'k')
        	    	{
        	    		piece = new Knight(pieceLine[1].charAt(0), game.cb ,Integer.parseInt(pieceLine[2]), Integer.parseInt(pieceLine[3]));
        	    		piece.isAlive = Boolean.parseBoolean(pieceLine[4]);
        	    	}
        	    	else if(pieceLine[5].charAt(0) == 'r')
        	    	{
        	    		piece = new Rook(pieceLine[1].charAt(0), game.cb ,Integer.parseInt(pieceLine[2]), Integer.parseInt(pieceLine[3]));
        	    		piece.isAlive = Boolean.parseBoolean(pieceLine[4]);
        	    	}
        	    	else
        	    	{
        	    		piece = new Bishop(pieceLine[1].charAt(0), game.cb ,Integer.parseInt(pieceLine[2]), Integer.parseInt(pieceLine[3]));
        	    		piece.isAlive = Boolean.parseBoolean(pieceLine[4]);
        	    	}
        	    	
        	    	if (piece.isAlive())
        	    	{
        	    		game.cb.board[piece.xCoord][piece.yCoord] = piece;
        	    	}
        	    	
    	    	}
    	    		
    	    	
    	    	//gameid, playerColour, xcoord, ycoord, alive, type

    	    }
    	    
    	    br.close();
  
    	    
    	}
    	catch(Exception e)
    	{
                    e.printStackTrace();
    	}
    }
    
    

    public void addNewGame(Game game)
    {
		BufferedWriter bw;
		try
		{
		    bw = new BufferedWriter(new FileWriter(PIECEFILE, true));
		    
		    for(int i=0; i<game.cb.board.length; i++)
		    {
		    	for(int j=0; j<game.cb.board.length; j++)
		    	{
		    		if (game.cb.board[i][j] != null)
		    		{
		    			String pieceLine = game.gameID + "," + game.cb.board[i][j].getPlayerColour() + "," + game.cb.board[i][j].xCoord + "," + game.cb.board[i][j].yCoord + "," + game.cb.board[i][j].isAlive + "," + Character.toLowerCase(game.cb.board[i][j].getSymbol());
		    			bw.write(pieceLine);
		    		    bw.newLine();	
		    		}
		    	}
		    }

	
		    
		    
		    bw.close();
		    
		    
		    
		    
		}
		catch(Exception e)
		{
	                e.printStackTrace();
		}
    }
    
    public void saveGame(Game game, int gameId)
    {
    	BufferedReader br;
    	
    	try
    	{
    	    br = new BufferedReader(new FileReader(PIECEFILE));
    	    
    	    String line;
    	    String output = "";
    	    while ((line = br.readLine()) != null)
    	    {
    	    	String[] pieceLine = line.split(",");
    	    	if(Integer.parseInt(pieceLine[0]) != game.gameID)
    	    	{
    	    		output += line + "\n";
        	    	
    	    	}
    	    		
    	    	
    	    	//gameid, playerColour, xcoord, ycoord, alive, type

    	    }
    	    
    	    br.close();
    	    
    	    BufferedWriter bw;
    		try
    		{
    		    bw = new BufferedWriter(new FileWriter(PIECEFILE, true));
    		    
    		    bw.write(output);

    		    bw.close();
    		    
    		    this.addNewGame(game);

    		    
    		}
    		catch(Exception e)
    		{
    	                e.printStackTrace();
    		}
  
    	    
    	}
    	catch(Exception e)
    	{
                    e.printStackTrace();
    	}

    }
    
}

