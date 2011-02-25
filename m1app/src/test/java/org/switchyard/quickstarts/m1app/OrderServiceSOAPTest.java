/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more 
 * details. You should have received a copy of the GNU Lesser General Public 
 * License, v.2.1 along with this distribution; if not, write to the Free 
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  
 * 02110-1301, USA.
 */

package org.switchyard.quickstarts.m1app;

import java.net.URL;
import org.junit.Test;
import org.switchyard.test.SwitchYardCDITestCase;
import org.switchyard.test.WebServiceInvoker;

/**
 * Test OrderService via SOAP.
 *
 * @author Magesh Kumar B <mageshbk@jboss.com> (C) 2011 Red Hat Inc.
 */
public class OrderServiceSOAPTest extends SwitchYardCDITestCase {

    @Test
    public void testOrderAccepted() throws Exception {
        newInvoker("OrderService");
System.out.println("Invoking WS ****************************");
        String input = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"" +
                       "                  xmlns:app=\"urn:switchyard-quickstarts:m1app:1.0\"" +
                       "                  xmlns:types=\"urn:switchyard-quickstarts:m1app-types:1.0\">" +
                       "   <soapenv:Body>" +
                       "      <app:submitOrder>" +
                       "         <types:order>" +
                       "            <orderId>ORDER01</orderId>" +
                       "            <itemId>BUTTER</itemId>" +
                       "            <quantity>100</quantity>" +
                       "         </types:order>" +
                       "      </app:submitOrder>" +
                       "   </soapenv:Body>" +
                       "</soapenv:Envelope>";
        
        String output = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                       "                  xmlns:app=\"urn:switchyard-quickstarts:m1app:1.0\"\n" +
                       "                  xmlns:types=\"urn:switchyard-quickstarts:m1app-types:1.0\">\n" +
                        "   <soapenv:Body>\n" +
                        "      <app:submitOrderResponse>\n" +
                        "         <types:orderAck>\n" +
                        "            <orderId>ORDER01</orderId>\n" +
                        "            <accepted>true</accepted>\n" +
                        "            <status>Order Accepted</status>\n" +
                        "         </types:orderAck>\n" +
                        "      </app:submitOrderResponse>\n" +
                        "   </soapenv:Body>\n" +
                        "</soapenv:Envelope>\n";

        String response = new WebServiceInvoker(new URL("http://localhost:18001/OrderService"), input).call();
        //XMLAssert.assertXMLEqual("Expected " + output + " but was " + response, output, response);
        System.out.println(response);
        System.in.read();
    }
}
