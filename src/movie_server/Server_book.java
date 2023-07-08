package movie_server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server_book implements Runnable{
	ServerSocket ss = null;
	Socket s = null;
	
	public Server_book() {
		try {
			ss = new ServerSocket(7780);
			System.out.println("뿡");
			
			new Thread(this).start();
		} catch (Exception e) {
		}
	}
	@Override
	public void run() {
		while(true) {
			try {
				s = ss.accept();
				CP_Client cc = new CP_Client(s, this);
				cc.start();
			} catch (Exception e) {
			}
		}
	}
	public static void main(String[] args) {
		new Server_book();
	}
}
