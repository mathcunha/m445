package arcademis;

/**
 * In a distributed system it is necessary to provide components with unique
 * identifiers so that they can be addressed in a non-ambiguous way whenever
 * necessary. That is the function of identifiers in Arcademis.
 */
public interface Identifier extends Comparable, Marshalable {
	/**
	 * Returns a textual representation of this identifier.
	 * @return an object of the <CODE>String</CODE> type that represents the
	 * contents of this object.
	 */
    public String toString();

	/**
	 * Determines the hash code of this object. Identifiers with the same hash
	 * code should return true when compared for equality.
	 * @return an integer that represents the hash code of this object.
	 */
    public int hashCode();

	/**
	 * Compares the identifiers for equality.
	 * @return true if the identifiers are equal, that is, if they have the same
	 * hash code, and false otherwise.
	 */
    public boolean equals(Object o);
}
