package rentcarTest.conn;

import java.awt.Font;
import java.io.File;
import java.io.InputStream;

public class FontConnection {
	String fontPath = System.getProperty("user.dir") + File.separator + "fonts" + File.separator;
	InputStream is = HairshopManagementProgram.class.getResourceAsStream(fontPath + "GmarketSansTTFBold.ttf");
	File gfont_file = new File(fontPath + "GmarketSansTTFBold.ttf");
	Font gSansBold = Font.createFont(Font.TRUETYPE_FONT, gfont_file);
	Font gSansBold28 = gSansBold.deriveFont(28f);
	Font gSansBold20 = gSansBold.deriveFont(16f);
}
