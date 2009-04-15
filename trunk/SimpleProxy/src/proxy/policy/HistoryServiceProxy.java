package proxy.policy;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

/*
 * Valores a serem levados para escolher o serviço:
 *   Operação do serviço;
 *   Quantidade de elementos XML no corpo do SOAP;
 *   Tempo de resposta;
 *   Quantidade de entradas do histórico a ser utilizado;
 *   Prazo de validade das entradas do histórico.
 */
public class HistoryServiceProxy extends ReplicatedServiceProxy_Impl {

	

	private List<EndPointInfo> listEndPointInfo;

		/**
	 * Quantidade de entradas no histórico do tipo do EndPoint a ser utilizada
	 * para se obter o EndPoint desejado.
	 */
	private Integer k;

	/**
	 * Prazo de validade das entradas do histórico. Atulizado em segundos.
	 */
	private Long t;

	private String service;
	private String operation;

	public HistoryServiceProxy(String service, String operation, Integer k,
			Long t) {
		this.k = k;
		this.t = t;

		this.operation = operation;
		this.service = service;
	}

	public boolean resetEndPointInfo(List<URL> destinos) {
		boolean retorno = true;
		
		setEndPointList( destinos);

		this.listEndPointInfo = new ArrayList<EndPointInfo>(destinos.size());

		Long dataAtual = System.currentTimeMillis();

		for (URL url : destinos) {
			EndPointInfo lEndPointInfo = new EndPointInfo(service, operation,1, dataAtual);
			lEndPointInfo.setUrl(url);
			lEndPointInfo.setTimeResponse(0l);

			addEndPointInfo(lEndPointInfo);
		}

		return retorno;
	}

	
	public URL chooseEndPoint(EndPointInfo endPointInfo) {

		/**
		 * A melhor URL a ser escolhida.
		 */
		URL bestURL = null;

		/**
		 * Inicializa a lista de serviço similares.
		 */
		List<EndPointInfo> listEndPointInfoSimilares = new ArrayList<EndPointInfo>();

		if (listEndPointInfo.size() == 1) {
			return endPointInfo.getUrl();
		}

		int quantidadeSimilares = 0;
		System.out.println("listEndPointInfo "+listEndPointInfo.size());
		
		for (int i =  listEndPointInfo.size()-1; i >= 0; i--) {
			EndPointInfo epi = listEndPointInfo.get(i);
			
			if (epi.getServiceName().equals(endPointInfo.getServiceName())
					&& epi.getOperationName().equals(
							endPointInfo.getOperationName())
					&& epi.getNodeNumber().equals(endPointInfo.getNodeNumber())
					&& (new Date().getTime() - epi.getDateEndPontInfo() <= t)) {

				quantidadeSimilares++;
				listEndPointInfoSimilares.add(epi);
				if (quantidadeSimilares == k) {
					break;
				}
			}
		}

		System.out.println("quantidadeSimilares ("+quantidadeSimilares +")");
		if (quantidadeSimilares == 0 || quantidadeSimilares == 1) {
			//Tem que ser aleatório
			System.out.println("Escolha aleatória! ");
			return super.chooseEndPoint();
		}

		//Somando os tempos por URL
		Hashtable<URL, Info> dados = new Hashtable<URL, Info>(); 
		for (EndPointInfo endPointInfo2 : listEndPointInfoSimilares) {
			Info info = dados.get(endPointInfo2.getUrl());
			if(info == null){
				info = new Info();
				dados.put(endPointInfo2.getUrl(), info);
			}
			info.qtdDados++;
			info.somaTempo += endPointInfo2.getTimeResponse();
		}
		
		
		//Calculando a média
		long menorTempo = Long.MAX_VALUE;
		for (Entry<URL, Info> valor : dados.entrySet()) {
			long tempoAtual = valor.getValue().somaTempo / valor.getValue().qtdDados;
			System.out.println(valor.getKey() +" "+valor.getValue());
			if(menorTempo >= tempoAtual){
				bestURL = valor.getKey();
				menorTempo = tempoAtual;
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
	}
	
	class Info{
		public long somaTempo = 0;
		public int qtdDados = 0;
		
		@Override
		public String toString(){
			return ("somaTempo ("+somaTempo+") qtdDados ("+ qtdDados+") ");
		}
	}
}
