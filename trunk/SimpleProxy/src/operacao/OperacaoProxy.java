package operacao;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;


public class OperacaoProxy implements
		operacao.Operacao_PortType {
	private String _endpoint = null;
	private operacao.Operacao_PortType operacao_PortType = null;

	public OperacaoProxy() {
		_initOperacaoProxy();
	}

	public OperacaoProxy(List<URL> list) {
		_initOperacaoProxy();
		
	}

	public OperacaoProxy(String endpoint) {
		_endpoint = endpoint;
		_initOperacaoProxy();
	}

	private void _initOperacaoProxy() {
		try {
			operacao_PortType = (new operacao.Operacao_ServiceLocator())
					.getOperacaoPort();
			if (operacao_PortType != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) operacao_PortType)
							._setProperty(
									"javax.xml.rpc.service.endpoint.address",
									_endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) operacao_PortType)
							._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (operacao_PortType != null)
			((javax.xml.rpc.Stub) operacao_PortType)._setProperty(
					"javax.xml.rpc.service.endpoint.address", _endpoint);

	}

	public operacao.Operacao_PortType getOperacao_PortType() {
		if (operacao_PortType == null)
			_initOperacaoProxy();
		return operacao_PortType;
	}

	@Override
	public Integer somar(Integer x, Integer y) throws RemoteException {
		Operacao_ServiceLocator serviceLocator = new Operacao_ServiceLocator();
		Integer resultado = null;
		try {
			URL lEndPoint = null;
			if (lEndPoint == null) {

				resultado = serviceLocator.getOperacaoPort().somar(x, y);
			} else {
				resultado = serviceLocator.getOperacaoPort(lEndPoint).somar(x,
						y);
			}

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public static void main(String args[]) {

		List<URL> list = new ArrayList<URL>(3);
		try {
			list.add(new URL("http://localhost:8080/Operacao/Operacao"));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			list.add(new URL("http://localhost:38080/Operacao/Operacao"));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			list.add(new URL("http://localhost:38081/Operacao/Operacao"));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		OperacaoProxy lOperacaoProxy = new OperacaoProxy(list);

		try {
			Integer r = lOperacaoProxy.somar(new Integer(args[0]), new Integer(
					args[1]));

			System.out.println("resultado " + r);
			 r = lOperacaoProxy.somar(new Integer(args[0]), new Integer(
					args[1]));

			System.out.println("resultado " + r);
			 r = lOperacaoProxy.somar(new Integer(args[0]), new Integer(
					args[1]));

			System.out.println("resultado " + r);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}