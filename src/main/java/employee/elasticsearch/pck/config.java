package employee.elasticsearch.pck;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
	
	@Value("${elasticsearch.host}")
	public String elasticsearchHost;
	
	@Value("${elasticsearch.port}")
	public int elasticsearchPort;
	
	public String getHost() {
		return elasticsearchHost;
	}
	
	public int getPort() {
		return elasticsearchPort;
	}
	
	@Bean(destroyMethod = "close")
	public RestHighLevelClient client() {
		RestHighLevelClient client = null;
	
			System.out.println("host:" + elasticsearchHost + "port:" + elasticsearchPort);
		
			client = new RestHighLevelClient(RestClient.builder(new HttpHost(elasticsearchHost, elasticsearchPort)));
			
		return client;		
	}	
}
