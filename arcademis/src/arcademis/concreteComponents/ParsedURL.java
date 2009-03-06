package arcademis.concreteComponents;

/**
 * This class do the parser of urls.
 */
public class ParsedURL {
	private String host = null;
	private int port = -1;
	private String name = null;


	/**
	 * Creates a new <CODE>ParsedURL</CODE> object that divides the given
	 * <CODE>String</CODE> into its constituents, such as protocol, host and port
	 * number. Each of them can be recovered afterwards.
	 * @param the <CODE>String</CODE> that contains the URL.
	 * @throws MalformedURLException if the URL is baddly formed.
	 */
	public ParsedURL (String str) throws MalformedURLException {
   		// looks for strange characters:
   		int numberOfSlashes = 0;
    	for(int i = 0; i < str.length(); i++) {
   			if(str.charAt(i) == '#' || str.charAt(i) == '@' || str.charAt(i) == '?')
    			throw new MalformedURLException("invalid character, " + str.charAt(i) + ", in URL name: " + str);
    		if(str.charAt(i) == '/') {
    			numberOfSlashes++;
    			if(numberOfSlashes > 1)
    				throw new MalformedURLException("Rme locator syntax: [machine][:port]{/}object-name");
    		}
    	}

		int indexOfSlash = str.indexOf('/');
		int indexOfColon = str.indexOf(':');

		if(indexOfColon != -1) {
			host = str.substring(0, indexOfColon);
			String portNumber = null;
			try {
				portNumber = str.substring(indexOfColon+1, indexOfSlash);
				port = Integer.parseInt(portNumber);
			} catch (NumberFormatException e) {
				throw new MalformedURLException("Invalid port number: " + portNumber);
			}
			name = str.substring(indexOfSlash+1, str.length());
		} else if (indexOfSlash != -1) {
			host = str.substring(0, indexOfSlash);
			name = str.substring(indexOfSlash+1, str.length());
		} else
			name = str;
	}


	/**
	 * Creates a new <CODE>ParsedURL</CODE> with the specified parameters.
	 * @param host the name of the host.
	 * @param port the number of the port specified by the URL.
	 * @param name the name of the resource described by this URL. It may be an
	 * object's name, for example.
	 */
	public ParsedURL(String host, int port, String name) {
    	this.host = host;
	    this.port = port;
	    this.name = name;
	}


	/**
	 * Returns a textual representation of this component.
	 * @return a <CODE>String</CODE> component.
	 */
	public String toString() {
		String result = "";
		if(host != null)
			result += host;
		if(port > -1)
			result += ":" + port;
		if(result.length() > 0)
			result += "/";
		result += name;
		return result;
	}

	/**
	 * Constructs an end point identifier from the contents of this object.
	 * @return an object of the <CODE>Epid</CODE> type.
	 */
	public HostPortEpid getEpid() {
		arcademis.EpidFc epFc = arcademis.ORB.getEpidFactory();
		HostPortEpid epid = (HostPortEpid)epFc.createEpid(arcademis.ArcademisConstants.HOST_PORT);
		epid.setHostName(host);
		epid.setPortNumber(port);
		return epid;
	}

	/**
	 * Informs the host name of this URL.
	 * @return an <CODE>String</CODE> object.
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Informs the resource's name of the URL. It can be, for example, the name
	 * of a remote object.
	 * @return an <CODE>String</CODE> object.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Informs the port number of the given url.
	 * @return an integer value.
	 */
	public int getPort() {
		return port;
	}
}