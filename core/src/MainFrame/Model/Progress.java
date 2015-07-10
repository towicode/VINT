package MainFrame.Model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Progress {

	// Note to self: Do not become a lender.

	public static ArrayList<Progresses> progress = new ArrayList<>();
	public static ArrayList<Point> points = new ArrayList<>();

	public void loadFromSave() {

	}

	public void newGame() {
		progress.clear();
		progress.add(new Progresses("test", false));
	}

    public Progresses getProgress(String name){
        try {
            return progress.stream().filter(s -> s.getName().contentEquals(name)).findFirst().get();
        } catch (NoSuchElementException e){
            //there was no progress so we'll create it and set it to false
            add(name, false);
            //then we'll just use a little bit of recursion to return that newly created progress.
            return getProgress(name);
        }
    }

    public Point getPoint(String name){
        try {
            return points.stream().filter(s -> s.getName().contentEquals(name)).findFirst().get();
        } catch (NoSuchElementException e){
            //there was no progress so we'll create it and set it to 0
            points.add(new Point(name, 0));
            //then we'll just use a little bit of recursion to return that newly created progress.
            return getPoint(name);
        }
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
