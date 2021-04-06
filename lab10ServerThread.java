import java.io.*; 
import java.net.*;
import java.util.*;

public class lab10ServerThread extends Thread{
	protected Socket socket       = null;
	protected PrintWriter out     = null;
	protected BufferedReader in   = null;

	//Constructor
	public lab10ServerThread(Socket socket){
		super();
		this.socket = socket;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.err.println("IOEXception while opening a read/write connection");
		}
	}

	public void run(){
		out.println("Connected");	

		boolean endOfSession = false;
		while(!endOfSession) {
			endOfSession = processCommand();
		}
		try {
			socket.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	protected boolean processCommand(){
		String message = null;
		out.println("hello");
		try {
			message = in.readLine();
		} catch (IOException e) {
			System.err.println("Error reading command from socket.");
			return true;
		}
		if (message == null) {
			return true;
		}		
	
		return processCommand(message);
	}

	protected boolean processCommand(String message){
		out.println(message);
		return true;
	}
}