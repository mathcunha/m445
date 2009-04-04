package proxy.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.io.IOUtils;

public class ProxyThread implements Runnable {
	Socket socket;

	public ProxyThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		BufferedReader reader;
		OutputStreamWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));

			writer = new OutputStreamWriter(socket.getOutputStream());
			StringBuilder request = new StringBuilder();
			String temp;
			String host = "";

			while (!(temp = reader.readLine()).equals("")) {
				if (temp.indexOf("Accept-Encoding") == -1) {
					request.append(temp);
				}

				request.append("\r\n");

				if (temp.indexOf("Host:") > -1) {
					host = temp.split(": ")[1];
				}
			}

			request.append("\r\n");
			String proxyRequest = new String(request);
			System.out.println(proxyRequest);

			System.out.println("Repassando dados para o servidor...");
			Socket connector = new Socket(host, 80);

			OutputStreamWriter d = new OutputStreamWriter(connector
					.getOutputStream());
			d.write(proxyRequest);
			d.flush();

			System.out.println("Esperando resposta de volta do servidor.");
			String resp = IOUtils.toString(connector.getInputStream());
			System.out.println(resp);

			d.close();
			connector.close();

			System.out.println("Repassando do proxy para o browser.");

			writer.write(resp);
			writer.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}