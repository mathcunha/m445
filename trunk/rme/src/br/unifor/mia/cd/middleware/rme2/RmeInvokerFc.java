package br.unifor.mia.cd.middleware.rme2;

import arcademis.*;
import br.unifor.mia.cd.middleware.rme2.extras.*;

public class RmeInvokerFc implements InvokerFc, RmeConstants {

	/**
	 * Creates a simple invoker. This is the default method of invoker
	 * instantiation.
	 * @return an object of the <CODE>Invoker</CODE> type.
	 */
	public Invoker createInvoker() {
		return new TwoWayInvoker();
	}

	/**
	 * Produces a new invoker based on the descriptor that is given to the
	 * factory.
	 * @return an object of the <CODE>Invoker</CODE> type.
	 */
	public Invoker createInvoker (int objType) {
		Invoker invoker = new TwoWayInvoker();
		if((objType & ONE_WAY) != 0)
			invoker = new AssynchronousInvoker(invoker);
		if((objType & CACHED_CALL) != 0)
			invoker = new CachedInvoker(invoker);
		return invoker;
	}

	/**
	 * Empty body.
	 * @param d an object of the <CODE>InvokerDecorator</CODE> type.
	 */
	public void insertDecorator(InvokerDecorator d) {}

	/**
	 * Empty body
	 */
	public void removeDecorators() {}

	/**
	 * Empty body.
	 * @param invoker the new base component.
	 */
	public void setBaseComponent(Invoker invoker) {}
}
