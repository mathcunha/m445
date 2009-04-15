package proxy.policy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ServiceBroker {
	
	private Hashtable<String, List<URL>> servicos;
	
	private HistoryServiceProxy lReplicatedServiceProxy ;
	
	public ServiceBroker() {
		servicos = new Hashtable<String, List<URL>>();
		List<URL> lista = new ArrayList<URL>();
		
		try {
			
			lista.add(new URL("http://localhost:8080/Operacao/Operacao"));
			lista.add(new URL("http://localhost:38080/Operacao/Operacao"));
			lista.add(new URL("http://localhost:38081/Operacao/Operacao"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		servicos.put("services", lista);
		
		//Cria um serviço de escolha com 5 considerações no hitórico e com uma 
		//tolerância de serviços dos ultimos 3 segundos
		lReplicatedServiceProxy = new HistoryServiceProxy("Operacao","Somar", 10,30000L);
		((HistoryServiceProxy) lReplicatedServiceProxy).resetEndPointInfo(lista);
	}

	public URL chooseEndpoint(EndPointInfo endPointInfo) {
		
		URL endPoint = lReplicatedServiceProxy.chooseEndPoint(endPointInfo);
		
		return endPoint;
	}
	
	public void addEndPointInfo(EndPointInfo endPointInfo) {
		lReplicatedServiceProxy.addEndPointInfo(endPointInfo);
	}

}
