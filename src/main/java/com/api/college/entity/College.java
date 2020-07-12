package com.api.college.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "college")
public class College {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long collegeid;
	private String collegeName;
	private String collegeAddress;
}