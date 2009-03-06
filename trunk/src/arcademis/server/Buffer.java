package arcademis.server;

import java.util.Enumeration;
import arcademis.BufferException;

/**
 * Buffers are used for the middleware in order to group data into linear
 * sequences, such as queues, stacks and heaps. For example, buffers may be
 * utilized to construct priority queues, so that remote method invocations
 * holding higher priorities are retrieved first.
 */
public interface Buffer {

	/**
	 * Insert the data into the buffer.
	 * @param o the object to be inserted.
	 * @throws BufferException if data cannot be successefully inserted into the
	 * buffer.
	 */
	public void in(Object o) throws BufferException;

	/**
	 * Retrives an object from the buffer.
	 * @return the next object to be retrieved.
	 * @throws BufferException if data cannot be successefully inserted into the
	 * buffer.
	 */
	public Object out() throws BufferException;

	/**
	 * Verifies if the given object can be found in the buffer.
	 * @param o the object that will be queried.
	 * @return a <CODE>boolean</CODE> value that is true if the given object can
	 * be found in the buffer and false otherwise.
	 */
    public boolean contains(Object o);

	/**
	 * Informs the number of components that can be found in the buffer.
	 * @return a <CODE>int</CODE> value.
	 */
    public int size();

	/**
	 * Informs the total capacity of the buffer. In order to know how many
	 * elements may be inserted into the buffer, it is sufficient to do
	 * <CODE>capacity()</CODE> - <CODE>size()</CODE>.
	 */
    public int capacity();

	/**
	 * Informs if there are data in the buffer.
	 * @return a <CODE>boolean</CODE> value that is true if there are no data
	 * in the buffer.
	 */
    public boolean isEmpty();

	/**
	 * Gives access to a list containing the elements of the buffer.
	 * @return an <CODE>Enumeration</CODE> holding all the buffers elements.
	 */
	public Enumeration getElements();
}