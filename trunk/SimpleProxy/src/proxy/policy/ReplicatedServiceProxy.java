package proxy.policy;

import java.net.URL;
import java.util.List;



public interface ReplicatedServiceProxy {

	List<URL> getEndpointLst();
	
	void setEndpointLst(List<URL> list);
	
	URL chooseEndpoint();
}
