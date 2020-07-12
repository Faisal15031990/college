package com.api.college.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollegeDTO implements Serializable{
	private String collegeName;
	private String collegeAddress;
}
