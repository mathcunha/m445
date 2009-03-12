package br.unifor.agenda;

import rme.RmeConfigurator;
import rme.naming.RmeNaming;

public class Client {
	  public static void main(String args[]) {
	    try {
	      RmeConfigurator c = new RmeConfigurator();
	      c.configure();
	      PhoneBook_Stub phoneBook = (PhoneBook_Stub)
	          RmeNaming.lookup("127.0.0.1/obj");
	      
	      
	      Pessoa a = phoneBook.getPhoneAddress("Matheus");
	      System.out.println(a.toString());
	    } catch (Exception e) {e.printStackTrace();}
	  }
	}