package arcademis;


/**
 * The remote reference represents an object that does not necessarily exist
 * in the same address space than the reference itself. This class holds all
 * the necessary information for communicating with the remote object, and it
 * also defines the semantic of operations such as equals or toString, that
 * every object owns. This class represents one of the few concrete components
 * provided by Arcademis.
 */
public class RemoteReference implements Marshalable {

	private Epid epid = null;

	private Identifier id = null;


	/**
	 * Constructor method. Generates a new remote reference with the given
	 * location and identifier.
	 * @param epid the location of the remote reference to be instantiated.
	 * @param id the identifier of the remote reference. It should be the same
	 * identifier as that used by the remote object this reference points to.
	 */
	public RemoteReference(Epid epid, Identifier id) {
		this.epid = epid;
		if(id == null)
			this.id = OrbAccessor.getIdentifier();
		else
			this.id = id;
	}

	/**
	 * Constructor method. Creates a new remote reference with the given location
	 * and with a random identifier.
	 * @param epid the location of the remote reference to be instantiated.
	 */
	public RemoteReference(Epid epid) {
		this.epid = epid;
		this.id = OrbAccessor.getIdentifier();
	}

	/**
	 * Creates a remote reference that does not have neither an actual location
	 * nor the identifier of a remote object. These attributes will have to be
	 * set after the reference creation.
	 */
	public RemoteReference() {
		this.epid = OrbAccessor.getEpid();
		this.id = OrbAccessor.getIdentifier();
	}

	/**
	 * Compares the identifiers of the remote references.
	 * @return true if the remote reference have equal identifiers. False
	 * otherwise.
	 */
	public boolean equals(Object r) {
		if(!(r instanceof RemoteReference))
			return false;
		else
			return this.id.equals(((RemoteReference)r).id);
	}

	/**
	 * Informs the identifier of the remote reference.
	 * @return an object of the <CODE>Identifier</CODE> type.
	 */
	public Identifier getIdentifier() {
		return id;
	}

	/**
	 * Informs the location of the remote object pointed by this reference.
	 * @return an object of the <CODE>Epid</CODE> type.
	 */
	public Epid getEpid() {
		return epid;
	}

	/**
	 * Fills the stream b with the byte sequence that describes this object. The
	 * information to be serialized includes the end point identifier and the
	 * unique identifier of this reference.
	 * @throws MarshalException if it is not possible to serialize this object.
	 * @param the stream used in the serialization process.
	 */
	public void marshal(Stream b) throws MarshalException {
		b.write(epid);
		b.write(id);
	}

	/**
	 * Fills the content of this object with information retrived from a
	 * stream.
	 * @param the stream used in the serialization process.
	 */
	public void unmarshal(Stream b) throws MarshalException {
		epid = (Epid)b.readObject();
		id = (Identifier)b.readObject();
	}

	/**
	 * Returns a textual representation of this remote reference.
	 * @return an object of the <CODE>String</CODE> type that represents the
	 * contents of this object, what includes its location and identifier.
	 */
	public String toString() {
		return "Remote reference:" +
		"\nEpid: " + epid.toString() +
		"\nIdentifier: " + id.toString();
	}
}
