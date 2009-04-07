package proxy.policy;

public class EndPoint {

	public EndPoint(String hostName, Integer port) {
		this.hostName = hostName;
		this.port = port;
	}

	private String hostName;
	private Integer port;

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}
