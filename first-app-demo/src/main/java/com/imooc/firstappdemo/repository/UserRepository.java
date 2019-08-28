package com.imooc.firstappdemo.repository;

/**
 * @Author: Alex
 * @Date: created in 17:05  2019/8/27
 * @Annotation: 类 dao层
 */

import com.imooc.firstappdemo.admain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link User} {@link Repository}
 */
//Repository:仓库，该注解说明该类是Spring组件，所以在SpringIOC容器扫描时会扫描并创建该类的bean
@Repository
public class UserRepository {

    /**
     *采用内存型的存储方式 -> Map
     * 采用内存存储，因为该类是Spring的bean，所以是单例的，为了防止高并发情况，使用ConcurrentMap
     */
    private final ConcurrentMap<Integer,User> repository
            = new ConcurrentHashMap<>();

    private final static AtomicInteger idGenerator
             = new AtomicInteger();

    /**
     * 保存用户对象
     * @param user
     * @return 如果保存成功，返回<code>true</code>
     *          否则返回<code>false</code>
     */
    public boolean save(User user){
        Integer id = idGenerator.incrementAndGet();
        //设置用户id
        user.setId(id);
        return repository.put(id,user) == null;
    }

    public Collection<User> findAll(){
        //返回此映射中值（仅返回值，不返回键）的 Collection
        return repository.values();
    }


}
