package VINT.Model;

public class ModelActor {

	private String name;
	private int r;
	private int g;
	private int b;

	public ModelActor(String x, int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.setName(x);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
