import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ActiveNode extends Thread {

  NodeInfo node = new NodeInfo();

  int activePort;
  private ServerSocket serverSocket;
  public ArrayList<ActiveNodeWorker> servlets = new ArrayList<ActiveNodeWorker>();

  public ActiveNode(int port) throws IOException {
    this.activePort = port;
    this.serverSocket = new ServerSocket(port);
  }

  public int getActiveNode() {
    return activePort;
  }

  public void run() {
    try {
      while(true) {
        ActiveNodeWorker i = new ActiveNodeWorker(serverSocket.accept(), this);
        servlets.add(i);
        i.start();
      }
    }

    catch(Exception e) {
      System.out.println("ERROR! ActiveNode: void run()");
      e.printStackTrace();
    }    	
  }

  public void sendMessage(String msg) {
    try {
      servlets.forEach(i->i.getPrintWriter().println(msg));
    }

    catch(Exception e) {
      System.out.println("ERROR! ActiveNode: void sendMessage()");
      e.printStackTrace();
    }
  }
  
  public ArrayList<ActiveNodeWorker> getServlets(){
      return servlets;  	
  }
      
  boolean Join(String name,int port) {		
    return true;
  }
	
}
