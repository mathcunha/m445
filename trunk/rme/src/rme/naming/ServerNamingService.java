package rme.naming;

import arcademis.*;
import arcademis.server.*;

public interface ServerNamingService {

    public void bind(String name, Stub obj) throws AlreadyBoundException, ArcademisException;

    public void unbind(String name) throws NotBoundException, ArcademisException;

    public void rebind(String name, Stub obj) throws ArcademisException;

}