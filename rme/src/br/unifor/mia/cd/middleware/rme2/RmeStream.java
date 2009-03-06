package br.unifor.mia.cd.middleware.rme2;

import arcademis.*;

/**
 * This class implements the serialization protocol used in Rme. This protocol
 * consists of the standards for writing primitives types and marshalable
 * objects. This class uses a buffer for storing the bytes. When the number of
 * bytes inserted into the byte stream reaches its capacity, a new buffer with
 * twice the capacity of the current buffer.
 */
public class RmeStream implements Stream, RmeConstants {

	private byte[] buffer = null;
	private int endOfBuffer = 0;
	private int startOfBuffer = 0;

	public static final int INITIAL_BUFFER_SIZE = 256;


	public RmeStream() {
		buffer = new byte[INITIAL_BUFFER_SIZE];
		endOfBuffer = startOfBuffer = 0;
	}


	/**
	 * Inspect the number of bytes that are stored in the byte stream.
	 * @return the number of bytes that can be read from this byte stream.
	 */
	public int size() {
		return endOfBuffer - startOfBuffer;
	}


	private void in(byte b) {
		if(endOfBuffer == buffer.length)
			reallocBuffer();
		buffer[endOfBuffer++] = b;
	}


	private byte out() {
		byte resp = buffer[startOfBuffer++];
		return resp;
	}

	public boolean equals(Object o) {
		if(!(o instanceof RmeStream))
			return false;
		else {
			int h1 = this.hashCode();
			int h2 = ((RmeStream)o).hashCode();
			return (h1 == h2);
		}
	}

	public int hashCode() {
		int hashcode = 0;
		for(int i = 0; i < this.size(); i++)
			hashcode = (hashcode + buffer[i]) % 2147483647;
		return hashcode;
	}

	/**
	 * Writes a byte into the byte stream.
	 */
	public void write(byte b) {
		in(b);
	}


	/**
	 * Reads a byte from the byte stream.
	 */
    public byte readByte() throws MarshalException{
		if(size() == 0)
			throw new MarshalException("No data available in buffer");
		return out();
    }


	/**
	 * Writes an array of Marshalable objects into the byte stream.
	 */
	public void write(Marshalable[] m) throws MarshalException {
		if(m == null)
			write(NULL_REF);
		else {
			write(MARSHALABLE_ARRAY);
			write((short)m.length);
			for(int i = 0; i < m.length; i++)
				write(m[i]);
		}
	}


	/**
	 * Writes a string into the byte stream.
	 */
    public void write(String[] s) {
    	if(s == null)
    		write(NULL_REF);
    	else {
    		write(STRING_ARRAY);
    		write((short)s.length);
    		for(int i = 0; i < s.length; i++)
    			write(s[i]);
    	}
    }


	/**
	 * Writes a marshalable object into the byte stream.
	 */
	public void write(Marshalable m) throws MarshalException {
		if(m == null)
			write(NULL_REF);
		else {
			if(m instanceof Exception)
				write(MARSHALABLE_EXCEPTION);
			else
				write(MARSHALABLE);
			if(m instanceof arcademis.server.RemoteObject) {
				Stub s = ((arcademis.server.RemoteObject)m).getStub();
				write(s.getClass().getName());
				s.marshal(this);
			} else {
				write(m.getClass().getName());
				m.marshal(this);
			}
		}
	}


	/**
	 * Writes an exception into the byte stream.
	 */
	public void write(Exception e) throws MarshalException {
		if(e == null)
			write(NULL_REF);
		else {
			if(e instanceof Marshalable)
				write((Marshalable)e);
			else
				write(NON_MARSHALABLE_EXCEPTION);
			write(e.getClass().getName());
		}
	}



	/**
	 * Writes a boolean value into the byte stream.
	 */
	public void write(boolean b) {
		if(b)
			in((byte)1);
		else
			in((byte)0);
    }


    public boolean readBoolean() throws MarshalException{
		if(size() == 0)
			throw new MarshalException("No data available in buffer");
		byte b = out();
		if(b == 0)
			return false;
		else
			return true;
    }


	/**
	 * Writes a char value into the byte stream.
	 */
    public void write(char c) {
		int i = (int)c;
		in((byte)(i & 255));
		in((byte)((i >> 8) & 255));
    }


    public char readChar() throws MarshalException{
		if(size() < 2)
			throw new MarshalException("No data available in buffer");
		short s = (short)out();
		byte aux = out();
		s = (short)(s | (aux << 8));
		return (char)s;
	}


	/**
	 * Writes a short value into the byte stream.
	 */
    public void write(short s) {
		in((byte)((s >> 8) & 255));
		in((byte)(s & 255));
    }


	/**
	 * reads a long value from the byte stream.
	 */
    public short readShort() throws MarshalException{
		if(size() < 2)
			throw new MarshalException("No data available in buffer");
		int i2 = (out() << 8) & 0xFF00;
		int i1 = out() & 0xFF;
		short s = (short)(i1 + i2);
		return s;
	}


