# Sorting
- PagingAndSortingRepository
- Static 
  List<Employee> list=findByOrderByEmpAgeDesc();
  
  // List<Employee> list=employeeRepository.findByOrderByEmpNameDesc();
    List<Employee> list = employeeRepository.findByOrderByEmpName();
		
- Dynamic
  Sort sort=Sort.by("empAge").descending()
                .and(Sort.by("empSal"))  
  initially- sort will happen based on empAge descending- on top of it-> sort by empSal ascending.    
  send columns names from UI application and construct the Sort object based on requirement.  
  List<Employee> list = employeeRepository.findAll(Sort.by("empName").descending());
           
# Pagination