@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return " hello Spring Boot !";
    }
}