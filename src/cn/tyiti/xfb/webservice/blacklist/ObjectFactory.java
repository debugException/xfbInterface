package cn.tyiti.xfb.webservice.blacklist;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.tyiti.xfb.webservice package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _QueryServiceItemByCode_QNAME = new QName(
			"http://webservice.internetfinance.com/", "queryServiceItemByCode");
	private final static QName _QueryServiceItemByCodeResponse_QNAME = new QName(
			"http://webservice.internetfinance.com/",
			"queryServiceItemByCodeResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.tyiti.xfb.webservice
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link QueryServiceItemByCodeResponse }
	 * 
	 */
	public QueryServiceItemByCodeResponse createQueryServiceItemByCodeResponse() {
		return new QueryServiceItemByCodeResponse();
	}

	/**
	 * Create an instance of {@link QueryServiceItemByCode }
	 * 
	 */
	public QueryServiceItemByCode createQueryServiceItemByCode() {
		return new QueryServiceItemByCode();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link QueryServiceItemByCode }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.internetfinance.com/", name = "queryServiceItemByCode")
	public JAXBElement<QueryServiceItemByCode> createQueryServiceItemByCode(
			QueryServiceItemByCode value) {
		return new JAXBElement<QueryServiceItemByCode>(
				_QueryServiceItemByCode_QNAME, QueryServiceItemByCode.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link QueryServiceItemByCodeResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webservice.internetfinance.com/", name = "queryServiceItemByCodeResponse")
	public JAXBElement<QueryServiceItemByCodeResponse> createQueryServiceItemByCodeResponse(
			QueryServiceItemByCodeResponse value) {
		return new JAXBElement<QueryServiceItemByCodeResponse>(
				_QueryServiceItemByCodeResponse_QNAME,
				QueryServiceItemByCodeResponse.class, null, value);
	}

}
