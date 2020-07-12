package com.api.college.aspect;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import com.api.college.entity.College;
import com.api.college.util.CacheUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Configuration
@Slf4j
@RequiredArgsConstructor
public class CollegeAspect {

	private final CacheUtil cacheUtil;

	@AfterReturning("execution(* com.api.college.controller.CollegeController.createOrUpdateCollege(..))")
	public void afterReturningCreateOrUpdateCollege(JoinPoint joinPoint) {
		Map<String, Object> params = getParams(joinPoint);
		College college = params.get("college") == null ? null : (College) params.get("college");
		Long colId = college.getCollegeid();
		System.out.println(colId);
		deleteCache(colId);
	}
	@Before("execution(* com.api.college.controller.CollegeController.deleteCollegeById(..))")
    public void afterReturningDeleteQnaAnswerComment(JoinPoint joinPoint) {
        Map<String, Object> params=getParams(joinPoint);
        Long colId = params.get("id") == null ? null : (Long) params.get("id");
        deleteCache(colId);
    }

	private void deleteCache(Long colId) {
		if (Objects.nonNull(colId)) {
			String pattern = "college*" + colId + "*";
			cacheUtil.deleteByPattern(pattern);
		}
	}
	/*private void deleteCache(Map<String, Object> params) {
		Long collegeid = params.get("collegeid") == null ? null : (Long) params.get("collegeid");
		if (Objects.nonNull(collegeid)) {
			String pattern = "college*" + collegeid + "*";
			cacheUtil.deleteByPattern(pattern);
		}
	}*/

	private Map<String, Object> getParams(JoinPoint joinPoint) {
		Map<String, Object> params = new HashMap<>();
		String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
		Object[] values = joinPoint.getArgs();
		if (argNames.length != 0) {
			for (int i = 0; i < argNames.length; i++) {
				params.put(argNames[i], values[i]);
			}
		}
		return params;
	}

}