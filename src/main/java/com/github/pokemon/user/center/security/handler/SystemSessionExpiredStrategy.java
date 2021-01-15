package com.github.pokemon.user.center.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pokemon.common.support.pojo.vo.ResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异地登录处理
 *
 * @author 杜家浩
 * @date 2020/4/26 14:17
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SystemSessionExpiredStrategy implements SessionInformationExpiredStrategy {

    private final ObjectMapper objectMapper;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException {
        log.info("用户在异地登录，请重新登录！");
        HttpServletResponse response = sessionInformationExpiredEvent.getResponse();
        ResultVO<Void> result = ResultVO.failure(403, "用户在异地登录，请重新登录！");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(objectMapper.writeValueAsString(result));
        response.getWriter().flush();
    }
}
