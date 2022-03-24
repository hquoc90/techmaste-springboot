package vn.techmaster.buoi2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.buoi2.model.Job;
import vn.techmaster.buoi2.model.JobRequest;
import vn.techmaster.buoi2.model.Location;
import vn.techmaster.buoi2.service.JobService;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/all")
    @ResponseBody
    public  List<Job> getListJob(){
        return jobService.getAllJob();
    }
    @GetMapping("/sortbylocation")
    public List<Job> sortByLocation(){
        return jobService.sortByLocation();
    }
    @GetMapping("/salary")
    public List<Job> getJobBySalary(@RequestParam int salary){
        return jobService.findBySalary(salary);
    }
    @GetMapping("/search")
    public List<Job> findJobByKeyword(@RequestParam String keyword){
        return jobService.findByKeyword(keyword);
    }
    @GetMapping("/search/{location}")
    public List<Job> findJobByKeyword(@RequestParam String keyword, @PathVariable String location){
        return jobService.findByKeywordAndLocation(keyword,location);
    }

    @PostMapping
    public ResponseEntity<?> addJob(@RequestBody JobRequest jobRequest){
        jobService.addJob(jobRequest);
        return ResponseEntity.ok("them thanh cong");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@RequestBody JobRequest jobRequest, @PathVariable String id){
        if (jobService.updateJob(jobRequest,id)) {
            return ResponseEntity.ok("thanh cong");
        }
        return ResponseEntity.ok("failed");
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?>  removeJob(@PathVariable String id){
        if (jobService.deleteJob(id)) {
            return ResponseEntity.ok("thanh cong");
        }
        return ResponseEntity.ok("failed");

    }


}
