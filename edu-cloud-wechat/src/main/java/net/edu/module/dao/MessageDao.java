package net.edu.module.dao;

import net.edu.module.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author weng
 * @date 2022/9/26 - 15:32
 **/
@Mapper
public interface MessageDao {

    void insertId(String openId);

    void save( UserEntity userEntity );

    void insertClassUser(Integer classId, String openId);
}
