import java.net.Socket;

public class Join {
	boolean hasJoined;

	public void Listener(String peers, NodeInfo node) throws Exception {
		String[] peer = peers.split(" ");

		for(int i = 0 ; i < peer.length ; i++) {
			String[] peerinfo = peer[i].split(":");
			System.out.println("Connected to " + peerinfo[0] + "@" + peerinfo[1]);

			Socket PNode = new Socket();
			Socket SNode = new Socket();

			try {
				if(node.prev == false) {
					NodeInfo P = new NodeInfo();
					P.Name = peerinfo[0];
					P.Port = Integer.valueOf(peerinfo[1]);

					PNode = new Socket("localhost", Integer.valueOf(peerinfo[1]));

					hasJoined = true;
					new ChatNode(PNode).start();
				}
				else if(node.next == false) {
					NodeInfo S = new NodeInfo();
					S.Port = Integer.valueOf(peerinfo[1]);
					S.Name = peerinfo[0];

					SNode = new Socket("localhost", Integer.valueOf(peerinfo[1]));

					hasJoined = true;
					new ChatNode(SNode).start();
				}
				else {
					System.out.print("Node has both Successor and Predecessor!");
				}
			}
			catch(Exception e) {
				System.out.println("ERROR! Join: void Listener");
				e.printStackTrace();
			}
		}
	}

}
