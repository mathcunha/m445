package proxy.policy;

import java.net.URL;
import java.util.List;

public interface ReplicatedServiceProxy {
	
	URL chooseEndPoint();
	
	void addEndPointInfo(EndPointInfo endPointInfo);
	
	void setEndPointList(List<URL> list);
}
