package com.github.pokemon.user.center.security.common;

/**
 * <p>
 * 创建时间为 下午3:09 2019/12/2
 * 项目名称 spring-boot-security
 * </p>
 *
 * @author shishaodong
 * @version 0.0.1
 * @since 0.0.1
 */

public interface SecurityConstant {

    String GET = "GET";

    String POST = "POST";

    String SYSTEM_LOGIN_USERNAME = "/system/login/username";

    String SYSTEM_LOGIN_LOGOUT = "/system/login/logout";

    String SYSTEM_TRIAL_CAPTCHA = "/system/trial/image";

    /**
     * 申请试用页 - 短信验证码校验的路径
     */
    String TRIAL_SMS_CODE_VALIDATE_PATH = "/share/trial/apply";
    /**
     * 申请试用页 - 图形验证码获取和校验接口
     */
    String TRIAL_IMAGE_CODE_VALIDATE_PATH = "/share/image/code";


    String SESSION_KEY_CODE_SMS = "SESSION_KEY_CODE_SMS";

    String SMS_CODE = "smsCode";

    String[] PERMIT_ALL = {SYSTEM_LOGIN_USERNAME, SYSTEM_LOGIN_LOGOUT, SYSTEM_TRIAL_CAPTCHA};

}
