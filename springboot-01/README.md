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
  
  
# Autoconfiguration report (dubug=true)
```

============================
CONDITIONS EVALUATION REPORT
============================


Positive matches:
-----------------

   AopAutoConfiguration matched:
      - @ConditionalOnProperty (spring.aop.auto=true) matched (OnPropertyCondition)
   
Negative matches:
-----------------

   ActiveMQAutoConfiguration:
      Did not match:
         - @ConditionalOnClass did not find required class 'jakarta.jms.ConnectionFactory' (OnClassCondition)

Exclusions:
-----------

    None

Unconditional classes:
----------------------

    org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration
    org.springframework.boot.autoconfigure.ssl.SslAutoConfiguration

```
 
- if developer wants to create a bean instead of springboot then developer has to add that in the exclusion   list.

```java
    @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})  
    Exclusions:
    -----------  
    org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```
- unconditional classes will be created irrespective of dependencies added in the pom.xml file, these are    base classes required for springboot application      

```java
Note: @SpringBootApplication is the combination of below configurtions
      //@SpringBootConfiguration
      //@EnableAutoConfiguration
      //@ComponentScan
```

- For the classes under sub packages of main root package are not required to add componentscan in StringBootApplcation.
  
# MVC Style RestServices  

```java
@Controller
@RequestMapping("/employees")
public class EmployeeController {

	
	@GetMapping
	@ResponseBody
	public List<String> getEmployee() {
		return Arrays.asList("Ramesh","Suresh");
		
	}
}

```
Note: `@ResponseBody` tells DispatcherServer send only HttpResponse not view.
  
- http://localhost:8080/api/employees
- http://localhost:8080/api/employees/getEmployeeByName?empName=Ramesh
- http://localhost:8080/api/employees/getEmployee
  requestbody:  
  
  {
        "empId": 13,
        "empName": "Ramesh",
        "empAge": 34
    } 
- Use postman to test the APIs
- ViewResolver will not come into picture.

