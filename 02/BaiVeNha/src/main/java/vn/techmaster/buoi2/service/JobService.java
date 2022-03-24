package vn.techmaster.buoi2.service;

import org.springframework.stereotype.Service;
import vn.techmaster.buoi2.model.Job;
import vn.techmaster.buoi2.model.JobRequest;
import vn.techmaster.buoi2.model.Location;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class JobService {
    private ConcurrentHashMap<String, Job> listJobs;


    public JobService() {
        listJobs = new ConcurrentHashMap<>();
        Job j1 = new Job("00-11", "Tuyen LTV", "Lap trinh vien python", Location.DaNang, 1000, 1500, "quochoang@gmai.com");
        Job j2 = new Job("00-13", "Tuyen tester", "Tuyen manual tester python", Location.HaNoi, 1200, 1700, "quochoang@gmai.com");
        Job j3 = new Job("00-12", "Tuyen BA", "Tuyen BA 4 nam kinh nghiem java tester", Location.HaiPhong, 800, 1000, "quochoang@gmai.com");
        Job j4 = new Job("00-10", "Tuyen PM", "PM quan tri du an ", Location.HoChiMinh, 2000, 2500, "quochoang@gmai.com");
        listJobs.put("00-11", j1);
        listJobs.put("00-12", j2);
        listJobs.put("00-13", j3);
        listJobs.put("00-14", j4);
    }

    public List<Job> getAllJob() {
        List<Job> result = new ArrayList<Job>(listJobs.values());
        return result;
    }

    public Job addJob(JobRequest jobRequest) {
        String uuid = UUID.randomUUID().toString();
        Job j = new Job();
        j.setId(uuid);
        j.setTitle(jobRequest.getTitle());
        j.setDescription(jobRequest.getDescription());
        j.setLocation(jobRequest.getLocation());
        j.setEmailTo(jobRequest.getEmailTo());
        j.setMaxSalary(jobRequest.getMaxSalary());
        j.setMinSalary(jobRequest.getMinSalary());
        listJobs.put(uuid, j);
        return j;
    }

    public boolean updateJob(JobRequest jobRequest, String id) {
        if (listJobs.get(id) != null) {
            Job j = new Job();
            j = listJobs.get(id);
            j.setTitle(jobRequest.getTitle());
            j.setDescription(jobRequest.getDescription());
            j.setLocation(jobRequest.getLocation());
            j.setEmailTo(jobRequest.getEmailTo());
            j.setMaxSalary(jobRequest.getMaxSalary());
            j.setMinSalary(jobRequest.getMinSalary());
            return true;
        }
        return false;
    }

    public boolean deleteJob(String id) {
        if (listJobs.get(id) != null) {
            listJobs.remove(id);
            return true;
        }
        return false;
    }

    public List<Job> sortByLocation() {
        List<Job> rs = new ArrayList<Job>(listJobs.values());
        Collections.sort(rs, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.getLocation().toString().compareTo(o2.getLocation().toString());
            }
        });
        return rs;
    }

    public List<Job> findBySalary(int salary) {
        List<Job> list = new ArrayList<Job>(listJobs.values());
        List<Job> result = new ArrayList<>();
        for (Job job :
            list) {
            if (job.getMinSalary() <= salary && job.getMaxSalary() >= salary) {
                result.add(job);
            }
        }
        return result;

    }

    public List<Job> findByKeyword(String keyword) {
        List<Job> list = new ArrayList<Job>(listJobs.values());
        List<Job> result = new ArrayList<>();
        for (Job job :
            list) {
            if (job.getTitle().toLowerCase().contains(keyword.toLowerCase()) || job.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(job);
            }
        }
        return result;

    }

    public List<Job> findByKeywordAndLocation(String keyword, String location) {
        List<Job> list = new ArrayList<Job>(listJobs.values());
        List<Job> result = new ArrayList<>();
        for (Job job :
            list) {
            if ((job.getTitle().toLowerCase().contains(keyword.toLowerCase()) || job.getDescription().toLowerCase().contains(keyword.toLowerCase())) & job.getLocation().toString().toLowerCase().equals(location.toLowerCase())) {
                result.add(job);
            }
            return result;
        }
        return  null;
    }
}
