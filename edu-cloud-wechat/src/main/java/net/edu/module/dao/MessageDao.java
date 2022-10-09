package net.edu.module.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author weng
 * @date 2022/9/26 - 15:32
 **/
@Mapper
public interface MessageDao {


    String selectUserOpenIdById(Long id);
}
