package buet.threebyzero.autoSecuritySystem.server;


import java.io.Serializable;

public class Data implements Serializable {
	private String element;

	public Data() {
		this.element = "";
	}

	public Data(String element) {
		this.element = element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getElement() {
		return this.element;
	}
}