package br.unifor.mia.cd.middleware.rme2;

import arcademis.ChannelFc;
import arcademis.Configurator;
import arcademis.ConnectorFc;
import arcademis.EpidFc;
import arcademis.IdentifierFc;
import arcademis.InvokerFc;
import arcademis.MessageFc;
import arcademis.ORB;
import arcademis.ProtocolFc;
import arcademis.ReconfigurationException;
import arcademis.RemoteRefFc;
import arcademis.ServiceHandlerFc;
import arcademis.StreamFc;
import arcademis.server.AcceptorFc;
import arcademis.server.ActivatorFc;
import arcademis.server.ConnectionServerFc;
import arcademis.server.DispatcherFc;
import br.unifor.mia.cd.middleware.rme2.rmep.RmeMessageFc;
import br.unifor.mia.cd.middleware.rme2.rmep.RmeProtocolFc;
import br.unifor.mia.cd.middleware.rme2.server.RmeAcceptorFc;
import br.unifor.mia.cd.middleware.rme2.server.RmeActivatorFc;
import br.unifor.mia.cd.middleware.rme2.server.RmeConServerFc;
import br.unifor.mia.cd.middleware.rme2.server.RmeDispatcherFc;

public final class RmeConfigurator implements Configurator {
	public void configure() throws ReconfigurationException {
		if(ORB.isOpenForReconfiguration()) {

			// define the activator factory
			ActivatorFc atFc = new RmeActivatorFc();
			ORB.setActivatorFactory(atFc);

			// define the invoker factory
			InvokerFc ivFc = new RmeInvokerFc();
			ORB.setInvokerFactory(ivFc);

			// define the dispatcher factory
			DispatcherFc dpFc = new RmeDispatcherFc();
			ORB.setDispatcherFactory(dpFc);

			// Define the channel factory
			ChannelFc cnFc = new RmeChannelFc();
			ORB.setChannelFactory(cnFc);

			// Define the message factory
			MessageFc msFc = new RmeMessageFc();
			ORB.setMessageFactory(msFc);

			// Define the protocol factory
			ProtocolFc ptFc = new RmeProtocolFc();
			ORB.setProtocolFactory(ptFc);

			// Define the connection server factory
			ConnectionServerFc csFc = new RmeConServerFc();
			ORB.setConnectionServerFactory(csFc);

			// Define a fabrica de identificadores remotos
			IdentifierFc idFc = new RmeIdentifierFc();
			ORB.setIdentifierFactory(idFc);

			// Define a fabrica de enderecos remotos
			EpidFc epFc = new RmeEpidFc();
			ORB.setEpidFactory(epFc);

			// Define the Acceptors
			AcceptorFc acFc = new RmeAcceptorFc();
			ORB.setAcceptorFactory(acFc);

			// Define the Connectors
			ConnectorFc coFc = new RmeConnectorFc();
			ORB.setConnectorFactory(coFc);

			// Define the service handler factory
			ServiceHandlerFc shFc = new RmeServiceHandlerFc();
			ORB.setServiceHandlerFactory(shFc);

			// Define a fabrica de referencias remotas
			RemoteRefFc rrFc = new RmeRemoteRefFc();
			ORB.setRemoteRefFactory(rrFc);

			// Define the stream factory
			StreamFc sfFc = new RmeStreamFc();
			ORB.setStreamFactory(sfFc);

			ORB.closeForReconfiguration();
		}

	}
}
