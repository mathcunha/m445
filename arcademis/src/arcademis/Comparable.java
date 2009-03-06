package arcademis;

/**
 * This interface characterizes the components of Arcademis that can be
 * ordered with respect a certain criterion.
 */
public interface Comparable {
	/**
	 * Compares this object with another one.
	 * @param o the object that is going to be compared againt this one.
	 * @return an integer value that is zero if the both objects are equal with
	 * respect to the comparation criterion, negative if the parameter is
	 * considered bigger than this object and positive of this object is greater
	 * than the given object.
	 */
    public int compareTo(Object o);
}
