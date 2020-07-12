package com.api.college.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.api.college.constants.ErrorConstants;
import com.api.college.entity.College;
import com.api.college.entity.CollegeDTO;
import com.api.college.exception.ValidationException;
import com.api.college.repository.CollegeRepository;

@Service
public class CollegeService {

	
	@Autowired
    CollegeRepository repository;
    public List<College> getAllCollegeName(Integer pageNo, Integer pageSize, String sortBy)
    {
    	
    	Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    	List<College> targetLongList = repository.findAll(paging).filter(x -> x!=null).stream().collect(Collectors.toList());
    	targetLongList.forEach(System.out::println);
    	return targetLongList;
    }
    public List<College> getAllCollegeName()
    {
        List<College> targetLongList = repository.findAll().stream().filter(x -> x!=null).collect(Collectors.toList());
        return targetLongList;
    }
    public List<College> findByCollegeName(String collegeName){
        List<College> list = repository.findByCollegeName(collegeName);
        return list;
    }
    public void createNewCollege(College college)
    {
    	repository.save(college);
    	
    }
    public CollegeDTO getCollegeById(Long id) 
    {
        Optional<College> college = repository.findById(id);
        if(college.isPresent()) {
        	College user = college.get();
        	CollegeDTO l=new CollegeDTO();
        	l.setCollegeName(user.getCollegeName());
        	l.setCollegeAddress(user.getCollegeAddress());
        	 return l;
        } else {
    		throw ValidationException.withMessage("No Students record exist for given id.").andCode(ErrorConstants
                    .COURSE_NOT_AVAILABLE);
    	}
    }
     
    public College createOrUpdateCollege(College entity) 
    {
        Optional<College> college = repository.findById(entity.getCollegeid());
         
        if(college.isPresent()) 
        {
        	College newEntity = college.get();
            newEntity.setCollegeName(entity.getCollegeName());
            newEntity.setCollegeAddress(entity.getCollegeAddress());
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    } 
   
    public void deleteStudentsById(Long id) 
    {
        Optional<College> college = repository.findById(id);
         
        if(college.isPresent()) 
        {
            repository.deleteById(id);
        } 
        else {
    		throw ValidationException.withMessage("No Students record exist for given id.").andCode(ErrorConstants
                    .COURSE_NOT_AVAILABLE);
    	}
    } 
}
