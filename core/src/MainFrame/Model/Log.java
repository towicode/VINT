package MainFrame.Model;

public class Log {

	private Actor actor;
	private String text;

	public Log(Actor actor, String text) {
		this.actor = actor;
		this.text = text;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
