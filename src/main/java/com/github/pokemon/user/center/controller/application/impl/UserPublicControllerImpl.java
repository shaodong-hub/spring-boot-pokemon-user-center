package com.github.pokemon.user.center.controller.application.impl;

import com.github.pokemon.user.center.controller.application.IUserPublicController;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 * <p
 * create in 2021/1/14 7:24 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */


@RestController
@RequestMapping("/user/public")
@PreAuthorize("permitAll()")
@RequiredArgsConstructor
public class UserPublicControllerImpl implements IUserPublicController {
}
