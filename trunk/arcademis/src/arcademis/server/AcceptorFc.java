package arcademis.server;

import arcademis.*;

/**
 * This interface determines the <CODE>Acceptor</CODE> factory. Whenever necessary to
 * modify the acceptor, it is sufficient to change the factory's implementation so it
 * pass to return the proper component.
 */
public interface AcceptorFc {

	/**
	 * Creates a default acceptor.
	 * @return an object of the <CODE>Acceptor</CODE> type.
	 */
	public Acceptor createAcceptor ();

	/**
	 * Creates a specific acceptor.
	 * @param objType defines the type of the acceptor that will be created.
	 * @return an object of the <CODE>Acceptor</CODE> type.
	 */
	public Acceptor createAcceptor (int objType);

	/**
	 * Creates a default acceptor bound to the given address point.
	 * @param epid the address in which the produced acceptor will wait for
	 * connections.
	 * @return an object of the <CODE>Acceptor</CODE> type.
	 */
	public Acceptor createAcceptor (Epid epid);
}
