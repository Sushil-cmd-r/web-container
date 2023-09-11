package com.company.mapper;

public interface Mapper<T> {

    T parse(String src) throws Exception;
}
