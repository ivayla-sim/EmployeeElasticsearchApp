package employee.elasticsearch.pck;
import java.io.IOException;

//import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
//import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController/*(value = "/employees")*/
@RequestMapping(value = "/employees")
public class EmployeeESController {
	
	//private static final String INDEX = "employees";
	//private static final String TYPE = "employee";
	
	//@Autowired
	//private RestHighLevelClient client;
	
	//@Autowired
	private EmployeeEsService employeeEsService;
	
	@Autowired
	public EmployeeESController(EmployeeEsService employeeEsService) {
		this.employeeEsService = employeeEsService;
	}
	
	@PostMapping("/_create")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws IOException {
		
		return new ResponseEntity(employeeEsService.createEmployeeDoc(employee), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/_doc/{id}")
	public Employee findById(@PathVariable String id) throws IOException {
		return employeeEsService.findById(id);
	}
	
	@PutMapping
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) throws IOException {
		return new ResponseEntity(employeeEsService.updateEmployeeDoc(employee), HttpStatus.OK);
	}
	
	
	/*
	public String create(@RequestBody EmployeeCDTO employeeCDTO) throws IOException {
		/*
		IndexResponse response = client.prepareIndex("employees", "employee", employeeCDTO.getId())
				.setSource(jsonBuilder()
						.startObject()
						.field("firstName", employeeCDTO.getFirstName())
						.field("lastName", employeeCDTO.getLastName())
						.field("addresses", employeeCDTO.getAddresses())
						.endObject()
						)
				.get();
				/
		CreateIndexRequest createIndexRequest = new CreateIndexRequest(INDEX);
		
		XContentBuilder builder = XContentFactory.jsonBuilder();
		
		builder.startObject();
		{
			builder.startObject("employees");{
				builder.startObject("employee");
				{
					builder.field("id");
					builder.field("lastName");	
					builder.field("firstName");
					builder.field("lastName");				
				}
				builder.endObject();
				builder.startObject("address");
				{
					builder.field("country");
					builder.field("city");	
					builder.field("villageType");		
					builder.field("street");	
					builder.field("zipCode");		
				}
				builder.endObject();
			}
			builder.endObject();			
		}
		builder.endObject();
		
		//createIndexRequest.mapping("employees", builder);
		createIndexRequest.mapping(INDEX, builder);
		createIndexRequest.timeout(TimeValue.timeValueMinutes(1));
		
		
		client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
		//AcknowledgedResponse acknowledgedResponse = client.indices().putMapping(createIndexRequest, RequestOptions.DEFAULT);
		
		//AcknowledgedResponse acknowledgedResponse = client.indices().put
		
		return "OK";
		
		
		
		
		
		
		
		
	}
*/

}
