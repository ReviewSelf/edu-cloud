package net.edu.module.service;

import net.edu.framework.common.utils.EncryptUtils;
import net.edu.framework.common.utils.RedisUtils;
import org.springframework.stereotype.Service;

@Service
public class PreviewResourceService {
    final RedisUtils redisUtils;

    public PreviewResourceService(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }


    public String getFileContent(String path) {
        String str=null;
        str= (String) redisUtils.get(path, RedisUtils.HOUR_ONE_EXPIRE);
        if(str==null){
            str= EncryptUtils.getFileBase64(path);
            redisUtils.set(path,str,RedisUtils.HOUR_ONE_EXPIRE);
        }
        return str;
    }
}
