package br.unifor.mobile;

import java.util.logging.Logger;

import arcademis.NetworkException;
import arcademis.OrbAccessor;
import arcademis.Stream;
import rme.MultiServerStub;
import rme.RmeStream;

public class MobileStub extends MultiServerStub {
		
	public Stream getRemoteState() throws NetworkException {

		RmeStream stream = (RmeStream) OrbAccessor.getStream();
		
		stream.write("getRemoteState");
		stream.write(0);

		beforeInvoke(stream);

		RmeStream lStream = (RmeStream) super.invoke(stream, 1, '?', 0);

		return lStream;
	}
	
	public void beforeInvoke(Stream stream){
		
	}
}
