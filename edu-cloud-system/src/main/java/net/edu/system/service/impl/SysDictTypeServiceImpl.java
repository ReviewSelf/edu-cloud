package net.edu.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.page.PageResult;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.mybatis.service.impl.BaseServiceImpl;
import net.edu.system.convert.SysDictTypeConvert;
import net.edu.system.dao.SysDictDataDao;
import net.edu.system.dao.SysDictTypeDao;
import net.edu.system.entity.SysDictDataEntity;
import net.edu.system.entity.SysDictTypeEntity;
import net.edu.system.query.SysDictTypeQuery;
import net.edu.system.service.SysDictTypeService;
import net.edu.system.vo.SysDictTypeVO;
import net.edu.system.vo.SysDictVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典类型
 *
 * @author 阿沐 babamu@126.com
 */
@Service
@AllArgsConstructor
public class SysDictTypeServiceImpl extends BaseServiceImpl<SysDictTypeDao, SysDictTypeEntity> implements SysDictTypeService {
    private final SysDictDataDao sysDictDataDao;

    private final RedisUtils redisUtils;

    @Override
    public PageResult<SysDictTypeVO> page(SysDictTypeQuery query) {
        IPage<SysDictTypeEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));
        return new PageResult<>(SysDictTypeConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }

    private Wrapper<SysDictTypeEntity> getWrapper(SysDictTypeQuery query){
        LambdaQueryWrapper<SysDictTypeEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(query.getDictType()), SysDictTypeEntity::getDictType, query.getDictType());
        wrapper.like(StrUtil.isNotBlank(query.getDictName()), SysDictTypeEntity::getDictName, query.getDictName());
        wrapper.orderByAsc(SysDictTypeEntity::getSort);

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysDictTypeVO vo) {
        SysDictTypeEntity entity = SysDictTypeConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);
        redisUtils.del(RedisKeys.getDict());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictTypeVO vo) {
        SysDictTypeEntity entity = SysDictTypeConvert.INSTANCE.convert(vo);

        updateById(entity);
        redisUtils.del(RedisKeys.getDict());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);
        redisUtils.del(RedisKeys.getDict());
    }

    @Override
    public List<SysDictVO> getDictList() {
        List<SysDictVO> sysDictVOS=null;

        sysDictVOS= (List<SysDictVO>) redisUtils.get(RedisKeys.getDict());
        if(sysDictVOS==null){
            // 全部字典类型列表
            List<SysDictTypeEntity> typeList = this.list(Wrappers.emptyWrapper());

            // 全部字典数据列表
            QueryWrapper<SysDictDataEntity> query = new QueryWrapper<SysDictDataEntity>().orderByAsc("sort");
            List<SysDictDataEntity> dataList = sysDictDataDao.selectList(query);

            // 全部字典列表
            sysDictVOS = new ArrayList<>(typeList.size());
            for (SysDictTypeEntity type : typeList){
                SysDictVO dict = new SysDictVO();
                dict.setDictType(type.getDictType());

                for (SysDictDataEntity data : dataList){
                    if(type.getId().equals(data.getDictTypeId())){
                        dict.getDataList().add(new SysDictVO.DictData(data.getDictLabel(), data.getDictValue()));
                    }
                }

                sysDictVOS.add(dict);
            }
            redisUtils.set(RedisKeys.getDict(),sysDictVOS,RedisUtils.HOUR_SIX_EXPIRE);
        }
        return sysDictVOS;
    }

}