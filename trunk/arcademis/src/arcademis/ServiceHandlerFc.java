package arcademis;

import arcademis.server.*;

/**
 * This interface determines the service handler factory. Whenever necessary to
 * modify the implementation of these components, it is sufficient to change this
 * factory's implementation so it pass to return the desired component.
 */
public interface ServiceHandlerFc {

	/**
	 * This method creates a new service handler of a default type.
	 * @return an object of the <CODE>ServiceHandler</CODE> type.
	 */
    public ServiceHandler createServiceHandler();
 
	/**
	 * This method creates a specific service handler.
	 * @param objType this argument determines the type of the service handler to
	 * be produced.
	 * @return an object of the <CODE>ServiceHandler</CODE> type.
	 */
    public ServiceHandler createServiceHandler (int objType);

	/**
	 * This method creates a request sender handler of a default type.
	 * @return an object of the <CODE>RequestSender</CODE> type.
	 */
    public RequestSender createRequestSender();
    
	/**
	 * This method creates a request receiver of a default type.
	 * @return an object of the <CODE>RequestReceiver</CODE> type.
	 */
    public RequestReceiver createRequestReceiver();

	/**
	 * This method creates a response sender of a default type.
	 * @return an object of the <CODE>ResponseSender</CODE> type.
	 */
    public ResponseSender createResponseSender();

	/**
	 * This method creates a response receiver of a default type.
	 * @return an object of the <CODE>ResponseReceiver</CODE> type.
	 */
    public ResponseReceiver createResponseReceiver();
}
