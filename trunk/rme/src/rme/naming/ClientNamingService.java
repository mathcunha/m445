package rme.naming;

import arcademis.ArcademisException;
import arcademis.NotBoundException;
import arcademis.Remote;

public interface ClientNamingService {
    public Remote lookup(String name) throws NotBoundException, ArcademisException;

    public String[] list() throws ArcademisException;
}