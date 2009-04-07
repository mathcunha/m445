package proxy.policy;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ServiceBroker {
	
	private Hashtable<String, List<URL>> servicos;
	
	public ServiceBroker() throws MalformedURLException{
		servicos = new Hashtable<String, List<URL>>();
		List<URL> lista = new ArrayList<URL>();
		
		lista.add(new URL("http://127.0.0.1:8080"));
		servicos.put("services", lista);
	}

	public EndPoint chooseEndpoint(String service) {
		
		ReplicatedServiceProxy lReplicatedServiceProxy = new ReplicatedServiceProxy_Impl();
		
		lReplicatedServiceProxy.setEndpointLst(servicos.get(service));
		
		URL endPoint = lReplicatedServiceProxy.chooseEndpoint();
		
		return new EndPoint(endPoint.getHost(), endPoint.getPort());
	}

}
