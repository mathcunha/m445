package br.unifor.agenda;

import rme.RmeConfigurator;
import rme.RmeStream;
import rme.RmeStreamFc;
import rme.naming.RmeNaming;
import arcademis.ArcademisException;
import arcademis.MarshalException;
import arcademis.NetworkException;
import arcademis.NotBoundException;
import arcademis.ReconfigurationException;
import arcademis.Stream;
import arcademis.concreteComponents.MalformedURLException;
import br.unifor.mobile.MobileStub;

public class PhoneBook_Stub extends MobileStub {

	PhoneBook_Stub stub = this;

	public Pessoa getPhoneAddress(String name) throws NetworkException {
		return stub.getPhoneAddress_stub(name);
	}

	public Pessoa getPhoneAddress_stub(String name) throws NetworkException {

		Pessoa lPessoa = null;
		Stream stream = (new RmeStreamFc()).createStream();
		try {
			stream.write("getPhoneAddress");
			stream.write(1);
			stream.write(name);

			RmeStream lStream = (RmeStream) super.invoke(stream, 1, '?', 0);
			lPessoa = new Pessoa();
			lPessoa.unmarshal(lStream);

		} catch (MarshalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {

			RmeConfigurator c = new RmeConfigurator();
			try {
				c.configure();
				stub = (PhoneBook_Stub) RmeNaming.lookup("obj");
				return stub.getPhoneAddress(name);

			} catch (ReconfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MalformedURLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (ArcademisException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (NotBoundException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

		}

		return lPessoa;
	}
}
