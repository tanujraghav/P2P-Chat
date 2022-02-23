import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ActiveNodeWorker extends Thread {

	public ActiveNode node;
	public Socket socket;
	public PrintWriter stream;

	public ActiveNodeWorker(Socket socket, ActiveNode activeNode) {
		this.node = activeNode;
		this.socket = socket;
	}

	public PrintWriter getPrintWriter() {
		return stream;
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.stream = new PrintWriter(socket.getOutputStream(), true);

			while(true) {
				node.sendMessage(reader.readLine());
			}
		}

		catch(Exception e) {
      System.out.println("ERROR! ActiveNodeWorker: void run()");
			node.getServlets().remove(this);
		}
	}

}
