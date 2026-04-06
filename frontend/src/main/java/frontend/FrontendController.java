package frontend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class FrontendController {

    @Value("${backend.url:http://backend-service:8080}")
    private String backendUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String index() {
        String response = restTemplate.getForObject(backendUrl + "/api/hello", String.class);
        return "Frontend received from backend: " + response;
    }

    @GetMapping("/api/hello")
    public String hello() {
        return restTemplate.getForObject(backendUrl + "/api/hello", String.class);
    }

    @GetMapping("/api/heavy")
    public String heavy() {
        return restTemplate.getForObject(backendUrl + "/api/heavy", String.class);
    }

    @GetMapping("/api/kill")
    public void kill() {
        restTemplate.getForObject(backendUrl + "/api/kill", String.class);
    }
}
