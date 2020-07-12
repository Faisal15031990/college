//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.api.college.redis;

import org.springframework.cache.Cache;
import org.springframework.lang.Nullable;

public interface CacheErrorHandler {
    void handleCacheGetError(RuntimeException var1, Cache var2, Object var3);

    void handleCachePutError(RuntimeException var1, Cache var2, Object var3, @Nullable Object var4);

    void handleCacheEvictError(RuntimeException var1, Cache var2, Object var3);

    void handleCacheClearError(RuntimeException var1, Cache var2);
}