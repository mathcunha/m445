package rme.extras.inquire;

import arcademis.*;

public class LoadMsg implements Message {
	Stream load = null;

	public void setLoad(Stream l) {
		load = l;
	}

	public Stream getLoad() {
		return this.load;
	}

	public void marshal(Stream b) throws MarshalException {
		// send header: RMEP
		b.write(0x524D4550);
		// send protocol version:
		b.write((byte) 0x01);
		// send load value
		b.append(load);
	}

	public void unmarshal(Stream b) throws MarshalException {
		int header = b.readInt();
		if (header != 0x524D4550)
			throw new MarshalException();

		byte version = b.readByte();
		if (version != (byte) 0x01)
			throw new MarshalException();

		this.load = b;
	}

	/**
	 * Do nothing. Normally because the service handler that received this
	 * message already do all the processing that is necessary to handle the
	 * information the message carries.
	 * 
	 * @param s
	 *            an object of the <CODE>ProtocolHandler</CODE> type.
	 */
	public void execute(ProtocolHandler s) {
	}
}