package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc") // GetMapping 은 직접 url 에다가 쳐서 들어가야..
    public String helloMvc(@RequestParam("name") String name, Model model) { // model 에 담아서 view 에서 렌더링할때 활용할 수 있도록
        //http://localhost:8080/hello-mvc?name=spring!
        model.addAttribute("name", name); // 앞에 있는게 key
        return "hello-template"; //templates 에 있는 hello-template.html 로 return
    }

    @GetMapping("hello-string")
    @ResponseBody // html의 body 부분에 return 내용을 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
        // view (html) 없이 그냥 그대로 내려줌
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        // Getter and Setter 단축키 alt + Ins
    }
    // {"name":"spring!"} 이런 식으로 출력 (JSON)
}
