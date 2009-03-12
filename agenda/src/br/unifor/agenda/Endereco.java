package br.unifor.agenda;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import arcademis.MarshalException;
import arcademis.Marshalable;
import arcademis.Stream;

public class Endereco implements Marshalable {

	private String rua;
	private String bairro;

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void marshal(Stream b) {
		try {
			b.write(rua);
			b.write(bairro);
		} catch (MarshalException e) {
			Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE,e.getMessage(),e);			
		}
		

	}

	public void unmarshal(Stream b) {

		try {
			this.rua = (String) b.readObject();
			this.bairro = (String) b.readObject();
		} catch (MarshalException e) {
			Logger.getLogger(Endereco.class.getName()).log(Level.SEVERE,e.getMessage(),e);
		}
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Rua "+getRua() +" Bairro "+getBairro();
	}
}
