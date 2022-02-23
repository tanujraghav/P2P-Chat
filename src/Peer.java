import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Peer {

	public static void main(String args[]) throws Exception {
		Scanner scanner = new Scanner(System.in);
		char input;
		String message = "";

		ActiveNode activeNode;
		NodeInfo node = new NodeInfo();
	
		Join joinrequest= new Join();
		Leave leaves=new Leave();

		MessageContent note = new MessageContent();

		System.out.println("Choose one of the following operations to perform :\nJ - JOIN\nM - SENDMESSAGE\nL - LEAVE\n");
		input = scanner.next().charAt(0);

		if(input == 'J') { 
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
			System.out.println("Enter Name");
			node.Name = reader.readLine();
	
			System.out.println("Enter Port");
			node.Port = Integer.parseInt(reader.readLine());
	
			activeNode = new ActiveNode(node.Port);
			activeNode.start();

			node.prev = false;
			node.next = false;

			System.out.println("Enter the name and port number to connect");
			String inputs = reader.readLine();

			joinrequest.Listener(inputs, node);

			if(joinrequest.hasJoined == true) {
				System.out.println("Joined sucessfully : choose one\n M - SENDMESSAGE \n L - LEAVE\n");
		
				input = scanner.next().charAt(0);

				if(input=='M') {
					note.Communicate(reader, node.Name, activeNode, node);  
				}
				else if(input=='L') {
					leaves.leave(node.Port, activeNode);
				}
			}
			else if(input=='M') {
				System.out.println("Please Join first to communicate");
			}
			else if(input=='L') {
				System.out.println("Cant leave without joining");
			}
			else {
				System.out.println("Invalid Input");
				System.exit(0);
			}
		}
		
		scanner.close();
	}
	
	public String mymsg(BufferedReader reader) throws IOException {
		String msg;
		msg = reader.readLine();
		return msg;
	}

}