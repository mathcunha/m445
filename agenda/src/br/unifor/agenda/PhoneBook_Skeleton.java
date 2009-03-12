package br.unifor.agenda;

import java.lang.reflect.Method;

import rme.RmeRemoteCall;
import rme.RmeStream;
import rme.RmeStreamFc;
import arcademis.MarshalException;
import arcademis.RemoteCall;
import arcademis.Stream;
import arcademis.server.Skeleton;

public class PhoneBook_Skeleton extends Skeleton {

	@Override
	public Stream dispatch(RemoteCall remoteCall) throws Exception {
		System.out.println("Achou!");
		StringBuffer lmetodo = new StringBuffer();
		Object[] argumentos = obterMetodo(remoteCall, lmetodo);
		String metodo = lmetodo.toString();
		
		System.out.println("Metodo "+metodo +", parametro ");
		
		
		PhoneBook lPhoneBook = (PhoneBook)super.getRemoteObject();
		
		Method method = PhoneBook.class.getDeclaredMethod(metodo, argumentos[0].getClass());
		Pessoa lPessoa = (Pessoa) method.invoke(lPhoneBook, argumentos);
		
		
		
		Stream b = (new RmeStreamFc()).createStream();
		
		lPessoa.marshal(b);
		
		return b;
	}

	private Object[] obterMetodo(RemoteCall remoteCall, StringBuffer metodo) throws MarshalException {
		RmeRemoteCall lRemoteCall = (RmeRemoteCall) remoteCall;
		
		RmeStream lStream = (RmeStream) lRemoteCall.getArguments();
		
		
		metodo.append((String)lStream.readObject());
		
		int parametros = lStream.readInt();
		
		Object argumentos[] = new Object[parametros];
		for (int i = 0; i < parametros; i++) {
			
			argumentos[i] = lStream.readObject();
		}
		return argumentos;
	}

}
