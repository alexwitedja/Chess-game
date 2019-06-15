package mvc.view.board;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import mvc.controller.GridButtonListener;
import mvc.model.game.Game;
import mvc.model.game.Piece;
import mvc.view.factory.PiecesFactory;
import mvc.view.menu.IntroPanel;

public class ChessBoard extends JPanel{
	
	private Game game;
	private IntroPanel ip;
	private ScorePanel sp;
	private GUI gui;
	
	private JButton[][] chessBoardSquares = new JButton[6][6];
	private Image[][] chessPieceImages = new Image[2][3];
	
	public static final int ROOK = 0, BISHOP = 1, KNIGHT = 2;
	public static final int[] STARTING_ROW = {
			ROOK, BISHOP, KNIGHT, KNIGHT, BISHOP, ROOK
	};
	private static final int CHESS_DIMENSION = 6;
	
	public ChessBoard(Game game, IntroPanel ip, ScorePanel sp, GUI gui) {
		
		this.game = game;
		this.ip = ip;
		this.sp = sp;
		this.gui= gui;
		
		setLayout(new GridLayout(CHESS_DIMENSION, CHESS_DIMENSION));
		setBorder((new CompoundBorder(
                new EmptyBorder(8,8,8,8),
                new LineBorder(Color.BLACK)
                )));
		setBackground(Color.LIGHT_GRAY);
		initButtons();
		initGrid();
		initImages();
		setVisible(false);
	}
	
	private void initButtons() {
		
		Insets buttonMargin = new Insets(0,0,0,0);
		for(int i = 0; i < CHESS_DIMENSION; i++) {
			for(int j = 0; j < CHESS_DIMENSION; j++) {
			JButton b = new JButton();
			
			b.setMargin(buttonMargin);
			
			ImageIcon icon = new ImageIcon(new BufferedImage(64,64, BufferedImage.TYPE_INT_ARGB));
			b.setIcon(icon);
			
				if((j % 2 == 1 && i % 2 == 1)||(j % 2 == 0 && i % 2 == 0)) {
					b.setBackground(Color.decode("#FFFACD"));
				}
				else {
					b.setBackground(Color.decode("#593E1A"));
				}
				
				chessBoardSquares[j][i] = b;
			}
		}
		
	}
	
	private void initGrid() {
		
		for(int i = 0; i < CHESS_DIMENSION; i++) {
			for(int j = 0; j < CHESS_DIMENSION; j++) {
				add(chessBoardSquares[j][i]);
				chessBoardSquares[i][j].addActionListener(new GridButtonListener(
						chessBoardSquares, chessBoardSquares[i][j], game, gui)); 
			}
		}
	}
	
	private void initImages() {
		
		BufferedImage buffImage;
		String fileName;
		File img;
		
		for(int i = 0; i < chessPieceImages.length; i++) {
			for(int j = 0; j < chessPieceImages[i].length; j++) {
				switch(i) {
				
				case 0:
					fileName = PiecesFactory.getFilePath(0,j);
					img = new File(fileName);
					try {
						buffImage = ImageIO.read(img);
						chessPieceImages[0][j] = buffImage;
					} 
					catch (IOException e) {
						System.out.println(e);
					}
					
				case 1:
					fileName = PiecesFactory.getFilePath(1,j);
					img = new File(fileName);
					try {
						buffImage = ImageIO.read(img);
						chessPieceImages[1][j] = buffImage;
					} 
					catch (IOException e) {
						System.out.println(e);
					}
				}
			}
		}
	}
	
	public void startGame() {
		
		setVisible(true);
		ip.setVisible(false);
		sp.setVisible(true);
		
		for(int i = 0; i < CHESS_DIMENSION; i++) {
			for(int j = 0; j < CHESS_DIMENSION; j++) {
				chessBoardSquares[i][j].setIcon(new ImageIcon(new BufferedImage(
						64,64, BufferedImage.TYPE_INT_ARGB)));
			}
		}

		for(int i = 0; i < STARTING_ROW.length; i++) {
			chessBoardSquares[i][0].setIcon(new ImageIcon(chessPieceImages[0][STARTING_ROW[i]]));
		}
		for(int i = 0; i < STARTING_ROW.length; i++) {
			chessBoardSquares[i][5].setIcon(new ImageIcon(chessPieceImages[1][STARTING_ROW[i]]));
		}
		
		
	}
	
	public void destroy() {
		this.setVisible(false);
		sp.setVisible(false);
		ip.setVisible(true);
	}
	
	public void redraw() {
		Piece[][] pgroup = game.cb.board;
		
		for(int i = 0; i < CHESS_DIMENSION; i++) {
			for(int j = 0; j < CHESS_DIMENSION; j++) {
				Piece piece = pgroup[i][j];
				char symbol = 'm';
				if(piece != null) {
					symbol = (char) piece.getSymbol();
				}
				switch(symbol) {
				case 'k':
					chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][2]));
					break;
				case 'K':
					chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][2]));
					break;
				case 'b':
					chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][1]));
					break;
				case 'B':
					chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][1]));
					break;
				case 'r':
					chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[0][0]));
					break;
				case 'R':
					chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[1][0]));
					break;
				default:
					chessBoardSquares[j][i].setIcon(new ImageIcon(new BufferedImage(64,64, BufferedImage.TYPE_INT_ARGB)));
				}
			}
		}	
	} 
}
