package br.unifor.agenda;

import br.unifor.mobile.MobileStub;
import arcademis.MarshalException;
import arcademis.NetworkException;
import arcademis.Stream;
import rme.MultiServerStub;
import rme.RmeStream;
import rme.RmeStreamFc;

public class PhoneBook_Stub extends MobileStub {

	public Pessoa getPhoneAddress(String name) throws NetworkException {
		
		Pessoa lPessoa = null;
		Stream stream = (new RmeStreamFc()).createStream();
		try {
			stream.write("getPhoneAddress");
			stream.write(1);
			stream.write(name);
			
			RmeStream lStream = (RmeStream)super.invoke(stream, 1, '?', 0);
			lPessoa = new Pessoa();
			lPessoa.unmarshal(lStream);
			
		} catch (MarshalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lPessoa;
	}
}
