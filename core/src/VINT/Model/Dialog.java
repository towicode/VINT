package VINT.Model;

import java.awt.Color;
import java.util.ArrayList;

public class Dialog {

	String text;
	Color color;

	public Letter[] getText() {
		Letter[] x = new Letter[text.length()];
		for (int i = 0; i < text.length(); i++) {
			x[0] = new Letter(String.valueOf(text.charAt(i)), 255);
		}

		return x;

	}

	public void setText(String text) {
		this.text = text;

	}

	private void setText(Letter[] x) {
		String text = "";
		for (Letter m : x) {
			text += m.letter;
		}

		this.text = text;

	}

	public void drawAnimatedText(String gText) {
		Thread text = new Thread() {
			public void run() {
				setText("");
				boolean brk = false;
				for (int i = 0; i < gText.length(); i++) {
					ArrayList<Letter> gLetters = new ArrayList<Letter>();
					for (Letter let : getText()) {
						gLetters.add(let);
					}
					gLetters.add(new Letter(String.valueOf(gText.charAt(i)), 0));

					Letter[] x = gLetters.toArray(new Letter[gLetters.size()]);
					if (x.length > i + 1) {
						console.info("we think we need to cancel animation");
						break;
					}
					setText(x);

					for (int j = 1; j < 6; j++) {
						try {
							getText()[i].setOpacity(j * 50);
						} catch (ArrayIndexOutOfBoundsException e) {
							// System.out.println("user skipped");
							// Game.setText("");
							brk = true;
							break;
						}
						try {
							Thread.sleep(Settings.getTextSpeed());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					if (brk)
						break;
				}
			}

		};
		text.start();
	}

}
