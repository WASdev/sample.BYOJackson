# WebSphere Liberty and JAX-RS: BYO Jackson

Chances are that if you use JAX-RS in WebSphere Liberty that you use JSON to
format your data.  If so, it is equally likely that you are using Jackson to do
it.  Liberty's JAX-RS implementation actually uses Jackson to as it's default
JSON provider.  _That's pretty cool,_ you say, _but how can I take advantage of
that in my application?_

Good question!  Liberty uses Jackson, but intentionally does not expose it to
user applications.  So if you want to use some of the [cool features in
Jackson](https://github.com/FasterXML/jackson-core/wiki/JsonParser-Features)
like annotating fields to ignore or providing serialization processing
instructions, you just can't do it... or can you?

Sure you can, just bring your own Jackson!  In other words, package the Jackson
JAX-RS Provider JAR files in your application (in the WEB-INF/lib directory of
your WAR file). The JARs you'll need are:
* [jackson-jaxrs-json-provider](https://mvnrepository.com/artifact/com.fasterxml.jackson.jaxrs/jackson-jaxrs-json-provider)
* [jackson-core](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core)
* [jackson-jaxrs-base](https://mvnrepository.com/artifact/com.fasterxml.jackson.jaxrs/jackson-jaxrs-base)
* [jackson-databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind)
* [jackson-module-jaxb-annotations](https://mvnrepository.com/artifact/com.fasterxml.jackson.module/jackson-module-jaxb-annotations)
* [jackson-annotations](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations) - for the cool annotations!



The Jackson JAX-RS JSON provider class is automatically registered if all of the
JAR files are in the WAR's WEB-INF/lib directory.  However, if you specify any
classes in your Application subclass via the getClasses() or getSingletons()
methods, then you will need to register the provider class, like so:
```Java

public class HelloWorldApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        // ...
        classes.add(JacksonJaxbJsonProvider.class);
        return classes;
    }

}
```

Automatic discovery/registration is not available in the JAX-RS Client APIs, so
you would need to explicitly register the provider class, like so:
```Java
Client client = ClientBuilder.newClient().register(JacksonJsonProvider.class);
```

That's it!  Now Liberty's JAX-RS implementation will use the Jackson provider
from your application instead of the one built-in to Liberty.  This means that
your application can now use those cool Jackson features.

 _Do I need to do any classloading tricks? Like parentLast?_ Nope, since the
 Liberty server does not expose the Jackson API packages, your application will
 only be able to load the Jackson classes that you provide.  It also means that
 the application work regardless of the delegation policy - so you can use
 parentLast if you really want to.

_Do you have a sample application that might help me understand how this works?_
You bet. Check out the [sample.BYOJackson app](https://github.com/WASdev/sample.BYOJackson)
at the WASdev GitHub repo!

Thanks!
