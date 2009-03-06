package arcademis.server;

public interface SchedulerFc {

	/**
	 * Creates a default scheduler.
	 * @return an object of the <CODE>Scheduler</CODE> type.
	 */
	public Scheduler createScheduler ();

	/**
	 * Creates a specific scheduler.
	 * @param objType an integer that specifies the type of Scheduler that will be
	 * created.
	 * @return an object of the <CODE>Scheduler</CODE> type.
	 */
	public Scheduler createScheduler (int objType);
}
