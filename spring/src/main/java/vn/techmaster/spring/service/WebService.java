package vn.techmaster.spring.service;

import org.springframework.stereotype.Service;
import vn.techmaster.spring.model.Student;
import vn.techmaster.spring.model.request.CreateUserReq;
import vn.techmaster.spring.model.request.UserReq;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WebService {
    public  String randomString(int length){
        byte[] array = new byte[length]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }

    public String randomQuote(){
        Random rand = new Random(); //instance of random class
        int upperbound = 5;
        //generate random values from 0-4
        int int_random = rand.nextInt(upperbound);
        switch (int_random){
            case 0:
                return "Kiến tha lâu đầy tổ";
            case 1:
                return "Có công mài sắt, có ngày nên kim";
            case 2:
                return "Không thầy đố mày làm nên";
            case 3:
                return "Học thầy không tày học bạn";
        }

    return  null;
    }

    //caculate BMI
    public float bmi(UserReq userReq){
        float rs;
        rs= userReq.getWeight()/ (userReq.getHeight()*2);
        return rs;
    }
    ArrayList<Student> users=new ArrayList<Student>();
    public List<Student> getListStudent(){

        return users;

    }
    public void addStudent(CreateUserReq createUserReq){
        Student s=new Student();
        s.setName(createUserReq.getName());
        s.setAddress(createUserReq.getAddress());
        s.setAge(createUserReq.getAge());
        users.add(s);
    }

}
