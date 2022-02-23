import java.io.BufferedReader;
import java.io.StringWriter;
import javax.json.Json;

public class MessageContent {

	public void Communicate(BufferedReader reader, String username, ActiveNode activeNode, NodeInfo node) throws Exception{

		Leave leaves = new Leave();
		Peer p = new Peer();

		System.out.println("Enter message to communicate");
		System.out.println(">>> [c change]");

		try {
			boolean flag = true;
			while(flag) {
				String message = p.mymsg(reader);
				if(message.equals("L")){
					flag = false;
					leaves.leave(node.Port, activeNode);	
					break;
				}
				else {
					StringWriter stringwriter = new StringWriter();
					Json.createWriter(stringwriter).writeObject(Json.createObjectBuilder()
							.add("username", username)
							.add("message", message)
							.build());
					activeNode.sendMessage(stringwriter.toString());
				}
			}
		}
		catch(Exception e) {
			System.out.println("ERROR! MessageContent: void Communicate()");
		}	
	}

}
