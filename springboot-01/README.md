# springboot

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication

```
- SprigBoot internally creates DispatcherServlet and it routes to corresponding controller based on request.


- After running the applciation
  log: Tomcat started on port(s): 8080 (http) with context path ''
  
- spring-mvc controller  
  http://localhost:8080/home
- changing port & context path in application.properties  
  server.port=9090  
  server.servlet.context-path=/eis  
  Tomcat started on port(s): 9090 (http) with context path '/eis'  
    
  http://localhost:9090/eis/home
  
# Random port number
  server.port=0  