package net.edu.module.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.edu.framework.mybatis.dao.BaseDao;
import net.edu.module.entity.UserEntity;
import net.edu.module.query.UserQuery;
import net.edu.module.vo.TeachPayVO;
import net.edu.module.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author weng
 * @date 2023/1/13 - 13:21
 **/
@Mapper
public interface UserDao extends BaseDao<UserEntity> {

    IPage<UserVO> selectStudentByPage(Page<UserVO> page, UserQuery query);


    default UserEntity getByUsername(String username){
        return this.selectOne(new QueryWrapper<UserEntity>().eq("username", username));
    }

    default UserEntity getByMobile(String mobile){
        return this.selectOne(new QueryWrapper<UserEntity>().eq("mobile", mobile));
    }



    void insertCadet(UserVO vo);

    List<Integer> selectUserStatus();

    String selectStuNumber();

    void returnBack(TeachPayVO vo);
    void updateUserAfterPay(TeachPayVO vo);


    List<UserVO> selectSaleName();
}