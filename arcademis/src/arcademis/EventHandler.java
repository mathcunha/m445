package arcademis;

/**
 * The interface EventHandler, together with the abstract class Event,
 * compounds the framework of event handling in Arcademis. In Arcademis, events
 * are handled according to the reactor pattern.
 * While the event class is responsible for notifing the occurrence of the
 * event, the event handler is responsible for dealing with the notified events.
 */
public interface EventHandler {

	/**
	 * Constructor method. It assigns the given event with this event handler.
	 * Whenever that event takes place, this event handler will be invoked to deal
	 * with it.
	 * @param e the event to be handled.
	 */
    public void handleEvent(Event e);

}
