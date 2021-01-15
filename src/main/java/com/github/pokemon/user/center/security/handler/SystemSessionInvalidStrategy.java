package com.github.pokemon.user.center.security.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pokemon.common.support.pojo.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录超时处理
 *
 * @author shishaodong
 * @date 2020/4/26 14:10
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SystemSessionInvalidStrategy implements InvalidSessionStrategy {

    private final ObjectMapper objectMapper;

    @Override
    public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        log.info("用户登录超时，请重新登录！");
        ResultVO<Void> result = ResultVO.failure(403, "用户登录超时，请重新登录！");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.getWriter().print(objectMapper.writeValueAsString(result));
    }

}
