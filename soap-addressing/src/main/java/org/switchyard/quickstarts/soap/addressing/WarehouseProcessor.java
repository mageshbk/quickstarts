/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */

package org.switchyard.quickstarts.soap.addressing;

import java.net.URL;
import java.util.Map;

import javax.inject.Named;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.switchyard.common.xml.XMLHelper;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

@Named("warehouseProcessor")
public class WarehouseProcessor implements Processor {

    /**
     * Creates new processor.
     */
    public WarehouseProcessor() {
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Order order = exchange.getIn().getBody(Order.class);
        SOAPHeaderElement replyTo = (SOAPHeaderElement)exchange.getIn().getHeaders().get("{http://www.w3.org/2005/08/addressing}ReplyTo");
        SOAPHeaderElement relatesTo = (SOAPHeaderElement)exchange.getIn().getHeaders().get("{http://www.w3.org/2005/08/addressing}RelatesTo");
        Map<String, Object> headers = exchange.getIn().getHeaders();

        String actionStr = "<wsa:Action xmlns:wsa=\"http://www.w3.org/2005/08/addressing\">"
                            + "urn:switchyard-quickstart:soap-addressing:1.0:ResponseService:orderRequest"
                            + "</wsa:Action>";
        Element action = XMLHelper.getDocumentFromString(actionStr).getDocumentElement();

        SOAPConnectionFactory conFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection connection = conFactory.createConnection();
        MessageFactory msgFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
        SOAPMessage msg = msgFactory.createMessage();
        Node node = msg.getSOAPPart().getEnvelope().getHeader().getOwnerDocument().importNode(action, true);
        msg.getSOAPHeader().appendChild(node);
        node = msg.getSOAPHeader().getOwnerDocument().importNode(relatesTo, true);
        msg.getSOAPHeader().appendChild(node);
        SOAPBodyElement bodyElement = msg.getSOAPBody().addBodyElement(new QName("urn:switchyard-quickstart:soap-addressing:1.0", "orderResponse"));
        SOAPElement child = bodyElement.addChildElement(new QName("urn:switchyard-quickstart:soap-addressing:1.0", "return"));
        child.addTextNode("Order " + order.getItem() + " with quantity " + order.getQuantity() + " accepted.");

        SOAPMessage response = connection.call(msg, new URL(((Node)replyTo.getChildElements().next()).getFirstChild().getNodeValue()));
        connection.close();

        Thread.sleep(25000);
    }

}
