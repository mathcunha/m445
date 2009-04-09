package proxy.policy;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*
 * Valores a serem levados para escolher o serviço:
 *   Operação do serviço;
 *   Quantidade de elementos XML no corpo do SOAP;
 *   Tempo de resposta;
 *   Quantidade de entradas do histórico a ser utilizado;
 *   Prazo de validade das entradas do histórico.
 */
public class ReplicatedServiceProxy_Impl implements ReplicatedServiceProxy {

	private List<URL> endpointList;
	
	private Random random = new Random(System.currentTimeMillis());
	
	@Override
	public URL chooseEndpoint() {
		
		if(endpointList == null || endpointList.size()==0){
			return null;
		}
		int indice = (int) (random.nextInt() % endpointList.size());
		return endpointList.get(Math.abs(indice));
	}

	@Override
	public List<URL> getEndpointLst() {
		return endpointList;
	}

	@Override
	public void setEndpointLst(List<URL> list) {
		endpointList = new ArrayList<URL>(list.size()+1);
		for (URL url : list) {
			endpointList.add(url);
		}
	}
}
