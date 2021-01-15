package com.github.pokemon.user.center.pojo.entity.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.AttributeConverter;

/**
 * 密码转换器。
 * 存进去时明文会自动转换成密文，取出来时，密文还是密文
 *
 * @author shishaodong
 * @date 2020-06-16 18:50
 */

@Slf4j
@RequiredArgsConstructor
public class PasswordConverter implements AttributeConverter<String, String> {

    private final PasswordEncoder encoder;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (StringUtils.isNotBlank(attribute)) {
            return encoder.encode(attribute);
        }
        return "";
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }

}
