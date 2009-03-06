package rme.naming;

import rme.RmeRemoteCall;
import arcademis.ArcademisException;
import arcademis.Marshalable;
import arcademis.OrbAccessor;
import arcademis.Remote;
import arcademis.RemoteCall;
import arcademis.Stream;
import arcademis.Stub;


public class RmeServiceDirectory_Impl_Skeleton extends arcademis.server.Skeleton {

	public Stream dispatch(RemoteCall r) throws Exception {

		RmeRemoteCall remoteCall = (RmeRemoteCall)r;
		int opCode = remoteCall.getOperationCode();
		Stream args = remoteCall.getArguments();;
		Stream returnStr = OrbAccessor.getStream();

		switch(opCode) {
			case 1: {
				String args1 = (String)args.readObject();
				Remote retValue = ((RmeServiceDirectory_Impl)super.remoteObject).lookup(args1);
				returnStr.write(retValue);
			}
			break;
			case 2: {
				String args1 = (String)args.readObject();
				Stub args2 = (Stub)args.readObject();
				((RmeServiceDirectory_Impl)super.remoteObject).bind(args1, args2);
				returnStr.write((Marshalable)null);
			}
			break;
			case 3: {
				String args1 = (String)args.readObject();
				((RmeServiceDirectory_Impl)super.remoteObject).unbind(args1);
				returnStr.write((Marshalable)null);
			}
			break;
			case 4: {
				String args1 = (String)args.readObject();
				Stub args2 = (Stub)args.readObject();
				((RmeServiceDirectory_Impl)super.remoteObject).rebind(args1, args2);
				returnStr.write((Marshalable)null);
			}
			break;
			case 5: {
				String[] retValue = ((RmeServiceDirectory_Impl)super.remoteObject).list();
				returnStr.write(retValue);
			}
			break;
			default: {
				throw new ArcademisException("Invalid operand in remote method request");
			}
		}	// end switch

		return returnStr;
	}	// end dispatch
}
