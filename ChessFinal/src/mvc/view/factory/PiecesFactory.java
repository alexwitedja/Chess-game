package mvc.view.factory;

import java.io.File;

public class PiecesFactory {
	
	private static final String[][] FILE_STRINGS = new String[][] {{"BR.gif", "BB.gif", "BN.gif"}, 
		{"WR.gif", "WB.gif", "WN.gif"}};
	
	public static final int NUM_COLORS = FILE_STRINGS.length;

	public static String getFilePath(int color, int pieceType)
	{
		return String.format("images%s%s", File.separator, FILE_STRINGS[color][pieceType]);
	}
	
	public static String getText(int color, int pieceType) 
	{
		return FILE_STRINGS[color][pieceType].substring(FILE_STRINGS[color][pieceType].indexOf("_") + 1, 
				FILE_STRINGS[color][pieceType].indexOf("."));
	}

}
