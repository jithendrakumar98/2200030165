package com.example.Calcuator.Controller;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class Average {
 @GetMapping("/")
    public String getMethodName() {
    return "Hiiiiiii!";
    }
private final String token = 
"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJNYXBDbGFpbXMiOnsiZXhwIjoxNzQ3ODk1ODc0LCJpYXQiOjE3NDc4OTU1NzQsImlzcyI6IkFmZm9yZG1lZCIsImp0aSI6IjkyZTQ0MjAzLWRkZDYtNGRhYy1hZjAzLTk4YmRkZGZhODlhZSIsInN1YiI6IjIyMDAwMzAxNjVjc2VoQGdtYWlsLmNvbSJ9LCJlbWFpbCI6IjIyMDAwMzAxNjVjc2VoQGdtYWlsLmNvbSIsIm5hbWUiOiJhcnRoaW1hbGxhIGppdGhlbmRyYSBrdW1hciIsInJvbGxObyI6IjIyMDAwMzAxNjUiLCJhY2Nlc3NDb2RlIjoiYmVUSmpKIiwiY2xpZW50SUQiOiI5MmU0NDIwMy1kZGQ2LTRkYWMtYWYwMy05OGJkZGRmYTg5YWUiLCJjbGllbnRTZWNyZXQiOiJLUXBaQURSeU1FcHRoZEJrIn0.r9yFPjG_he7c9VO7GbSTSzyCQbnnuZM4OK-oA7hRdg8";
 String even = "http://20.244.56.144/evaluation-service/even";
String prime = "http://20.244.56.144/evaluation-service/primes";
 String fibn = "http://20.244.56.144/evaluation-service/fibo";
 String rand = "http://20.244.56.144/evaluation-service/rand";


 RestTemplate RT = new RestTemplate();
 @GetMapping("/numbers/e")
    public Map<String, Object> Even() {
        return AVG(even);
    }

@GetMapping("/numbers/p")
    public Map<String, Object> Prime() {
        return AVG(prime);
    }
    @GetMapping("/numbers/f")
    public Map<String, Object> FibnO() {
        return AVG(fibn);
    }
    @GetMapping("/numbers/r")
    public Map<String, Object> Random() {
        return AVG(rand);
    }
      private Map<String, Object> AVG(String url) {
        HttpHeaders headers = new HttpHeaders();
headers.set("Authorization", "Bearer " + token); 
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = RT.exchange(
                url,
                HttpMethod.GET,
                entity,
                Map.class
        );
        
        List<Integer> numbers = (List<Integer>) response.getBody().get("numbers");
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("windowPrevState", new ArrayList<>());
        result.put("windowCurrState", numbers);
        result.put("numbers", numbers);
        result.put("avg", calAVG(numbers));
        return result;
    }
    private double calAVG(List<Integer> numbers) {
        int sum = 0, count = 0;
        for (int num : numbers) {
            if (num % 2 == 0) {
                sum += num;
                count++;
            }
        }
        return count == 0 ? 0.0 : (double) sum / count;
    }

}

    