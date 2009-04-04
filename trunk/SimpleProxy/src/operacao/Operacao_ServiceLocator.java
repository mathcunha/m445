/**
 * Operacao_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package operacao;

public class Operacao_ServiceLocator extends org.apache.axis.client.Service implements operacao.Operacao_Service {

    public Operacao_ServiceLocator() {
    }


    public Operacao_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Operacao_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for OperacaoPort
    private java.lang.String OperacaoPort_address = "http://localhost:8080/Operacao/Operacao";

    public java.lang.String getOperacaoPortAddress() {
        return OperacaoPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String OperacaoPortWSDDServiceName = "OperacaoPort";

    public java.lang.String getOperacaoPortWSDDServiceName() {
        return OperacaoPortWSDDServiceName;
    }

    public void setOperacaoPortWSDDServiceName(java.lang.String name) {
        OperacaoPortWSDDServiceName = name;
    }

    public operacao.Operacao_PortType getOperacaoPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(OperacaoPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getOperacaoPort(endpoint);
    }

    public operacao.Operacao_PortType getOperacaoPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            operacao.OperacaoPortBindingStub _stub = new operacao.OperacaoPortBindingStub(portAddress, this);
            _stub.setPortName(getOperacaoPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setOperacaoPortEndpointAddress(java.lang.String address) {
        OperacaoPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (operacao.Operacao_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                operacao.OperacaoPortBindingStub _stub = new operacao.OperacaoPortBindingStub(new java.net.URL(OperacaoPort_address), this);
                _stub.setPortName(getOperacaoPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("OperacaoPort".equals(inputPortName)) {
            return getOperacaoPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:Operacao", "Operacao");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:Operacao", "OperacaoPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("OperacaoPort".equals(portName)) {
            setOperacaoPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
