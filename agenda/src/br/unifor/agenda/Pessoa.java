package br.unifor.agenda;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import arcademis.MarshalException;
import arcademis.Marshalable;
import arcademis.Stream;

public class Pessoa implements Marshalable {

	private String name;
	private Endereco endereco;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void marshal(Stream b) {
		try {
			b.write(name);
			endereco.marshal(b);
		} catch (MarshalException e) {
			Logger.getLogger(Pessoa.class.getName()).log(Level.SEVERE,e.getMessage(),e);
		}

	}

	public void unmarshal(Stream b) {

		try {
			this.name = (String) b.readObject();
			Endereco local = (new Endereco());
			local.unmarshal(b);
			setEndereco(local) ;
		} catch (MarshalException e) {
			Logger.getLogger(Pessoa.class.getName()).log(Level.SEVERE,e.getMessage(),e);
		}

	}
	
	@Override
	public String toString() {
		
		return "Nome "+getName()+" "+getEndereco();
	}
}
