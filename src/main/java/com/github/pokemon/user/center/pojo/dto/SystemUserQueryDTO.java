package com.github.pokemon.user.center.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * TODO
 * <p>
 * create in 2021/1/14 9:27 上午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Data
public class SystemUserQueryDTO implements Serializable {

    private static final long serialVersionUID = -2145835935950242475L;

    private String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastLoginDate;

}
