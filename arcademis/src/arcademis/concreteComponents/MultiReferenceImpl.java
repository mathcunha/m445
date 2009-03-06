package arcademis.concreteComponents;

import arcademis.*;

/**
 * This class implements the concept of multi-reference, that is, a reference
 * to several remote objects, instead of just one. Such entity is meaningful
 * only in a over-provisioned environment, in which the clients could take
 * benefit of the several available servers.
 */
public class MultiReferenceImpl implements MultiReference {

	private InternalElement head = null;
	private InternalElement tail = null;
	private InternalElement curr = null;
	private int numElements = 0;

	/**
	 * Constructor method. Initializes the data structure that will hold the
	 * set of remote references.
	 */
	public MultiReferenceImpl() {
		this.initialize();
	}

	/**
	 * Adds a new reference to the set of remote references.
	 * @param r the remote reference that is been inserted into the list.
	 */
	public void attach(RemoteReference r) {
		this.tail.n = new InternalElement();
		this.tail = this.tail.n;
		this.tail.r = r;
		this.numElements++;
	}

	/**
	 * This method returns a reference to one of the remote references stored
	 * in the list. The following code can be used to get a reference to all
	 * elements stored into the list:
	 * <PRE>
	 * int i = mr.getNumberOfReferences();
	 * for(int j = 0; j < i; j++)
	 *     mr.nextReference()
	 * </PRE>
	 * @return an object of the <CODE>RemoreReference</CODE> type.
	 */
	public RemoteReference nextReference() {
		RemoteReference r = this.curr.n.r;
		this.curr = this.curr.n == this.tail ? this.head : this.curr.n;
		return r;
	}

	/**
	 * Removes the remote reference from the set of references.
	 * @param r the reference to be removed.
	 */
	public boolean removeReference(RemoteReference r) {
		InternalElement e = this.head;
		while(e.n != null)
			if(e.n.r.equals(r)) {
				if(this.tail == e.n)
					this.tail = e;
				e.n = e.n.n;
				numElements--;
				return true;
			} else
				e = e.n;
		return false;
	}

	/**
	 * Informs the number of elements stored in the reference list.
	 * @return an integer number representing the quantity of remote references
	 * stored in the list.
	 */
	public int getNumberOfReferences() {
		return numElements;
	}

	/**
	 * Fills the stream b with the byte sequence that describes this object.
	 * @throws MarshalException if it is not possible to serialize this object.
	 * @param the stream used in the serialization process.
	 */
	public void marshal(Stream b) throws MarshalException {
		b.write(this.numElements);
		for(InternalElement i = this.head.n; i != null; i = i.n)
			b.write((Marshalable)i.r);
	}

	/**
	 * Fills the content of this object with information retrived from a
	 * stream.
	 * @param the stream used in the serialization process.
	 */
	public void unmarshal(Stream b) throws MarshalException {
		this.initialize();
		for(int i = b.readInt(); i > 0; i--) {
			RemoteReference r = (RemoteReference)b.readObject();
			this.attach(r);
		}
	}

	/**
	 * Returns a textual representation of all the remote references stored in
	 * this data structure.
	 * @return an object of the <CODE>String</CODE> type that represents the
	 * contents of this object.
	 */
	public String toString() {
		String s = "";
		for(InternalElement i = this.head.n; i != null; i = i.n)
			s += i.r.toString() + '\n';
		return s;
	}

	private void initialize() {
		this.head = new InternalElement();
		this.tail = this.head;
		this.curr = this.tail;
		this.numElements = 0;
	}

	private class InternalElement {
		RemoteReference r = null;
		InternalElement n = null;
	}
}
