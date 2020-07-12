package com.api.college.redis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

public class CustomCacheErrorHandler implements CacheErrorHandler{
	
	private static final Logger log = LoggerFactory.getLogger(CustomCacheErrorHandler.class);
	
    @Override
    public void handleCacheGetError(RuntimeException exception, 
                                    Cache cache, Object key) {
    	log.error("Error in handleCacheGetError",exception);
    }
    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, 
                                    Object key, Object value) {
    	log.error("Error in handleCachePutError",exception);
    }
    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, 
                                      Object key) {
    	log.error("Error in handleCacheEvictError",exception);
    }
    @Override
    public void handleCacheClearError(RuntimeException exception,Cache cache){
    	log.error("Error in handleCacheClearError",exception);
    }
}