package proxy.server;

import java.net.*;
import java.io.*;

import proxy.policy.ServiceBroker;

public class ProxyServer {
	
	// Implementar a política de seleção
	private static ServiceBroker serviceBroker = new ServiceBroker();
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		boolean listening = true;

		int port = 111; // default
		try {
			port = Integer.parseInt(args[0]);
		} catch (Exception e) {
			// ignore me
		}

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Started on: " + port);
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + args[0]);
			System.exit(-1);
		}

		while (listening) {
			Socket cliente = serverSocket.accept();
			System.out.println("iniciando");
			new Thread(new ProxyThread(cliente, serviceBroker),"ProxyThread").start();
		}
		serverSocket.close();
	}
}