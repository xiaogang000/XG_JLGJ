package com.demo.dao;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

public class UserSqLProvider {
    public String selectB(final Map<String, Object> params){
        return new SQL(){{
            SELECT("*");
            FROM("sqldemo.user");
            WHERE( "`"+params.get("tablename")+"` = #{name}");
        }}.toString();
    }
}
