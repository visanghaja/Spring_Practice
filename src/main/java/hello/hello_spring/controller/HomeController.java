package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 처음 localhost8080 에 들어가면 호출됨
    public String home() {
        return "home";
    }
}
