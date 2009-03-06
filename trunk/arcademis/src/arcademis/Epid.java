package arcademis;

/**
 * This interface defines the location of remote objects according to the
 * Arcademis framework. Locations can be specified in several different ways,
 * for example, in RME locations are given by the pair host and port number. In
 * SOAP the location is given by an URL and a file name and path. In a shared
 * memory based middleware, the location could be defined as an address of
 * memory.
 */
public interface Epid extends Marshalable {

}