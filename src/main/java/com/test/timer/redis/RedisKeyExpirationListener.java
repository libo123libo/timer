package com.test.timer.redis;

import com.test.timer.constant.RedisKeysConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * 监听所有失效key
 *
 * @author libo
 * @date 2021/11/28 20:52
 */
@Slf4j
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * redis失效key事件处理
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        super.onMessage(message, pattern);
        // 失效的key
        String expiredKey = message.toString();

        if (expiredKey.startsWith(RedisKeysConstant.TIMER_PREFIX)) {
            log.debug("监听成功！失效key：{}", expiredKey);
        } else {
            log.debug("监听失败！失效key：{}", expiredKey);
        }
    }

}
