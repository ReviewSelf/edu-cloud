package net.edu.framework.security.cache;

import lombok.AllArgsConstructor;

import net.edu.framework.common.cache.RedisKeys;
import net.edu.framework.common.utils.RedisUtils;
import net.edu.framework.security.user.UserDetail;
import org.springframework.stereotype.Component;



/**
 * 认证 Cache
 *
 * @author 阿沐 babamu@126.com
 */
@Component
@AllArgsConstructor
public class TokenStoreCache {
    private final RedisUtils redisUtils;

    public void saveUser(String accessToken, UserDetail user) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        redisUtils.delByPre(RedisKeys.getAccessTokenKey(null)+user.getUsername()+"&");
        redisUtils.set(key,user);
    }

    public UserDetail getUser(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        return (UserDetail) redisUtils.get(key);
    }

    public void deleteUser(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        redisUtils.del(key);
    }
}
