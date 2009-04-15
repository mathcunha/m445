package operacao;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class OperacaoProxy implements operacao.Operacao_PortType {
	private String _endpoint = null;
	private operacao.Operacao_PortType operacao_PortType = null;

	public OperacaoProxy() {
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
		Integer resultado = getOperacao_PortType().somar(x, y);
		return resultado;
	}

	public static void main(String args[]) {

		System.getProperties().put("http.proxyHost", "localhost");// http proxy
		// server
		System.getProperties().put("http.proxyPort", "111"); // http port #
		System.getProperties().put("http.proxySet", "true");

		OperacaoProxy lOperacaoProxy = new OperacaoProxy();

		try {
			Integer r = lOperacaoProxy.somar(new Integer(args[0]), new Integer(
					args[1]));

			System.out.println("resultado " + r);
			r = lOperacaoProxy
					.somar(new Integer(args[0]), new Integer(args[1]));

			System.out.println("resultado " + r);
			r = lOperacaoProxy
					.somar(new Integer(args[0]), new Integer(args[1]));

			System.out.println("resultado " + r);
			r = lOperacaoProxy
					.somar(new Integer(args[0]), new Integer(args[1]));

			System.out.println("resultado " + r);
			r = lOperacaoProxy
					.somar(new Integer(args[0]), new Integer(args[1]));

			System.out.println("resultado " + r);
			r = lOperacaoProxy
					.somar(new Integer(args[0]), new Integer(args[1]));

			System.out.println("resultado " + r);
			r = lOperacaoProxy
					.somar(new Integer(args[0]), new Integer(args[1]));

			System.out.println("resultado " + r);
			r = lOperacaoProxy
					.somar(new Integer(args[0]), new Integer(args[1]));

			System.out.println("resultado " + r);
			r = lOperacaoProxy
					.somar(new Integer(args[0]), new Integer(args[1]));

			System.out.println("resultado " + r);
			r = lOperacaoProxy
					.somar(new Integer(args[0]), new Integer(args[1]));

			System.out.println("resultado " + r);
			r = lOperacaoProxy
					.somar(new Integer(args[0]), new Integer(args[1]));

			System.out.println("resultado " + r);
			r = lOperacaoProxy
					.somar(new Integer(args[0]), new Integer(args[1]));

			System.out.println("resultado " + r);
			r = lOperacaoProxy
					.somar(new Integer(args[0]), new Integer(args[1]));

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