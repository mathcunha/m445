package arcademis;

/**
 * The remote interface marks stubs and remote objects. According to the
 * Arcademis' specification, all stub and remote object classes should
 * implement this interface. Also interfaces that declare remote methods have
 * to implement this interface. It serves two main purposes. First, in the
 * serialization process, Remote objects are passed by reference instead of by
 * value. That is, whenever a remote object have to be sent across the network,
 * its stub is sent instead. The second purpose of the remote interface is to
 * provide clients, servers and location agencies with a common interface for
 * handling remote objects.
 */
public interface Remote extends Marshalable {}
