package br.unifor.mia.cd.middleware.rme2.naming;

import br.unifor.mia.cd.middleware.rme2.MultiServerStub;
import arcademis.ArcademisException;
import arcademis.NotBoundException;
import arcademis.OrbAccessor;
import arcademis.Remote;
import arcademis.Stream;
import arcademis.Stub;
import arcademis.server.AlreadyBoundException;

public class RmeServiceDirectory_Impl_Stub extends MultiServerStub implements
		ClientNamingService, ServerNamingService {

	public Remote lookup(String name) throws ArcademisException,
			NotBoundException {
		Remote resp = null;
		try {
			Stream args = OrbAccessor.getStream();
			args.write(name);
			int op = 1;
			Stream future = invoke(args, op, '?', 0);
			if (future.isException()) {
				Exception e = (Exception) future.readObject();
				throw e;
			}
			resp = (Remote) future.readObject();
		} catch (ArcademisException e) {
			throw e;
		} catch (NotBoundException e) {
			throw e;
		} catch (Exception e) {
			throw new arcademis.UnspecifiedException(e.toString());
		}
		return resp;
	}

	public void bind(String name, Stub obj) throws ArcademisException,
			AlreadyBoundException {
		try {
			Stream args = OrbAccessor.getStream();
			args.write(name);
			args.write(obj);
			int op = 2;
			Stream future = invoke(args, op, '?', 0);
			if (future.isException()) {
				Exception e = (Exception) future.readObject();
				throw e;
			}
			future.readObject();
		} catch (ArcademisException e) {
			throw e;
		} catch (AlreadyBoundException e) {
			throw e;
		} catch (Exception e) {
			throw new arcademis.UnspecifiedException(e.toString());
		}
	}

	public void unbind(String name) throws ArcademisException,
			NotBoundException {
		try {
			Stream args = OrbAccessor.getStream();
			args.write(name);
			int op = 3;
			Stream future = invoke(args, op, '?', 0);
			if (future.isException()) {
				Exception e = (Exception) future.readObject();
				throw e;
			}
			future.readObject();
		} catch (NotBoundException e) {
			throw e;
		} catch (ArcademisException e) {
			throw e;
		} catch (Exception e) {
			throw new arcademis.UnspecifiedException(e.toString());
		}
	}

	public void rebind(String name, Stub obj) throws ArcademisException {
		try {
			Stream args = OrbAccessor.getStream();
			args.write(name);
			args.write(obj);
			int op = 4;
			Stream future = invoke(args, op, '?', 0);
			if (future.isException()) {
				Exception e = (Exception) future.readObject();
				throw e;
			}
			future.readObject();
		} catch (ArcademisException e) {
			throw e;
		} catch (Exception e) {
			throw new arcademis.UnspecifiedException(e.toString());
		}
	}

	public String[] list() throws ArcademisException {
		String[] resp = null;
		try {
			Stream args = OrbAccessor.getStream();
			int op = 5;
			Stream future = invoke(args, op, '?', 0);
			if (future.isException()) {
				Exception e = (Exception) future.readObject();
				throw e;
			}
			resp = (String[]) future.readObject();
		} catch (ArcademisException e) {
			throw e;
		} catch (Exception e) {
			throw new arcademis.UnspecifiedException(e.toString());
		}
		return resp;
	}
}