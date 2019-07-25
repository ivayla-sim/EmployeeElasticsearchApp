package employee.elasticsearch.pck;
import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
//@Slf4j
public class EmployeeEsService {
	
	private static final String INDEX = "employees";
	//private static final String TYPE_GET = "_doc";
	//private static final String TYPE_POST = "_create";
	private static final String TYPE_PUT = "_doc";
	
	@Autowired
	private RestHighLevelClient client;
	
	private ObjectMapper objectMapper;
	
	@Autowired
	public EmployeeEsService(RestHighLevelClient client, ObjectMapper objectMapper) {
		this.client = client;
		this.objectMapper = objectMapper;
	}
	
	
	public String createEmployeeDoc(Employee employee) throws IOException {
		System.out.println ("Start Service layer POST");
		//UUID uuid = UUID.randomUUID();
	
		//employee.setEsId(Integer.toString(employeeCDTO.getId()));
		
		Map<String, Object> documentMapper = objectMapper.convertValue(employee, Map.class);
		
		//the constructor is deprecated
		//IndexRequest indexRequest = new IndexRequest(INDEX, TYPE_POST, employee.getId()).source(documentMapper);
		
		IndexRequest indexRequest = new IndexRequest(INDEX)
				.id(employee.getId())
				.source(documentMapper);
		
		IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
		
		System.out.println ("End Service layer POST");
		
		return indexResponse
				.getResult()
				.name();
		
	}
	
	public Employee findById (String id) throws IOException {
		System.out.println ("Start Service layer GET");
		
		//the constructor is deprecated
		//GetRequest getRequest = new GetRequest(INDEX, TYPE_GET, id);
		
		GetRequest getRequest = new GetRequest(INDEX).id(id);
		
		GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
		
		Map<String, Object> resultMap = getResponse.getSource();
		
		System.out.println ("End Service layer GET");
		
		return objectMapper.convertValue(resultMap, Employee.class);
		
	}
	
	public String updateEmployeeDoc (Employee employee) throws IOException {
		
		System.out.println("Start Service layer PUT");
		
		Employee resultEmployee = findById(employee.getId());
		
		UpdateRequest updateRequest = new UpdateRequest().index(INDEX).id(employee.getId());
		
		Map<String, Object> documentMapper = objectMapper.convertValue(employee, Map.class);
		
		updateRequest.doc(documentMapper);
		
		UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
		
		System.out.println("End Service layer PUT");
		
		return updateResponse.getResult().name();	
		
	}
	

}
