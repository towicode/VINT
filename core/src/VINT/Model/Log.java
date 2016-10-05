package VINT.Model;

public class Log {

	private ModelActor actor;
	private String text;

	public Log(ModelActor actor, String text) {
		this.actor = actor;
		this.text = text;
	}

	public ModelActor getActor() {
		return actor;
	}

	public void setActor(ModelActor actor) {
		this.actor = actor;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
