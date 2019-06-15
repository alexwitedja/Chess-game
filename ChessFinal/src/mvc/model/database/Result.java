/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.database;

import java.time.*;

/**
 *
 * @author brian
 */
public class Result {
    
    private int id;
    private Player winner;
    private Player loser;
    private int winnerScore;
    private int loserScore;
    private Instant startTime;
    private Instant endTime;

    public Result(int id, int winnerScore, int loserScore, Instant startTime, Instant endTime)
    {
	this.id = id;
	this.winnerScore = winnerScore;
	this.loserScore = loserScore;
	this.startTime = startTime;
	this.endTime = endTime;
    }
    
    public int getId()
    {
	return this.id;
    }
    
    public void setId(int id)
    {
	this.id = id;
    }
    
    public Player getWinner()
    {
	return this.winner;
    }
    
    public void setWinner(Player winner)
    {
	this.winner = winner;
	winner.addResult(this);
    }
    
    public Player getLoser()
    {
	return this.loser;
    }
    
    public void setLoser(Player loser)
    {
	this.loser = loser;
	loser.addResult(this);
    }
    
    public int getWinnerScore()
    {
	return this.winnerScore;
    }
    
    public void setWinnerScore(int winnerScore)
    {
	this.winnerScore = winnerScore;
    }
    
    public int getLoserScore()
    {
	return this.loserScore;
    }
    
    public void setLoserScore(int loserScore)
    {
	this.loserScore = loserScore;
    }
    
    public Instant getStartTime()
    {
	return this.startTime;
    }
    
    public void setStartTime(Instant startTime)
    {
	this.startTime = startTime;
    }
    
    public Instant getEndTime()
    {
	return this.endTime;
    }
    
    public void setEndTime(Instant endTime)
    {
	this.endTime = endTime;
    }
}
