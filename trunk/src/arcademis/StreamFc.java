package arcademis;

/**
 * This interface determines the stream factory. Whenever necessary to
 * modify the implementation of this component, it is sufficient to change this
 * factory's implementation so it pass to return the proper implementation of
 * the component.
 */
public interface StreamFc {

	/**
	 * Creates a default stream.
	 * @return an object of the <CODE>Stream</CODE> type.
	 */
	public Stream createStream ();

	/**
	 * Creates a specific stream.
	 * @param objType defines the type of the stream that will be created.
	 * @return an object of the <CODE>Stream</CODE> type.
	 */
	public Stream createStream (int objType);

}
