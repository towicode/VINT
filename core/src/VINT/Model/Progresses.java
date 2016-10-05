package VINT.Model;

public class Progresses {

	boolean done;
	String name;

	public Progresses(String name, boolean done) {
		this.done = done;
		this.name = name;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
