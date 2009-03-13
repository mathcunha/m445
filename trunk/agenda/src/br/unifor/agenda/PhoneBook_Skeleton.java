package br.unifor.agenda;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.unifor.mobile.MobileRemoteObject;
import br.unifor.mobile.MobileSkeleton;

import rme.RmeRemoteCall;
import rme.RmeStream;
import rme.RmeStreamFc;
import arcademis.MarshalException;
import arcademis.RemoteCall;
import arcademis.Stream;
import arcademis.server.Skeleton;

public class PhoneBook_Skeleton extends MobileSkeleton {

	@Override
	public void callMethod(Stream stream,
			MobileRemoteObject mobileRemoteObject, Method method,
			Object[] arguments) {
		Pessoa lPessoa = null;
		try {
			PhoneBook lPhoneBook = (PhoneBook)super.getRemoteObject();
			lPessoa = (Pessoa) method.invoke(lPhoneBook, arguments);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lPessoa.marshal(stream);
	}

	/*
	 * public Stream dispatch(RemoteCall remoteCall) throws Exception {
	 * System.out.println("Achou!"); StringBuffer lmetodo = new StringBuffer();
	 * Object[] argumentos = obterMetodo(remoteCall, lmetodo); String metodo =
	 * lmetodo.toString();
	 * 
	 * System.out.println("Metodo "+metodo +", parametro ");
	 * 
	 * 
	 * PhoneBook lPhoneBook = (PhoneBook)super.getRemoteObject();
	 * 
	 * Method method = PhoneBook.class.getDeclaredMethod(metodo,
	 * argumentos[0].getClass()); Pessoa lPessoa = (Pessoa)
	 * method.invoke(lPhoneBook, argumentos);
	 * 
	 * 
	 * 
	 * Stream b = (new RmeStreamFc()).createStream();
	 * 
	 * lPessoa.marshal(b);
	 * 
	 * return b; }
	 * 
	 * private Object[] obterMetodo(RemoteCall remoteCall, StringBuffer metodo)
	 * throws MarshalException { RmeRemoteCall lRemoteCall = (RmeRemoteCall)
	 * remoteCall;
	 * 
	 * RmeStream lStream = (RmeStream) lRemoteCall.getArguments();
	 * 
	 * 
	 * metodo.append((String)lStream.readObject());
	 * 
	 * int parametros = lStream.readInt();
	 * 
	 * Object argumentos[] = new Object[parametros]; for (int i = 0; i <
	 * parametros; i++) {
	 * 
	 * argumentos[i] = lStream.readObject(); } return argumentos; }
	 */

}
