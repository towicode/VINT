package VINT.Model;

import java.util.prefs.Preferences;

public class Settings {
	private final static Preferences prefs = Preferences.userRoot().node(
			"com/mainframe/settings");

	public static int getWidth() {
		return (prefs.getInt("WIDTH", 1280));
	}

	public static void setWidth(int x) {
		prefs.putInt("WIDTH", x);
	}

	public static int getHeight() {
		return (prefs.getInt("HEIGHT", 720));
	}

	public static void setHeight(int x) {
		prefs.putInt("HEIGHT", x);
	}

	public static void setTextSpeed(int x) {
		prefs.putInt("TEXT_SPEED", x);
	}

	public static int getTextSpeed() {
		return (prefs.getInt("TEXT_SPEED", 50)); // TODO default text speed

	}

}