package service.ejb;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Session Bean implementation class Operacao
 */
@Stateless
@WebService(serviceName = "Operacao", targetNamespace = "urn:Operacao")
public class Operacao implements OperacaoRemote {

	@WebMethod
	@WebResult(name = "resultado")
	public Integer somar(@WebParam(name="x") Integer x, @WebParam(name="y") Integer y){
		Integer resultado = null;
		if(x != null && y != null){
			resultado = x + y;
		}
		
		System.out.println("no servidor "+resultado);
		return resultado;
	}

}
