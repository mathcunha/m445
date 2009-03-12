package br.unifor.agenda.server;

import rme.RmeInvokerFc;
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

public class AgendaConfigurator implements Configurator {
	public void configure() throws ReconfigurationException {
		if (ORB.isOpenForReconfiguration()) {
			
			// define the activator factory
			ActivatorFc atFc = null;
			ORB.setActivatorFactory(atFc);

			// define the invoker factory
			InvokerFc ivFc = new RmeInvokerFc();
			ORB.setInvokerFactory(ivFc);

			// define the dispatcher factory
			DispatcherFc dpFc = null;
			ORB.setDispatcherFactory(dpFc);

			// Define the channel factory
			ChannelFc cnFc = null;
			ORB.setChannelFactory(cnFc);

			// Define the message factory
			MessageFc msFc = null;
			ORB.setMessageFactory(msFc);

			// Define the protocol factory
			ProtocolFc ptFc = null;
			ORB.setProtocolFactory(ptFc);

			// Define the connection server factory
			ConnectionServerFc csFc = null;
			ORB.setConnectionServerFactory(csFc);

			// Define a fabrica de identificadores remotos
			IdentifierFc idFc = null;
			ORB.setIdentifierFactory(idFc);

			// Define a fabrica de enderecos remotos
			EpidFc epFc = null;
			ORB.setEpidFactory(epFc);

			// Define the Acceptors
			AcceptorFc acFc = null;
			ORB.setAcceptorFactory(acFc);

			// Define the Connectors
			ConnectorFc coFc = null;
			ORB.setConnectorFactory(coFc);

			// Define the service handler factory
			ServiceHandlerFc shFc = null;
			ORB.setServiceHandlerFactory(shFc);

			// Define a fabrica de referencias remotas
			RemoteRefFc rrFc = null;
			ORB.setRemoteRefFactory(rrFc);

			// Define the stream factory
			StreamFc sfFc = null;
			ORB.setStreamFactory(sfFc);
			
			ORB.closeForReconfiguration();
		}
	}
}
