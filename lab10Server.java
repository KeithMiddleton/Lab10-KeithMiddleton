import java.io.*;
import java.net.*;
import java.util.Vector;

public class lab10Server{
	protected Socket clientSocket           = null;
	protected ServerSocket serverSocket     = null;
	protected lab10ServerThread[] threads    = null;
	protected int numClients                = 0;

	public static int SERVER_PORT = 16789;
	public static int MAX_CLIENTS = 25;

	public lab10Server() {
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("---------------------------");
			System.out.println("Chat Server Application is running");
			System.out.println("---------------------------");
			System.out.println("Listening to port: "+SERVER_PORT);
			threads = new lab10ServerThread[MAX_CLIENTS];
			while(true) {
				clientSocket = serverSocket.accept();
				System.out.println("Client #"+(numClients+1)+" connected.");
				threads[numClients] = new lab10ServerThread(clientSocket);
				threads[numClients].start();
				numClients++;
			}
		} catch (IOException e) {
			System.err.println("IOException while creating server connection");
		}
	}

	public static void main(String[] args) {
		lab10Server app = new lab10Server();
	}
}