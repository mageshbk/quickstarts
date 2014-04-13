Introduction
============
This quickstart demonstrates the usage of SOAP with Camel Cxf component. It binds
a SwitchYard service over SOAP/HTTP URL and invokes that using a Cxf url.

<pre>
+------------+      +--------------+      +-------------+      +--------------------------+      +-----------+      +------------------+
| http://    | ---- | OrderService | ---- | camel:route | ---- | WarehouseServiceExternal | ---- | cxf://url | ---- | WarehouseService |
+------------+      +--------------+      +-------------+      +--------------------------+      +-----------+      +------------------+
</pre>

Running the quickstart
======================

JBoss AS 7
----------
1. Start JBoss AS 7 in standalone mode:
<pre>
    ${AS}/bin/standalone.sh --server-config=standalone.xml
</pre>
2. If on Windows, please create a directory called 'tmp' under c:
3. Build and deploy the quickstart
<pre>
    mvn install -Pdeploy
</pre>
4. Open a console window and type
<pre>
    mvn exec:java -Dexec.args="Boeing 10"
</pre>
5. You should see the following output
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <SOAP-ENV:Body>
        <orderResponse xmlns="urn:switchyard-quickstart:camel-cxf:2.0">
            <return>Order Boeing with quantity 10 accepted.</return>
        </orderResponse>
    </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
6. Undeploy the quickstart:

        mvn clean -Pdeploy

## Further Reading

1. [SOAP Binding Documentation](https://docs.jboss.org/author/display/SWITCHYARD/SOAP)
2. [Camel-CXF] http://camel.apache.org/cxf.html
