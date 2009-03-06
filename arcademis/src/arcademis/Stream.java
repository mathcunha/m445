package arcademis;

/**
 * The implementation of the <CODE>Stream</CODE> interface determines the
 * serialization protocol adopted by the middleware platform. Every instance of
 * the <CODE>Marshalable</CODE> interface can use streams in order to save its
 * internal state as a sequence of bytes, and to carry on the inverse process:
 * to fill its attributes with information retrieved from a stream.
 * Essentially, every stream encapsulates a sequence of bytes, but Arcademis
 * does not commites the middleware developer to adopt any particular
 * implementation.
 */
public interface Stream extends Marshalable {

	/**
	 * Writes a <CODE>boolean</CODE> value into the byte stream.
	 * @param b the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(boolean b) throws MarshalException;

	/**
	 * Writes a <CODE>char</CODE> value into the byte stream.
	 * @param c the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(char c) throws MarshalException;

	/**
	 * Writes a <CODE>byte</CODE> value into the byte stream.
	 * @param b the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(byte b) throws MarshalException;

	/**
	 * Writes a <CODE>short</CODE> value into the byte stream.
	 * @param s the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(short s) throws MarshalException;

	/**
	 * Writes an <CODE>int</CODE> value into the byte stream.
	 * @param i the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(int i) throws MarshalException;

	/**
	 * Writes a <CODE>long</CODE> value into the byte stream.
	 * @param l the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(long l) throws MarshalException;

	/**
	 * Writes a <CODE>String</CODE> value into the byte stream.
	 * @param s the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(String s) throws MarshalException;

	/**
	 * Writes an <CODE>Exception</CODE> value into the byte stream.
	 * @param e the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(Exception e) throws MarshalException;

	/**
	 * Writes a <CODE>Marshalable</CODE> value into the byte stream.
	 * @param m the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(Marshalable m) throws MarshalException;

	/**
	 * Writes an array of <CODE>boolean</CODE> into the byte stream.
	 * @param a the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(boolean[] a) throws MarshalException;

	/**
	 * Writes an array of <CODE>char</CODE> into the byte stream.
	 * @param a the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(char[] a) throws MarshalException;

	/**
	 * Writes an array of <CODE>byte</CODE> into the byte stream.
	 * @param a the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(byte[] a) throws MarshalException;

	/**
	 * Writes an array of <CODE>short</CODE> into the byte stream.
	 * @param a the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(short[] a) throws MarshalException;

	/**
	 * Writes an array of <CODE>int</CODE> into the byte stream.
	 * @param a the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(int[] a) throws MarshalException;

	/**
	 * Writes an array of <CODE>long</CODE> into the byte stream.
	 * @param a the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(long[] a) throws MarshalException;

	/**
	 * Writes an array of <CODE>String</CODE> into the byte stream.
	 * @param a the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(String[] a) throws MarshalException;

	/**
	 * Writes an array of <CODE>Marshalable</CODE> objects into the byte stream.
	 * @param a the value to be written.
	 * @throws MarshalException if something goes wrong in the serialization
	 * process.
	 */
	public void write(Marshalable[] a) throws MarshalException;

	/**
	 * Reads a <CODE>boolean</CODE> value from the byte stream.
	 * @return a <CODE>boolean</CODE> value.
	 * @throws MarshalException in the case something goes wrong during the
	 * serialization process.
	 */
	public boolean readBoolean() throws MarshalException;

	/**
	 * Reads a <CODE>char</CODE> value from the byte stream.
	 * @return a <CODE>char</CODE> value.
	 * @throws MarshalException in the case something goes wrong during the
	 * serialization process.
	 */
	public char readChar() throws MarshalException;

	/**
	 * Reads a <CODE>byte</CODE> value from the byte stream.
	 * @return a <CODE>byte</CODE> value.
	 * @throws MarshalException in the case something goes wrong during the
	 * serialization process.
	 */
	public byte readByte() throws MarshalException;

	/**
	 * Reads a <CODE>short</CODE> value from the byte stream.
	 * @return a <CODE>short</CODE> value.
	 * @throws MarshalException in the case something goes wrong during the
	 * serialization process.
	 */
	public short readShort() throws MarshalException;

	/**
	 * Reads an <CODE>int</CODE> value from the byte stream.
	 * @return an <CODE>int</CODE> value.
	 * @throws MarshalException in the case something goes wrong during the
	 * serialization process.
	 */
	public int readInt() throws MarshalException;

	/**
	 * Reads a <CODE>long</CODE> value from the byte stream.
	 * @return a <CODE>long</CODE> value.
	 * @throws MarshalException in the case something goes wrong during the
	 * serialization process.
	 */
	public long readLong() throws MarshalException;

	/**
	 * Reads a marshalable <CODE>Object</CODE> from the byte stream.
	 * @return a marshalable <CODE>Object</CODE> value.
	 * @throws MarshalException in the case something goes wrong during the
	 * serialization process.
	 */
	public Object readObject() throws MarshalException;

	/**
	 * Gives acess to the sequence of bytes stored by the stream.
	 * @return an array of bytes.
	 */
	public byte[] getBytes();

	/**
	 * Fills the content of the stream with the given sequence of bytes.
	 * @param b an array of bytes that will fill the stream.
	 */
	public void fill(byte[] b);

	/**
	 * Fills the content of the stream with the content of the given stream.
	 * @param b the stream that holds the content to be passed away.
	 */
	public void fill(Stream b);

	/**
	 * Appends the content of the given stream at the end of the current stream.
	 * @param b the stream that holds the byte sequence to be appended.
	 */
	public void append(Stream b);

	/**
	 * Removes the content of the stream.
	 */
	public void clean();

	/**
	 * Informs the size of the stream.
	 * @return an integer number.
	 */
	public int size();

	/**
	 * Informs if the stream holds an exception.
	 * @return a boolean value that is true if the stream holds an exception and
	 * false otherwise.
	 */
	public boolean isException();
}