	/**
	 * Writes an integer value into the byte stream.
	 */
    public void write(int i) {
		in((byte)((i >> 24) & 255));
		in((byte)((i >> 16) & 255));
		in((byte)((i >> 8) & 255));
		in((byte)(i & 255));
    }


	/**
	 * reads an integer value from the byte stream.
	 */
    public int readInt() throws MarshalException{
		if(size() < 4)
			throw new MarshalException("No data available in buffer");
		int i4 = (out() << 24) & 0xFF000000;
		int i3 = (out() << 16) & 0xFF0000;
		int i2 = (out() << 8) & 0xFF00;
		int i1 = out() & 0xFF;
		return (i4 + i3 + i2 + i1);
    }


	/**
	 * Writes a long value into the byte stream.
	 */
    public void write(long l) {
		int i = (int)((l >> 32) & 0xFFFFFFFFL);
		write(i);
		i = (int)(l & 0xFFFFFFFFL);
		write(i);
    }


	/**
	 * reads a long value from the byte stream.
	 */
    public long readLong() throws MarshalException{
		if(size() < 8)
			throw new MarshalException("No data available in buffer");
		return ((long)(readInt()) << 32) + (readInt() & 0xFFFFFFFFL);
    }


	/**
	 * Writes a string value into the byte stream.
	 */
    public void write(String s) {
    	if(s == null)
    		write(NULL_REF);
    	else {
    		write(STRING);
			write((short)s.length());
			for(int i = 0; i < s.length(); i++)
				in((byte)s.charAt(i));
    	}
    }


	/**
	 * Writes an array of boolean values into the byte stream.
	 */
    public void write(boolean[] a) {
   		write(BOOLEAN_ARRAY);
		write((short)a.length);
		for(int i = 0; i < a.length; i++)
			write(a[i]);
    }


	/**
	 * Writes an array of bytes into the byte stream.
	 */
    public void write(byte[] a) {
   		write(BYTE_ARRAY);
		write((short)a.length);
		for(int i = 0; i < a.length; i++)
			in(a[i]);
    }


	/**
	 * Writes an array of chars into the byte stream.
	 */
    public void write(char[] a) {
   		write(CHAR_ARRAY);
		write((short)a.length);
		for(int i = 0; i < a.length; i++)
			write(a[i]);
    }


	/**
	 * Writes an array of shorts into the byte stream.
	 */
    public void write(short[] a) {
   		write(SHORT_ARRAY);
		write((short)a.length);
		for(int i = 0; i < a.length; i++)
			write(a[i]);
    }


	/**
	 * Writes an array of integer values into the byte stream.
	 */
    public void write(int[] a) {
   		write(INT_ARRAY);
		write((short)a.length);
		for(int i = 0; i < a.length; i++)
			write(a[i]);
    }


	/**
	 * Writes an array of long values into the byte stream.
	 */
    public void write(long[] a) {
   		write(LONG_ARRAY);
		write((short)a.length);
		for(int i = 0; i < a.length; i++)
			write(a[i]);
    }


	/**
	 * Reads an object from the byte stream.
	 * @return an object that has been recovered from the byte sequence this
	 * stream encapsulates.
	 * @return MarshalException in the case something goes wrong in the
	 * desserialization process.
	 */
	public Object readObject() throws MarshalException {
		if(size() < 1)
			throw new MarshalException("No data available in buffer");
		byte b = readByte();
		switch(b) {
			case NULL_REF: {
				return null;
			}
			case MARSHALABLE: {
				return readMarshalable();
			}
			case STRING_ARRAY: {
				return readStringArray();
			}
			case STRING: {
				return readString();
			}
			case MARSHALABLE_ARRAY: {
				return readMarshalableArray();
			}
			case BOOLEAN_ARRAY: {
				return readBooleanArray();
			}
			case BYTE_ARRAY: {
				return readByteArray();
			}
			case CHAR_ARRAY: {
				return readCharArray();
			}
			case SHORT_ARRAY: {
				return readShortArray();
			}
			case INT_ARRAY: {
				return readIntArray();
			}
			case LONG_ARRAY: {
				return readLongArray();
			}
			case MARSHALABLE_EXCEPTION: {
				return readMarshalableException();
			}
			case NON_MARSHALABLE_EXCEPTION: {
				return readNonMarshalableException();
			}
			default:
				throw new MarshalException("Attempt to read an marshalable object or exception");
		}
	}


