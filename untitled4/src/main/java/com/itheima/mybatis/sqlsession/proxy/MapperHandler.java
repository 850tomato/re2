package com.itheima.mybatis.sqlsession.proxy;

import com.itheima.mybatis.cfg.Mapper;
import com.itheima.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.sql.Connection;
import java.util.Map;

public class MapperHandler implements InvocationHandler {
    private Map<String, Mapper> mappers;
    private Connection conn;
    public MapperHandler(Map<String,Mapper> mappers, Connection conn){
        this.mappers=mappers;
        this.conn=conn;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName=method.getName();
        String className=method.getDeclaringClass().getName();
        String key=className+"."+methodName;
        Mapper mapper=mappers.get(key);
        if(mapper==null){
            throw new IllegalAccessException("error");
        }
        return new Executor().selectList(mapper,conn);

    }
}
