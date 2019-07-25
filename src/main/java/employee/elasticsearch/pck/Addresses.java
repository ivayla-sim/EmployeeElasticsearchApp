package employee.elasticsearch.pck;
import lombok.Data;

@Data
public class Addresses {
	
	private String country;
	private String city;
	private String villageType;
	private String street;
	private int zipCode;

}
