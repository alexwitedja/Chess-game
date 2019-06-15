///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package mvc.model.database;
//
//import java.time.*;
//import java.time.format.*;
//import java.util.*;
//import static java.lang.System.exit;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Scanner;
///**
// *
// * @author brian
// */
//public class Chess {
//
//    /**
//     * @param args the command line arguments
//     * @throws DuplicatePlayerException 
//     */
//    public static void main(String[] args) throws PlayerException {
//	
//	PlayerDAO playerDAO = new PlayerDAO();
//	
//	ResultDAO resultDAO = new ResultDAO(playerDAO);
//	
//	AuthService authService = new AuthService(playerDAO);
//	
//	while (true)
//	{
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Press r to register / Press l to login / Press p to see all players / Press q to quit");
//		
//		String input = sc.nextLine();
//		if(input.equals("r")) {
//
//			registerPlayerPrompt(authService);
//		}
//		else if(input.equals("l")) {
//			sc.close();
//			loginPlayerPrompt(authService);
//		}
//		else if(input.equals("p")) {
//			sc.close();
//			printAllPlayers(playerDAO);
//		}
//		else if(input.equals("q")) {
//			sc.close();
//			exit(0);
//		}
//		
//	}
//	
//	
//    }
//    
//    private static void loginPlayerPrompt(AuthService authService) throws PlayerException
//    {
//    	System.out.println("Please login");
//    	System.out.print("Enter Username: ");
//    	Scanner sc = new Scanner(System.in);
//    	String username = sc.nextLine();
//    	
//    	System.out.print("Enter Password: ");
//    	String password = sc.nextLine();
//
//    	Player loginPlayer = authService.authenticatePlayer(username, password);
//    	
//
//    }
//    
//    private static void registerPlayerPrompt(AuthService authService) throws PlayerException
//    {
//    	System.out.println("Please register a Player");
//    	System.out.print("Enter Username: ");
//    	Scanner sc = new Scanner(System.in);
//    	String username = sc.nextLine();
//    	System.out.print("Enter Email Address: ");
//    	String email = sc.nextLine();
//    	System.out.print("Enter Password: ");
//    	String password = sc.nextLine();
//    	System.out.print("Enter Nickname: ");
//    	String nickname = sc.nextLine();
// 
//    	try {
//    		Player newPlayer = authService.registerPlayer(username, email, password, nickname);
//    		
//    		System.out.println(String.format("User %s created", newPlayer.getUsername()));
//
//        }
//        catch (PlayerException e)
//        {
//            //e.printStackTrace();
//          
//        }
//    	
//    	
//    	
//    }
//    
//    private static void printAllPlayers(PlayerDAO playerDAO)
//    {
//    	ArrayList<Player> players;
//    	
//    	players = playerDAO.getAllPlayers();
//    	
//    	System.out.println(String.format("Players:") );
//    	System.out.println(String.format("%4s %16s %16s %32s", "ID", "Username", 
//    		    "Nickname", "Email") );
//    	
//    	players.forEach((player) -> {
//    	    System.out.println(String.format("%4s %16s %16s %32s", player.getId(), player.getUsername(), 
//    		    player.getNickname(), player.getEmail()) );
//    	});
//    }
//    
//    private static void printAllResults(ResultDAO resultDAO)
//    {
//    	ArrayList<Result> results = resultDAO.getAllResults();
//    	
//    	System.out.println(String.format("Results:") );
//    	
//    	System.out.println(String.format("%4s %16s %16s %8s %16s %16s", "ID", "Winner", 
//    		    "Loser", "Score", "Start Time", "End Time") );
//    	
//    	DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
//                         .withLocale( Locale.UK )
//                         .withZone( ZoneId.systemDefault() );
//    	
//    	results.forEach((result) -> {
//    	    System.out.println(String.format("%4s %16s %16s %8s %16s %16s", result.getId(), result.getWinner().getNickname(),
//    		    result.getLoser().getNickname(), result.getWinnerScore() + "-" + result.getLoserScore(), 
//    		    formatter.format(result.getStartTime()), formatter.format(result.getEndTime()) ));
//    	});
//    	
//    }
//    
//    private static void printPlayerResults(Player player)
//    {
//    	System.out.println(String.format("%s's Results:", player.getNickname()) );
//    	
//    	
//    	System.out.println(String.format("%4s %16s %16s %8s %16s %16s", "ID", "Winner", 
//    		    "Loser", "Score", "Start Time", "End Time") );
//    	
//    	DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
//                .withLocale( Locale.UK )
//                .withZone( ZoneId.systemDefault() );
//    	
//    	player.getResults().forEach((result) -> {
//    	    System.out.println(String.format("%4s %16s %16s %8s %16s %16s", result.getId(), result.getWinner().getNickname(),
//    		    result.getLoser().getNickname(), result.getWinnerScore() + "-" + result.getLoserScore(), 
//    		    formatter.format(result.getStartTime()), formatter.format(result.getEndTime()) ));
//    	});
//    }
//}
