public class Leave {

	public void leave(int port, ActiveNode node) throws Exception {
		try{
			boolean flag = true;

			while(flag) {
				for(ActiveNodeWorker i: node.servlets) {
					if(i.node.activePort == port) {    
						System.out.println("Node @ " +  i.node.activePort  + " is removed.");
						flag = false;
						break;
					}
				}	
			}
			System.exit(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
