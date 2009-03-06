package arcademis.server;

/**
 * The factory of ativator components. These components determine how a remote
 * object is made ready for receaving remote calls.
 */
public interface ActivatorFc {

	/**
	 * Creates a default activator.
	 * 
	 * @return an object of the <CODE>Activator</CODE> type.
	 */
	public Activator createActivator();

	/**
	 * Creates a specific activator.
	 * 
	 * @return an object of the <CODE>Activator</CODE> type.
	 */
	public Activator createActivator(int objType);

}