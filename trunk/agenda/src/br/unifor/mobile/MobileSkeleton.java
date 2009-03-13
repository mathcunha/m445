package br.unifor.mobile;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.unifor.agenda.PhoneBook;
import rme.RmeRemoteCall;
import rme.RmeStream;
import arcademis.OrbAccessor;
import arcademis.RemoteCall;
import arcademis.Stream;
import arcademis.server.Skeleton;

public class MobileSkeleton extends Skeleton {
	@Override
	public Stream dispatch(RemoteCall remoteCall) throws Exception {

		RmeStream rStream = (RmeStream) OrbAccessor.getStream();
		RmeStream stream = (RmeStream) ((RmeRemoteCall) remoteCall)
				.getArguments();
		String nomeMetodo = (String) stream.readObject();
		int totalParametros = stream.readInt();

		Class argumentTypes[] = new Class[totalParametros];
		Object arguments[] = new Object[totalParametros];

		for (int i = 0; i < totalParametros; i++) {
			arguments[i] = stream.readObject();
			argumentTypes[i] = arguments[i].getClass();
		}

		MobileRemoteObject lMobileRemoteObject = (MobileRemoteObject) super
				.getRemoteObject();
		Method method = null;
		if (!nomeMetodo.equals("getRemoteState")) {
			method = lMobileRemoteObject.getClass().getMethod(nomeMetodo,
					argumentTypes);

			callMethod(rStream, lMobileRemoteObject, method, arguments);
		} else {
			lMobileRemoteObject.getState(rStream);
		}

		return rStream;
	}

	public void callMethod(Stream stream,
			MobileRemoteObject mobileRemoteObject, Method method,
			Object arguments[]) {

	}

	/*
	 * private void callMethod(Stream stream, MobileRemoteObject
	 * mobileRemoteObject, Method method, Object arguments[]) { try {
	 * 
	 * if (method.getName().equals("getRemoteState")) {
	 * method.invoke(mobileRemoteObject, arguments);
	 * mobileRemoteObject.getState(stream); } else { callChildMethod(stream,
	 * mobileRemoteObject, method, arguments); } } catch
	 * (IllegalArgumentException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IllegalAccessException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } catch
	 * (InvocationTargetException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * public void callChildMethod(Stream stream, MobileRemoteObject
	 * mobileRemoteObject, Method method, Object arguments[]) {
	 * 
	 * }
	 */

}
