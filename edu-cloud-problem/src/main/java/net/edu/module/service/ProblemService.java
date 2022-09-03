package net.edu.module.service;

import net.edu.framework.common.page.PageResult;

import java.util.List;

/**
 * 问题共同方法接口
 * @param <T>
 * @param <R>
 */

public interface ProblemService<T,R> {

     /**
      * 新增问题
      * @param object
      */
     void savaProblem(T object);

     /**
      * 删除问题
      * @param problemId
      */
     void delProblem(Integer problemId);

     /**
      * 批量删除问题
      * @param problemIdList
      */
     void delProblemBatch(List<Integer> problemIdList);

     /**
      * 更新问题
      * @param object
      */
     void updateProblem(T object);

     /**
      * 获取单个问题信息
      * @param problemId
      * @return
      */
     T getProblemDetail(Integer problemId);

     /**
      * 分页查询
      * @param query
      * @return
      */
     PageResult<T>  getProblemPage(R query);

     /**
      * 修改状态
      * @param problemId
      * @param stateCode
      */
     void changeState(Integer problemId,Integer stateCode);

     /**
      * 批量修改状态
      * @param problemIdList
      * @param stateCode
      */
     void changeStateBatch(List<Integer> problemIdList,Integer stateCode);
}