	private Marshalable readMarshalable() {
		try {
			String className = (String)this.readObject();
			Class c = Class.forName(className);
			Marshalable m = (Marshalable)c.newInstance();
			m.unmarshal(this);
			return m;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	private Exception readMarshalableException() {
		try {
			String className = (String)this.readObject();
			Class c = Class.forName(className);
			Exception e = (Exception)c.newInstance();
			((Marshalable)e).unmarshal(this);
			return e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	private Exception readNonMarshalableException() {
		try {
			String className = (String)this.readObject();
			Class c = Class.forName(className);
			Exception e = (Exception)c.newInstance();
			return e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public boolean isException() {
		return buffer[startOfBuffer] == MARSHALABLE_EXCEPTION ||
		buffer[startOfBuffer] == NON_MARSHALABLE_EXCEPTION;
	}

	private Marshalable[] readMarshalableArray() throws MarshalException{
		if(size() < 2)
			throw new MarshalException("No data available in buffer");
		short len = readShort();
		if(size() < len)
			throw new MarshalException("No data available in buffer. BufferSize = " + size() + " pending reads = " + len);
		Marshalable m[] = new Marshalable[len];
		for(int i = 0; i < len; i++)
			m[i] = (Marshalable)readObject();
		return m;
	}


    private String readString() throws MarshalException {
		if(size() < 2)
			throw new MarshalException("No data available in buffer");
		short len = readShort();
		if(size() < len)
			throw new MarshalException("No data available in buffer. BufferSize = " + size() + " pending reads = " + len);
		byte a[] = new byte[len];
		for(int i = 0; i < len; i++)
			a[i] = out();
		return new String(a);
    }


	private String[] readStringArray() throws MarshalException {
		if(size() < 2)
			throw new MarshalException("No data available in buffer");
		short len = readShort();
		if(size() < len)
			throw new MarshalException("No data available in buffer. BufferSize = " + size() + " pending reads = " + len);
		String s[] = new String[len];
		for(int i = 0; i < len; i++)
			s[i] = (String)readObject();
		return s;
	}


	private byte[] readByteArray() throws MarshalException {
		if(size() < 2)
			throw new MarshalException("No data available in buffer");
		short len = readShort();
		if(size() < len)
			throw new MarshalException("No data available in buffer. BufferSize = " + size() + " pending reads = " + len);
		byte b[] = new byte[len];
		for(int i = 0; i < len; i++)
			b[i] = readByte();
		return b;
	}


	private boolean[] readBooleanArray() throws MarshalException {
		if(size() < 2)
			throw new MarshalException("No data available in buffer");
		short len = readShort();
		if(size() < len)
			throw new MarshalException("No data available in buffer. BufferSize = " + size() + " pending reads = " + len);
		boolean b[] = new boolean[len];
		for(int i = 0; i < len; i++)
			b[i] = readBoolean();
		return b;
	}


	private char[] readCharArray() throws MarshalException {
		if(size() < 2)
			throw new MarshalException("No data available in buffer");
		short len = readShort();
		if(size() < len)
			throw new MarshalException("No data available in buffer. BufferSize = " + size() + " pending reads = " + len);
		char c[] = new char[len];
		for(int i = 0; i < len; i++)
			c[i] = readChar();
		return c;
	}


	private short[] readShortArray() throws MarshalException {
		if(size() < 2)
			throw new MarshalException("No data available in buffer");
		short len = readShort();
		if(size() < len)
			throw new MarshalException("No data available in buffer. BufferSize = " + size() + " pending reads = " + len);
		short s[] = new short[len];
		for(int i = 0; i < len; i++)
			s[i] = readShort();
		return s;
	}


	private int[] readIntArray() throws MarshalException {
		if(size() < 2)
			throw new MarshalException("No data available in buffer");
		short len = readShort();
		if(size() < len)
			throw new MarshalException("No data available in buffer. BufferSize = " + size() + " pending reads = " + len);
		int a[] = new int[len];
		for(int i = 0; i < len; i++)
			a[i] = readInt();
		return a;
	}


	private long[] readLongArray() throws MarshalException {
		if(size() < 2)
			throw new MarshalException("No data available in buffer");
		short len = readShort();
		if(size() < len)
			throw new MarshalException("No data available in buffer. BufferSize = " + size() + " pending reads = " + len);
		long a[] = new long[len];
		for(int i = 0; i < len; i++)
			a[i] = readLong();
		return a;
	}


	public byte[] getBytes() {
		byte[] resp = new byte[size()];
		for(int i = startOfBuffer, j = 0; i != endOfBuffer; i++, j++)
			resp[j] = buffer[i];
        return resp;
    }


	public void clean() {
		endOfBuffer = startOfBuffer = 0;
	}


	public void fill(byte[] b) {
		this.startOfBuffer = 0;
		this.endOfBuffer = b.length;
		this.buffer = b;
	}


	public void fill(Stream b) {
		this.startOfBuffer = ((RmeStream)b).startOfBuffer;
		this.endOfBuffer = ((RmeStream)b).endOfBuffer;
		this.buffer = ((RmeStream)b).buffer;
	}


	public void append(Stream b) {
		for(int i = ((RmeStream)b).startOfBuffer; i != ((RmeStream)b).endOfBuffer; i++)
			in(((RmeStream)b).buffer[i]);
	}

	public void marshal(Stream b) throws MarshalException {
		b.write(this.getBytes());
	}


	public void unmarshal(Stream b) throws MarshalException {
		this.fill((byte[])b.readObject());
	}


	private void reallocBuffer() {
		byte[] newBuffer = new byte[2 * size()];
		int j = 0;
		for(int i = startOfBuffer; i != endOfBuffer; i++, j++)
			newBuffer[j] = buffer[i];
		buffer = newBuffer;
	}
}
