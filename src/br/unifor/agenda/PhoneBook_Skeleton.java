package br.unifor.agenda;

import java.lang.reflect.Method;

import rme.RmeRemoteCall;
import rme.RmeStream;
import rme.RmeStreamFc;
import arcademis.RemoteCall;
import arcademis.Stream;
import arcademis.server.Skeleton;

public class PhoneBook_Skeleton extends Skeleton {

	@Override
	public Stream dispatch(RemoteCall remoteCall) throws Exception {
		System.out.println("Achou!");
		
		RmeRemoteCall lRemoteCall = (RmeRemoteCall) remoteCall;
		
		RmeStream lStream = (RmeStream) lRemoteCall.getArguments();
		
		
		String metodo = (String)lStream.readObject();
		String parametro = (String)lStream.readObject();
		
		System.out.println("Metodo "+metodo +", parametro "+parametro);
		
		
		PhoneBook lPhoneBook = (PhoneBook)super.getRemoteObject();
		
		Method method = PhoneBook.class.getDeclaredMethod(metodo, String.class);
		Pessoa lPessoa = (Pessoa) method.invoke(lPhoneBook, parametro);
		//System.out.print("Opa! "+lPessoa);
		
		
		Stream b = (new RmeStreamFc()).createStream();
		
		System.out.print("Opa! "+b.getBytes().length);
		
		lPessoa.marshal(b);
		System.out.print("Opa! "+b.getBytes().length);
		return b;
	}

}
