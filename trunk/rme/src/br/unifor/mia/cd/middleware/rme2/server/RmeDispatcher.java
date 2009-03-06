package br.unifor.mia.cd.middleware.rme2.server;

import br.unifor.mia.cd.middleware.rme2.RmeRemoteCall;
import arcademis.Identifier;
import arcademis.IncompatibleStubException;
import arcademis.MarshalException;
import arcademis.OrbAccessor;
import arcademis.RemoteCall;
import arcademis.RemoteReference;
import arcademis.Stream;
import arcademis.server.Dispatcher;
import arcademis.server.RemoteObject;
import arcademis.server.Skeleton;

/**
 * This dispatcher determines a very simple server. Every remote call is passed
 * to the skeleton directly. The call result is sent to the client
 * straighforwardly.
 */
public class RmeDispatcher extends Dispatcher {

	Skeleton skeleton = null;

	/**
	 * Determines the skeleton associated to this dispatcher.
	 * @param a skeleton.
	 */
	public void setSkeleton(Skeleton skeleton) {
		this.skeleton = skeleton;
	}

	/**
	 * This dispatcher takes the folowing actions:
	 * <OL>
	 * <LI> Check if the call comes from a valid stub, that is, a stub created
	 * by the same remote object this dispatcher encapsulates.
	 * <LI> Passes the remote call to the remote object if 1 is true.
	 * <LI> Receives the return of the call or an exception.
	 * <LI> If a exception is registered during the processing of the call, the
	 * exception is return insted of the return value.
	 * <LI> Return to the caller the obtained return value.
	 * </OL>
	 * Repasses the call to the skeleton.
	 */
	public Stream dispatch(RemoteCall call) {
		Stream returnValue = null;
		try {
			if(!comesFromValidStub((RmeRemoteCall)call)) {
				IncompatibleStubException ise = new IncompatibleStubException();
				if(returnValue == null)
					returnValue = OrbAccessor.getStream();
				returnValue.write((Exception)ise);
			} else {
				try {
					returnValue = this.skeleton.dispatch(call);
				} catch (Exception e) {
					if(returnValue == null)
						returnValue = OrbAccessor.getStream();
					returnValue.write(e);
				}
			}
		} catch (MarshalException me) {
			me.printStackTrace();
		}
		return returnValue;
	}

	/**
	 * This method verifies if the given MethodRequest has been issued by a stub
	 * that has been created by the same remote object that created the
	 * skeleton itself.
	 * @param m the remote call that will be checked.
	 * @return a boolean value that is true if the request was issued by a valid
	 * stub, that is, a stub that was created by the same remote object that
	 * produced the skeleton.
	 */
	public boolean comesFromValidStub(RmeRemoteCall rCall) {
		RemoteObject remoteObject = skeleton.getRemoteObject();
		RemoteReference ref = remoteObject.getRef();
		Identifier id = ref.getIdentifier();
		return id.equals(rCall.getTargetObjectIdentifier());
	}
}
