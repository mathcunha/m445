package arcademis.server;

/**
 * The factory of buffers. Buffers are used whenever necessary to organize data
 * into linear sequences.
 */
public interface BufferFc {

	/**
	 * Creates a default buffer.
	 * @return an object of the <CODE>Buffer</CODE> type.
	 */
	public Buffer createBuffer ();

	/**
	 * Creates a specific buffer, such as a stack, a queue, a dequeue, a skip list,
	 * etc.
	 * @param objType an integer that specifies the type of buffer that will be
	 * created.
	 * @return an object of the <CODE>Buffer</CODE> type.
	 */
	public Buffer createBuffer (int objType);
}
