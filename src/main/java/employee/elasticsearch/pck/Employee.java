package employee.elasticsearch.pck;
import java.util.List;

import lombok.Data;

@Data
public class Employee {

	private String id;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private List<Addresses> addresses;

}
