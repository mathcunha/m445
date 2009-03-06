package arcademis.concreteComponents;

import arcademis.*;

/**
 * This class implements an identifier that is unique in a distributed
 * environment.
 * The class use three attributes to assure the uniqueness of each of its
 * instances:
 * <CODE>unique</CODE>: unique in the same host. This value is generated at
 * random; thus, there is a small possibility in the order of 1/2<SUP>32</SUP>
 * that two different virtual machine will generate the value for this argument.
 * <CODE>time</CODE>: the time when the id has been generated.
 * <CODE>count</CODE>: a counter that is used to discriminate identifiers
 * generated in the same machine at the same time.
 */
public class HostTimeCountId implements Identifier {

    private static long  ONE_SECOND = 1000; // in milliseconds
    private static final int hostUnique = (new Object()).hashCode();

    private static final Object lock = new Object();
    private static long lastTime = System.currentTimeMillis();
    private static short lastCount = Short.MIN_VALUE;

    /**
     * number that uniquely identifies the VM that this <code>UID</code>
     * was generated in with respect to its host and at the given time
     * @serial
     */
    private int unique;

    /**
     * a time (as returned by {@link System#currentTimeMillis()}) at which
     * the VM that this id was generated in was alive
     */
    private long time;

    /**
     * 16-bit number to distinguish <code>UID</code> instances created
     * in the same VM with the same time value
     * @serial
     */
    private short count;

	/**
	 * Constructor method. Generates a new <CODE>HostTimeCountId</CODE> identifier
	 * unique in the distributed system.
	 */
	public HostTimeCountId() {
		unique = hostUnique;

		synchronized (lock) {
	    	if (lastCount == Short.MAX_VALUE) {
				boolean done = false;
				while (!done) {
				    long now = System.currentTimeMillis();
				    if (now < lastTime + ONE_SECOND) {
						// pause for a second to wait for time to change
						try {
						    Thread.sleep(ONE_SECOND);
						} catch (java.lang.InterruptedException e) {}
						continue;
			    	} else {
						lastTime = now;
						lastCount = Short.MIN_VALUE;
						done = true;
		    		}
				}
		    }
		    time = lastTime;
		    count = lastCount++;
		}
	}

	/**
	 * Constructor method. Generates a new <CODE>HostTimeCountId</CODE> identifier
	 * that maybe not unique in the distributed system. Two instances of this class
	 * created by this constructor method are equal if they were given the same value
	 * as a parameter.
	 * @param num a short value that will discriminate the objects created by this
	 * method.
	 */
	public HostTimeCountId(short num){
		unique = 0;
		time = 0;
		count = num;
	}

	/**
	 * Determines if the given object is of the same type and holds the same content
	 * than this one.
	 * @param the object to be compared againt this.
	 * @return a <CODE>boolean</CODE> value that is true if the both objects are
	 * equal and false otherwise.
	 */
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof HostTimeCountId)) {
		    HostTimeCountId uid = (HostTimeCountId)obj;
	    	return (unique == uid.unique && count == uid.count && time == uid.time);
		} else {
		    return false;
		}
	}

	/**
	 * Returns the hash code of this object.
	 * @return an integer value.
	 */
    public int hashCode() {
		return (int) time + (int) count;
    }

	/**
	 * Returns a textual representation of this component.
	 * @return a <CODE>String</CODE> component.
	 */
    public String toString() {
		return	Integer.toString(unique,16) + ":" +
		    	Long.toString(time,16) + ":" +
	    		Integer.toString(count,16);
    }


	/**
	 * Compares this identifier againt another one. If the given object is not an
	 * instance of the <CODE>HostTimeCountId</CODE> class, a value of zero is
	 * returned.
	 * @return an integer value that is greater than zero if this object is considered
	 * greater than the given one, zero if they are considered the same and smaller
	 * than zero is the given object is considered greater. The first criterion for
	 * determining if an identifier is greater than other is the time in which they
	 * were instantiated, than the value of the host identifier and finally the value
	 * of the object.
	 */
	public int compareTo(Object obj) {
		if ((obj != null) && (obj instanceof HostTimeCountId)) {
			if(this.time != ((HostTimeCountId)obj).time)
				return (int)(this.time - ((HostTimeCountId)obj).time);
			else if(this.count != ((HostTimeCountId)obj).count)
				return this.count - ((HostTimeCountId)obj).count;
			else if(this.unique != ((HostTimeCountId)obj).unique)
				return this.unique - ((HostTimeCountId)obj).unique;
		}
		return 0;
	}

	/**
	 * Fills the stream b with the byte sequence that describes this address.
	 * @param the stream used in the serialization process.
	 * @throws MarshalException if it is not possible to serialize this object.
	 */
    public void marshal(Stream b) {
    	try {
	    	b.write(unique);
	    	b.write(time);
	    	b.write(count);
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
    		this.unique = b.readInt();
    		this.time = b.readLong();
    		this.count = b.readShort();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
    }
}