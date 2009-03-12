package br.unifor.agenda;

import rme.*;    
import arcademis.*;

public interface PhoneCatalogue extends Remote {
    public Pessoa getPhoneAddress(String name)
           throws ArcademisException;
}
