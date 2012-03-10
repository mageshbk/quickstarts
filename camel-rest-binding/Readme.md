Introduction
============
This quickstart demonstrates the usage of Camel Component's REST binding features. It binds two
SwitchYard services over CXFRS URLs that can be accessed by any REST based client. One of them also
acts as a client. When a message arrives to the CXFRS endpoint a SwitchYard service will be invoked.
This SwitchYard service internally invokes another CXFRS endpoint via reference binding.

<pre>
+-----------------+      +--------------+      +----------------+      +-----------------+
| Camel cxfrs://  | ---- | HelloService | ---- | Camel cxfrs:// | ---- | Greetingservice |
+-----------------+      +--------------+      +----------------+      +-----------------+
</pre>

Running the quickstart
======================

JBoss AS 7
----------
1. Build the quickstart:
<pre>
    mvn clean install
</pre>
2. Start JBoss AS 7 in standalone-preview mode:
<pre>
    ${AS}/bin/standalone.sh --server-config=standalone.xml
</pre>
3. Deploy the quickstart
<pre>
    cp target/switchyard-quickstarts-camel-rest-binding.jar ${AS7}/standalone/deployments
</pre>
4. Open your favourite web browse and navigate to this URL  
    [http://localhost:18001/greeter/sayhello/Jones] (http://localhost:18001/greeter/sayhello/Jones)
5. You should get the following response:  
<pre>
    Hello there Jones :-) - message received via REST binding.
</pre>
6. Swicth back to server console or log and these messages should be displayed  
<pre>
    Jones is saying Hello!
    Jones is saying Hi!
</pre>

## Further Reading

1. [Camel Binding Documentation](https://docs.jboss.org/author/display/SWITCHYARD/Camel+Bindings)
