import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.json.Json;
import javax.json.JsonObject;
import java.net.Socket;

public class ChatNode extends Thread {

	private BufferedReader reader;

	public ChatNode(Socket socket) throws IOException {
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
	}

	public void run() {
		boolean flag = true;

		while(flag) {
			try {
				JsonObject jsonobject = Json.createReader(reader).readObject();
				if(jsonobject.containsKey("username")) {
					System.out.println("[" + jsonobject.getString("username") + "]: "+ jsonobject.getString("message"));
				}
			}

			catch(Exception e) {
	      System.out.println("ERROR! ChatNode: void run()");
				flag = false;
				interrupt();
			}
		}
	}

}
