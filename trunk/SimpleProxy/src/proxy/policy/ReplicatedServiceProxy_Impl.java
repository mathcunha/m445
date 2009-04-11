package proxy.policy;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/*
 * Valores a serem levados para escolher o serviço:
 *   Operação do serviço;
 *   Quantidade de elementos XML no corpo do SOAP;
 *   Tempo de resposta;
 *   Quantidade de entradas do histórico a ser utilizado;
 *   Prazo de validade das entradas do histórico.
 */
public class ReplicatedServiceProxy_Impl implements ReplicatedServiceProxy {

	List<URL> endpointList = new ArrayList<URL>();
	
	private List<EndPointInfo> listEndPointInfo;
	
	private List<EndPointInfo> listEndPointInfoSimilares;
	
	private EndPointInfo endPointInfo;
	
	/**
	 * Quantidade de entradas no histórico do tipo do EndPoint a
	 * ser utilizada para se obter o EndPoint desejado.
	 */
	private Integer k;
	
	/**
	 * Prazo de validade das entradas do histórico. Atulizado em segundos.
	 */
	private Long t;
	
	public ReplicatedServiceProxy_Impl(Integer k, Long t) {
		this.k = k;
		this.t = t;
	}
	
	@Override
	public URL chooseEndPoint() {

		/**
		 * A melhor URL a ser escolhida.
		 */
		URL bestURL;
		
		/**
		 * Inicializa a lista de serviço similares.
		 */
		this.listEndPointInfoSimilares = new ArrayList<EndPointInfo>();
		
		if (listEndPointInfo.size() == 1) {
			return endPointInfo.getUrl();
		}
		
		int quantidadeSimilares = 0;
		for (EndPointInfo epi : listEndPointInfo) {
			
			if (epi.getServiceName().equals(endPointInfo.getServiceName())
				&& epi.getOperationName().equals(endPointInfo.getOperationName())
				&& epi.getNodeNumber().equals(endPointInfo.getNodeNumber())
				&& (new Date().getTime() - epi.getDateEndPontInfo() <= t ) ) {
				
				quantidadeSimilares++;
				listEndPointInfoSimilares.add(epi);
				if (quantidadeSimilares == k) {
					break;
				}
			}
		}
		
		if (quantidadeSimilares == 0 || quantidadeSimilares == 1) {
			return endPointInfo.getUrl();
		}
		
		EndPointInfo endPointInfoAnterior = listEndPointInfoSimilares.get(0);
		bestURL = endPointInfoAnterior.getUrl();
		for(int i = 1; i < listEndPointInfoSimilares.size(); i++) {
			
			EndPointInfo endPointInfoAtual = listEndPointInfoSimilares.get(i);
			if (endPointInfoAtual.getTimeResponse() < endPointInfoAnterior.getTimeResponse()) {
				bestURL = endPointInfoAtual.getUrl();
			}
		}
		System.out.println("URL escolhida: " + bestURL.toString());
		return bestURL;
	}

	@Override
	public void addEndPointInfo(EndPointInfo endPointInfo) {
		if (this.listEndPointInfo == null) {
			this.listEndPointInfo = new ArrayList<EndPointInfo>();
		}
		this.listEndPointInfo.add(endPointInfo);
		this.endPointInfo = endPointInfo;
	}
	
	@Override
	public void setEndPointList(List<URL> list) {
		endpointList = new ArrayList<URL>(list.size()+1);
		for (URL url : list) {
			endpointList.add(url);
		}
	}

}
