package MainFrame.Model;

import java.util.ArrayList;

public class Progress {

	// Note to self: Do not become a lender.

	public static ArrayList<Progresses> progress = new ArrayList<Progresses>();
	public static ArrayList<Point> points = new ArrayList<Point>();

	public void loadFromSave() {

	}

	public void newGame() {
		progress.clear();
		progress.add(new Progresses("test", false));
	}

	public void add(String name, boolean bool) {
		progress.add(new Progresses(name, bool));
	}

	public void setTrue(String name) {
		if (progress.stream().noneMatch(s -> s.name.contains(name))) {
			add(name, true);
		}
		progress.stream().filter(s -> s.getName().contentEquals(name))
				.forEach(s -> s.setDone(true));

	}

	public void setFalse(String name) {
		progress.stream().filter(s -> s.getName().contentEquals(name))
				.forEach(s -> s.setDone(false));

		if (progress.stream().noneMatch(s -> s.name.contains(name))) {
			add(name, false);
		}
	}

	public void setPoint(String name, int value) {
		points.stream().filter(s -> s.getName().contentEquals(name))
				.forEach(s -> s.setValue(value));

		if (points.stream().noneMatch(s -> s.getName().contains(name))) {
			points.add(new Point(name, value));
		}
	}

	public void incPoint(String name) {
		points.stream().filter(s -> s.getName().contentEquals(name))
				.forEach(s -> s.setValue(s.getValue() + 1));

	}

	public void decPoint(String name) {
		points.stream().filter(s -> s.getName().contentEquals(name))
				.forEach(s -> s.setValue(s.getValue() - 1));

	}

}
