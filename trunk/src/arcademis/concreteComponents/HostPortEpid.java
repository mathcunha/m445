package arcademis.concreteComponents;

import arcademis.*;

/**
 * This end point identifier implementation defines address as a pair where the first
 * element is the host name and the second element is the port number. That is the
 * general form of addresses used in the TCP/IP protocol.
 */
public class HostPortEpid implements Epid {

	private static int currentPortNumber = 0;

	private int portNumber = 0;
	private String hostName = null;

	/**
	 * Creates a new address that is bound to the port 0 and to the local host.
	 */
	public HostPortEpid() {
		this.portNumber = 0;
		this.hostName = "localhost";
	}

	/**
	 * Returns a textual representation of this component.
	 * @return a <CODE>String</CODE> component.
	 */
	public String toString(){
		return hostName + ":" + portNumber;
	}

	/**
	 * Determines if the given object is of the same type and holds the same content
	 * than this one.
	 * @param the object to be compared againt this.
	 * @return a <CODE>boolean</CODE> value that is true if the both objects are
	 * equal and false otherwise.
	 */
	public boolean equals(Object o) {
		if(!(o instanceof HostPortEpid))
			return false;
		else {
			boolean sameHost = (hostName.equals(((HostPortEpid)o).hostName));
			boolean samePort = (this.portNumber == ((HostPortEpid)o).portNumber);
			return sameHost && samePort;
		}
	}

	/**
	 * Returns the hash code of this object.
	 * @return an integer value.
	 */
	public int hashCode() {
		return portNumber + hostName.hashCode();
	}

	/**
	 * Informs the host name of this address.
	 * @return a <CODE>String</CODE> object.
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * Defines the host name of this object
	 * @param hostName the new host name of this address.
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * Informs the port number of this address.
	 * @return an integer value.
	 */
	public int getPortNumber() {
		return portNumber;
	}

	/**
	 * Defines the new port number of this object.
	 * @param portNumber the new port number value.
	 */
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	/**
	 * Fills the stream b with the byte sequence that describes this address.
	 * @param the stream used in the serialization process.
	 * @throws MarshalException if it is not possible to serialize this object.
	 */
	public void marshal(Stream b) {
		try {
			b.write(portNumber);
			b.write(hostName);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fills the content of this object with information retrived from a
	 * stream.
	 * @param the stream used in the serialization process.
	 */
	public void unmarshal(Stream b) {
		try {
			this.portNumber = b.readInt();
			this.hostName = (String)b.readObject();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ascribes a random port number in the interval from 1200 to 30000 to this
	 * object. The next assignments will be not performed randomly. The first
	 * assigned value will be used as a base for the next assignments. For
	 * example, if the first assignment gets the port number 1234, the next will
	 * get 1235, the next other, 1236, and so on.
	 */
	public void assignRandomPortNumber() {
		if(currentPortNumber == 0)
			currentPortNumber = randInt(1200, 30000);
		this.portNumber = currentPortNumber++;
	}

	/**
	 * Ascribes a random port number in the given interval to this object.
	 * @param min the initial value of the interval.
	 * @param max the end value of the interval. If <CODE>min</CODE> is greater
	 * than <CODE>max</CODE>, the <CODE>min</CODE> value is assign to the port
	 * number.
	 */
	public void assignRandomPortNumber(int min, int max) {
		if(min > max)
			this.portNumber = min;
		else
			this.portNumber = randInt(min, max);
	}

	private int randInt(int low, int high) {
		int i = low + (int) (java.lang.Math.random() * (high - low + 1));
		return i;
	}
}