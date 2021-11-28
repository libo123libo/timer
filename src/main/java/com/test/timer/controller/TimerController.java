package com.test.timer.controller;

import com.test.timer.Utils.RedisUtils;
import com.test.timer.constant.RedisKeysConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 定时器控制器
 *
 * @author libo
 * @date 2021/11/28 20:58
 */
@Slf4j
@RestController
@RequestMapping("/timer")
public class TimerController {

    /**
     * 设置定时器
     *
     * @param invalidTime 失效时间
     */
    @GetMapping("/setTimer")
    public void setTimer(@RequestParam("invalidTime") Long invalidTime) {
        String key = RedisKeysConstant.TIMER_PREFIX + invalidTime;
        log.debug("设置定时器，入参：{}", invalidTime);
        RedisUtils.set(key, null, invalidTime);
        log.debug("设置定时器成功！，出参：{}", key);
    }

}
