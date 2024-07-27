package com.demo.dao;

import com.demo.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

public interface UserDao {

    List<User> selectA(@Param(value="tablename") String tablename,@Param(value="name") String name);


    @SelectProvider(type = UserSqLProvider.class,method = "selectB")
    List<User> selectB(Map<String, Object> params);
}
