package com.api.college.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.college.constants.CacheConstants;
import com.api.college.entity.College;
import com.api.college.entity.CollegeDTO;
import com.api.college.service.CollegeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "CollegeController", description = "REST Apis related to College Entity!!!!")
@RestController
public class CollegeController {


	@Autowired
	CollegeService service;
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"), 
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    
    @GetMapping("/college/pegination")
    public ResponseEntity<List<College>> getAllCollegeName(@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "2") Integer pageSize,
            @RequestParam(defaultValue = "studenName") String sortBy) {
        List<College> list = service.getAllCollegeName(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<College>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/college")
    public ResponseEntity<List<College>> getAllCollegeName() {
        List<College> list = service.getAllCollegeName();
        return new ResponseEntity<List<College>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/college/{id}")
    @Cacheable(value = CacheConstants.STUDENT_COLLEGE_CACHE,key = "{#id}")
    public CollegeDTO getCollegeById(@PathVariable("id") Long id){
    	CollegeDTO entity = service.getCollegeById(id);
    		return entity;
    }
 
    @PostMapping("/college/{id}")
    public ResponseEntity<College> createOrUpdateCollege(@RequestBody  College college) {
    	College updated = service.createOrUpdateCollege(college);
        return new ResponseEntity<College>(updated, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping("/college")
    public ResponseEntity<College> createNewCollege(@RequestBody College college) {
        service.createNewCollege(college);
        return new ResponseEntity<College>(new HttpHeaders(), HttpStatus.OK);
    }
    @DeleteMapping("/college/{id}")
    public HttpStatus deleteCollegeById(@PathVariable("id") Long id){
        service.deleteStudentsById(id);
        return HttpStatus.ACCEPTED;
    }
    @GetMapping("/college/name/{collegeName}")
    public ResponseEntity<List<College>> findByCollegeName(@PathVariable("collegeName") String mobileNo){
    	List<College> list = service.findByCollegeName(mobileNo);
    	return new ResponseEntity<List<College>>(list, new HttpHeaders(), HttpStatus.OK);
    }

}
