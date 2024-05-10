package com.fang.springboot.common.functions_module.redis.service;

import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author shaobin
 * @date 2024/5/10 16:23
 */
public class RedisService {
    private RedisTemplate<Serializable, Serializable> template;

    public RedisTemplate<Serializable, Serializable> getTemplate() {
        return template;
    }

    public void setTemplate(RedisTemplate<Serializable, Serializable> template) {
        this.template = template;
    }

    /**
     * 往缓存里放数据
     * @param key
     * @param value
     */
    public void put(final String key, final Serializable value) {
        this.getTemplate().opsForValue().set(key,value);
    }

    /**
     * 往缓存里放数据并指定过期时间
     * @param key
     * @param value
     * @param expiredDelay
     */
    public void put(final String key, final Serializable value, final long expiredDelay) {
        this.getTemplate().opsForValue().set(key,value,expiredDelay, TimeUnit.SECONDS);
    }

    /**
     * 从缓存里取数据
     * @param key
     * @return
     */
    public Serializable get(final String key) {
        return this.getTemplate().opsForValue().get(key);
    }

    /**
     * 删除缓存
     * @param key
     */
    public void remove(final String key) {
        this.getTemplate().delete(key);
    }

    /**
     * 指定过期时间
     * @param key
     * @param expiredDelay
     */
    public void expire(final String key, final long expiredDelay){
        this.getTemplate().expire(key,expiredDelay,TimeUnit.SECONDS);
    }

    /**
     * 执行keys操作
     * @param pattern
     * @return
     */
    public Set<Serializable> keys(final Serializable pattern){
        return this.getTemplate().keys(pattern);
    }
}
