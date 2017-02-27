package cn.tyiti.xfb.webservice.blacklist;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for queryServiceItemByCode complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="queryServiceItemByCode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestParameter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryServiceItemByCode", propOrder = { "requestParameter" })
public class QueryServiceItemByCode {

	protected String requestParameter;

	/**
	 * Gets the value of the requestParameter property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRequestParameter() {
		return requestParameter;
	}

	/**
	 * Sets the value of the requestParameter property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRequestParameter(String value) {
		this.requestParameter = value;
	}

}
