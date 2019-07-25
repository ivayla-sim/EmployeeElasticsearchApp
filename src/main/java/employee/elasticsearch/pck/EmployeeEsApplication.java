package employee.elasticsearch.pck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeEsApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(EmployeeEsApplication.class, args);
		System.out.println("Elasticsearch");
		
		
	}

}
