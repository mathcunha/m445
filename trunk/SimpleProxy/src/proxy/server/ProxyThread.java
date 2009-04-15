package proxy.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Date;

import proxy.policy.EndPointInfo;
import proxy.policy.HistoryServiceProxy;
import proxy.policy.ServiceBroker;

public class ProxyThread implements Runnable {
	Socket socket;
	
	ServiceBroker serviceBroker;

	public ProxyThread(Socket socket, ServiceBroker serviceBroker) {
		this.socket = socket;
		
		this.serviceBroker = serviceBroker;
	}

	public void run() {
		try {

			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream2 = socket.getOutputStream();

			int bufferSize = 1024;
			byte buffer[] = new byte[bufferSize];
			int readBytes = inputStream.read(buffer);

			String service = "";
			String request = new String(buffer);
			int indice = request.indexOf("POST ");
			if (indice > -1) {
				int indice2 = request.indexOf(" ", indice + 5);
				service = request.substring(indice + 5, indice2 - 1);
			}

			int limite = service.lastIndexOf("/");
			String serviceName = (service.substring(0, service.lastIndexOf("/",
					limite)));
			serviceName = serviceName.substring(serviceName.lastIndexOf("/") + 1);

			
			EndPointInfo endPointInfo = new EndPointInfo(serviceName, "Somar", 1, new Date().getTime());
			URL endPoint = serviceBroker.chooseEndpoint(endPointInfo);
			

			// Host remoto
			Long inicio = System.currentTimeMillis();
			Socket connector = new Socket(endPoint.getHost(), endPoint.getPort());
			OutputStream outputStream = connector.getOutputStream();
			InputStream inputStream2 = connector.getInputStream();

			while (readBytes == bufferSize) {
				outputStream.write(buffer);
				readBytes = inputStream.read(buffer);
			}
			if (readBytes != -1) {
				outputStream.write(buffer, 0, readBytes);
				outputStream.flush();
				// running = false;
			}

			int bufferSize2 = 1024;
			byte buffer2[] = new byte[bufferSize2];
			Thread.sleep(1000);
			int readBytes2 = inputStream2.read(buffer2);

			while (readBytes2 == bufferSize2) {
				outputStream2.write(buffer2);
				readBytes2 = inputStream2.read(buffer2);
				System.out.println("readBytes2: " + readBytes2);
			}
			if (readBytes2 != -1) {
				outputStream2.write(buffer2, 0, readBytes2);
				outputStream2.flush();
				// running = false;
			}
			
			Long fim = System.currentTimeMillis();
			endPointInfo.setUrl(endPoint);
			endPointInfo.setTimeResponse(fim-inicio);
			serviceBroker.addEndPointInfo(endPointInfo);

			socket.close();
			connector.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}