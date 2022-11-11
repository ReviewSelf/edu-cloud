package net.edu.mini.service.Impl;

import net.edu.framework.common.utils.Result;
import net.edu.mini.dao.WxMiniDao;
import net.edu.mini.service.WxMiniService;
import net.edu.mini.vo.MyLessonVo;
import net.edu.mini.vo.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxMiniServiceImpl implements WxMiniService {

    @Autowired
    WxMiniDao wxMiniDao;

    @Override
    public List<MyLessonVo> getLesson(String time, String userId) {
        System.out.println(time);
        System.out.println(userId);
        List<MyLessonVo> list = wxMiniDao.selectLesson(time,userId);
        System.out.println(list);
        return list;
    }

    @Override
    public MyMessage getMessage(String userId) {
        MyMessage user = wxMiniDao.selectMessage(userId);
        return user;
    }
}
