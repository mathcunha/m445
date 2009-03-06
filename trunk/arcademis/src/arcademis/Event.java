package arcademis;

/**
 * This class is the notifier provided by Arcademis for perceiving events.
 * Events can be programmed by the application developer. Exemplos of events
 * are the connection establishment, the passing of time, etc.
 * In Arcademis, Events are programmed according to the reactor pattern.
 */
public abstract class Event {

    /**
     * Every event is uniquely identified by a <code>Identifier</code>
     */
    private Identifier uid;

    /**
     * When the event takes place, it handler must be invoked
     */
    protected EventHandler eventHandler;

	/**
	 * Constructor method. Associates this event with a given event handler.
	 * @param eventHandler the event handler that will be invoked if this event
	 * takes place.
	 */
    public  Event(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
        this.uid = OrbAccessor.getIdentifier();
    }

	/**
	 * The handle is an object that describes the event that took place. For
	 * example, considering the event of connection establishment, the channel
	 * established after its occurrence can be used as a handle for that event.
	 * @return an object that represents the handle.
	 */
    public abstract Object getHandle();

	/**
	 * The notifier starts probing the occurrence of events after it is explicitly
	 * initialized.
	 */
    public abstract void initiate();

	/**
	 * The event handler is an object that is able to deal with an event. For
	 * example, the
	 * connector could be an event handle for addressing the event of connection
	 * establishment.
	 * @return an object that represents the event handler.
    public EventHandler getEventHandler() {
        return eventHandler;
    }

	/**
	 * This method compares this event with another object. All the events have a
	 * unique identifier. Events are considered equal if they have the same
	 * identififier.
	 * @param an object that represents the event to be compared.
	 * @return a boolean value that is true if the both events are equal or false
	 * otherwise.
	 */
    public boolean equals(Object obj) {
        if(!(obj instanceof Event))
            return false;
        else
            return this.uid.equals(((Event)obj).uid);
    }
}
