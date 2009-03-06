package arcademis;

/**
 * This interface characterizes the configurator components. Such components
 * are responsible for determining the structure of the ORB, that is, the
 * factories that will be used for the creation of the middleware components.
 */
public interface Configurator {
	/**
	 * This method associates a set of factories with the ORB; therefore, it
	 * determines the overall behavior of the middleware instance that is being
	 * generated.
	 * @throws ReconfigurationException in the case the ORB can not be configured
	 * anymore. Such situations happens if the ORB has being already configured
	 * and a new attempt of reconfiguration is made.
	 */
    public void configure() throws ReconfigurationException;
}
