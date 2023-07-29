- @RestController=@Controller+ @ResponseBody

-  `@RequestEntity`  -> contains information about request headers & body.
-  `@ResponseEntity` -> contains information about response headers & body.

# Global Exception Handler
- `@ControllerAdvice(annotations=Controller.class)` - used for spring MVC controllers
- `@ControllerAdvice(annotations=RestController.class)` - used for spring rest controllers
- To perform validations on Input data - extends `ResponseEntityExceptionHandler` and override `handleMethodArgumentNotValid` method.

- if there are multiple global exception handlers , we can give priority to which handler should execute first
  using `@Order(1)`
  
# To send response as XML then add below dependency in pom.xml

```XML
	<dependency>
	    <groupId>com.fasterxml.jackson.dataformat</groupId>
		<artifactId>jackson-dataformat-xml</artifactId>
    </dependency>
```
- update `@RequestMapping(path="/api/employees",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})`
- Then , send request with Accept: application/xml header

# filtering response data & renaming response fields

- if we don't want send sensitive information to other application

```java
 @JsonProperty("employeeAge")
 private Integer empAge;
 
 @JsonIgnore
 private String empAddress;

 //POJO class level - ignore multiple fields
 @JsonIgnoreProperties(value={"empAddress"})  
```
 
 