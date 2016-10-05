package VINT.Model;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class Letter {

	String letter;
	int opacity;
	int letterWidth;

	public Letter(String Letter, int opacity) {
		this.opacity = opacity;
		this.letter = Letter;

		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform, true,
				true);
		Font font = new Font("Tahoma", Font.BOLD, 24);
		this.letterWidth = (int) (font.getStringBounds(Letter, frc).getWidth());
	}

	public static  String letterToString(Letter[] x) {
		String temp = "";
		for (Letter m : x) {
			temp = temp + m.letter;
		}
		return temp;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public int getOpacity() {
		return opacity;
	}

	public void setOpacity(int opacity) {
		this.opacity = opacity;
	}

	public int getLetterWidth() {
		return letterWidth;
	}

	public void setLetterWidth(int letterWidth) {
		this.letterWidth = letterWidth;
	}

}
