package br.unifor.mobile;

import arcademis.Stream;
import rme.server.RmeRemoteObject;

public abstract class MobileRemoteObject extends RmeRemoteObject{
	public abstract void getState(Stream stream);
	
	public abstract void restoreState(Stream stream);
}
