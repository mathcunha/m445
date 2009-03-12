package br.unifor.agenda;

import java.util.Hashtable;



public class PhoneBook extends rme.server.RmeRemoteObject implements
		PhoneCatalogue {

	Hashtable<String, Pessoa> h = new Hashtable();

	public Pessoa getPhoneAddress(String name) {
		System.out.println("name "+name);
		return (Pessoa) h.get(name);
	}

	public void insert(String name, String rua, String bairro) {
		Pessoa lp = new Pessoa();
		lp.setName(name);
		
		Endereco endereco = new Endereco();
		endereco.setBairro(bairro);
		endereco.setRua(rua);
		
		lp.setEndereco(endereco);
		
		h.put(name, lp);
	}
}
