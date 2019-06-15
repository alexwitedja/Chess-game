package mvc.model.database;

import java.io.*;
import java.time.*;
import java.util.*;

public class ResultDAO
{
    private ArrayList<Result> results;

    private static final String RESULTFILE = "src/mvc/model/database/Results.csv";

    public ResultDAO(PlayerDAO playerDAO)
    {
	BufferedReader br;
	
	try
	{
	    br = new BufferedReader(new FileReader(RESULTFILE));
	    
	    String line;
	    results = new ArrayList();
	    
	    while ((line = br.readLine()) != null)
	    {
		String[] resultLine = line.split(",");
		
		Result result = new Result(Integer.parseInt(resultLine[0]),
			Integer.parseInt(resultLine[3]), Integer.parseInt(resultLine[4]), 
			Instant.ofEpochSecond(Integer.parseInt(resultLine[5])), Instant.ofEpochSecond(Integer.parseInt(resultLine[6])));
		
		result.setWinner(playerDAO.getPlayerById(Integer.parseInt(resultLine[1])));
		result.setLoser(playerDAO.getPlayerById(Integer.parseInt(resultLine[2])));
		
		results.add(result);
	    }
	    
	    br.close();
	    
	}
	catch(Exception e)
	{
                e.printStackTrace();
	}
    }


    public ArrayList<Result> getAllResults()
    {
	    return this.results;
    }
    
    

    public void addNewResult(Result result)
    {
	BufferedWriter bw;
	try
	{
	    bw = new BufferedWriter(new FileWriter(RESULTFILE, true));
	    
	    String resultLine = result.getId() + "," + result.getWinner().getId() + "," + result.getLoser().getId()
		    + "," + result.getWinnerScore() + "," + result.getLoserScore() + "," + result.getStartTime().getEpochSecond()
		    + "," + result.getEndTime().getEpochSecond();

	    bw.write(resultLine);
	    bw.newLine();
	    
	    bw.close();
	    
	    results.add(result);
	    
	}
	catch(Exception e)
	{
                e.printStackTrace();
	}
    }
}

