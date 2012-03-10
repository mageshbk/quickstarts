/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
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

package org.switchyard.quickstarts.camel.rest.binding;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Simple REST interface for a Greeter Service on client side.
 *
 * @author Magesh Kumar B <mageshbk@jboss.com> (C) 2012 Red Hat Inc.
 */
@Path("/sayhello")
public class ClientResource {

    /**
     * This is just a dummy method and will not be invoked by Camel cxfrs.
     * See switchyard.xml for configuration
     *
     * @param name the name of the person to be greeted.
     */
    @POST
    @Path("/{name}")
    public String sayHello(@PathParam("name") String name) {
        System.out.println("sayHello resource called ..................");
        return "";
    }

    /**
     * This is just a dummy method and will not be invoked by Camel cxfrs.
     * See switchyard.xml for configuration
     *
     * @param name the name of the person to be greeted.
     */
    @GET
    @Path("/{name}")
    public String hello(@PathParam("name") String name) {
        System.out.println("sayHello resource called ..................");
        return "";
    }
}
