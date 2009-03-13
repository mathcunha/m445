package br.unifor.agenda;

import java.util.Hashtable;
import java.util.Set;
import java.util.Map.Entry;

import rme.RmeStream;

import arcademis.MarshalException;
import arcademis.Stream;
import br.unifor.mobile.MobileRemoteObject;



public class PhoneBook extends MobileRemoteObject implements
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

	@Override
	public void getState(Stream stream) {
		RmeStream lStream = (RmeStream)stream;
		Set<Entry<String, Pessoa>> valores = h.entrySet();
		
		try {
			stream.write(valores.size());
			System.out.println("valores.size() "+valores.size());
			for (Entry<String, Pessoa> entry : valores) {
				stream.write(entry.getKey());
				entry.getValue().marshal(stream);
			}
			
		} catch (MarshalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void restoreState(Stream stream) {
		h = new Hashtable<String, Pessoa>();
		
		RmeStream lStream = (RmeStream) stream;
		try {
			int tamanho = stream.readInt();
			String chave;
			Pessoa pessoa;
			System.out.println("tamanho "+tamanho);
			for (int i = 0; i < tamanho; i++) {
				chave= (String)lStream.readObject();
				pessoa = new Pessoa();
				pessoa.unmarshal(stream);
				
				System.out.println("chave "+chave);
				System.out.println("pessoa "+pessoa);
				
				h.put(chave, pessoa);
			}
			
		} catch (MarshalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
