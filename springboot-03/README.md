- https://spring.io/projects/spring-data

# Spring Data projects

```
Spring Data JDBC - Mini version of Spring Data JPA
Spring Data JPA
Spring Data LDAP
Spring Data MongoDB
Spring Data Redis
Spring Data R2DBC
Spring Data REST
Spring Data for Apache Cassandra
Spring Data for Apache Geode
Spring Data for Apache Solr
Spring Data for VMware GemFire
Spring Data Couchbase
Spring Data Elasticsearch
Spring Data Envers
Spring Data Neo4j
Spring Data JDBC Extensions
Spring for Apache Hadoop
```

- Spring data is abstraction layer between application and persistence frameworks.

# Repository
- Available in spring-data-commons.jar 
- It'a marker interface
- CrudRepository- interface that extends Repository.
- as it has all abstract methods- how objects are going to save,update and delete?
  The corresponding code will be generated for operations in runtime.
- ListCrudRepository- extends CrudRepository
- CrudRepository - Returns Iterable
- ListCrudRepository - Returns List
- PagingAndSortingRepository -
- ListPagingAndSortingRepository extends  PagingAndSortingRepository
- Spring data team followed interface segregation principle (Developers can use any interface based on requirement) 
- Q: @Repository vs Repository interface.

- Spring Data
   Spring Data JPA- JpaRepository (available in spring-data-jpa.jar)
   Spring Data Mongo - MongoRepository 

- SpringDataJpa removes _ from table name
  @Table(name="contact_details")
  class ContactDetails- in this case no need to mention table name "contact_details" as it deletes _
  
- @MappedSuperclass- telling to Spring Data JPA treat the field names as column names   
  
# Spring Data JPA dependency

```java
  <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>
```
- Table creation script

```sql
  CREATE TABLE IF NOT EXISTS `employee` (
  `emp_id` int AUTO_INCREMENT  PRIMARY KEY,
  `emp_name` varchar(100) NOT NULL,
  `emp_email` varchar(100) NOT NULL,
  `emp_address` varchar(100) NOT NULL,
  `emp_type` varchar(10) NOT NULL,
  `created_dt` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_dt` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL
);
```
- Adding GeneratedValue strategy for Id(native:database will take care of generating id) 

```java
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
	@GenericGenerator(name = "native",strategy = "native")
	private Integer empId;
```
 
- Adding enum type fields - if database column is string type(employee_type varchar) then add `@Enumerated(EnumType.STRING)`

```java
    @Enumerated(EnumType.STRING)
	private EMPLOYEE_TYPE empType;
	
	public enum EMPLOYEE_TYPE{
		CONTRACT,PERMINENT
	}
```
# Derived query methods inside Spring Data JPA (custom methods)
- SpringDataJPA create queries & implementation at runtime automatically by parsing the methods. 

```java
  //find Employees by last name
  List<Employee> findByLastName(String lastName);
  
  //find Employee by email id
  Employee findByEmail(String email);
  
  //find Employee by firstName & LastName
  Employee findByFirstNameAndLastName(String firstName,String lastName);
```
- Add below annotations on main class(optional- if below packages sub packages of main application class package)

```java
@EnableJpaRepositories("com.tech2java.eis.repository")
@EntityScan("com.tech2java.eis.entity")
```

# application.properties

```
spring.datasource.url=jdbc:mysql://localhost:3306/eis
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
 
- Finished Spring Data repository scanning in 46 ms. Found 1 JPA repository interfaces.

# Auditing data

- step1

```java
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	
	@CreatedBy
	@Column(updatable = false)
	private String createdBy;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDt;
		
	@LastModifiedBy
	@Column(insertable = false)
	private String updatedBy;
	@LastModifiedDate
	@Column(insertable = false)
	private LocalDateTime updatedDt;
}
```
-step2 - crate class AuditAwareImpl that implements `AuditorAware<String>`

```java
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		
		return Optional.of("System");
	}

}
```

-step3 - add @EnableJpaAuditing on main application class

```java
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
```
-- save operation json

```
{
    "empName": "Jogn",
    "empEmail": "john@gmail.com",
    "empAddress": "USA",
    "empType": 1
 }
```

# APIS
-http://localhost:8080/api/employees  
-http://localhost:8080/api/employees/saveEmp  
-http://localhost:8080/api/employees/deleteEmp  
-http://localhost:8080/api/employees/updateEmail  
-http://localhost:8080/api/employees/getEmployeeByEmail?empEmail=john@gmail.com
