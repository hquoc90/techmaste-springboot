package vn.techmaster.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.spring.model.Book;
import vn.techmaster.spring.model.Student;
import vn.techmaster.spring.model.request.CreateUserReq;
import vn.techmaster.spring.model.request.UserReq;
import vn.techmaster.spring.service.WebService;

import java.util.List;

@Controller
@RequestMapping("/")
public class WebController {
    @Autowired
    private WebService webService;
    @GetMapping(value="/hi")
    @ResponseBody
    public String hello(){
        return "hello";
    }
    @GetMapping(value="/book", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Book getBook(){
        return new Book("hoang", "quoc", 10);
    }
    @GetMapping(value="/random/{length}")
    @ResponseBody
    public String getRandomString(@PathVariable("length") int length){
        return webService.randomString(length);

    }
    @GetMapping(value="/quote")
    @ResponseBody
    public String getQuote(){
        return  webService.randomQuote();

    }
    @PostMapping(value="/bmi")
    @ResponseBody
    public float getBmi(@RequestBody UserReq userReq){
        return webService.bmi(userReq);
    }
    @GetMapping(value="/list-student")
    @ResponseBody
    public List<Student> getListStudent(){
        return  webService.getListStudent();

    }
    @PostMapping(value="/add-student")
    @ResponseBody
    public void createStudent(@RequestBody CreateUserReq createUserReq){
        webService.addStudent(createUserReq);
    }
}
