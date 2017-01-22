package Server;

import java.io.Serializable;

public class ClientQuery implements Serializable {
	public String type;
	public boolean isResponseTrue;
	public String parameters[] = new String[10];

	public ClientQuery(String type) {
		for (int i = 0; i < 10; i++) {
			parameters[i] = new String();
		}
		this.type = type;
	}
}
