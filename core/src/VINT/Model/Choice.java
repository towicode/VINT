package VINT.Model;

import java.util.List;

public class Choice {

	boolean active;
	boolean chosen;
	String name;
	String choice;
	List<String> choices;

	public Choice(boolean active, String name, List<String> choices) {
		this.active = active;
		this.name = name;
		this.choices = choices;
		this.chosen = false;
		this.choice = "";

	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}

	public void choose(String choice) {
		this.chosen = true;
		this.choice = choice;
	}

	public String getChoice() {
		return this.choice;
	}
}
