package br.unifor.mia.cd.middleware.rme2.naming;

import arcademis.ArcademisException;
import arcademis.NotBoundException;
import arcademis.Remote;

public interface ClientNamingService {
    public Remote lookup(String name) throws NotBoundException, ArcademisException;

    public String[] list() throws ArcademisException;
}