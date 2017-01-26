package Server;

import java.io.Serializable;

public class ClientQuery implements Serializable {
	public String type;
	public boolean isResponseTrue;
	public String parameters[] = new String[10];
	private Object[] additionalObjects;

	public ClientQuery(String type) {
		for (int i = 0; i < 10; i++) {
			parameters[i] = new String();
		}
		this.type = type;
	}

	public void setAdditionalObjects(Object[] objects) {
		additionalObjects = objects;
	}

	public Object[] getAdditionalObjects() {
		return additionalObjects;
	}
}
