package com.github.pokemon.user.center.exception.handler;

import com.github.pokemon.common.support.pojo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * TODO
 * <p>
 * create in 2021/1/14 9:18 下午
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Slf4j
@Order(Integer.MIN_VALUE)
@RestControllerAdvice
public class ParameterExceptionHandler {

    /**
     * PathVariable 参数异常
     *
     * @param exception ConstraintViolationException
     * @return ResultVO
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVO<Void> constraintViolationExceptionHandler(@NotNull ConstraintViolationException exception) {
        return ResultVO.failure();
    }

    /**
     * body 参数异常
     *
     * @param exception MethodArgumentNotValidException
     * @return ResultVO
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultVO<Void> methodArgumentNotValidExceptionHandler(@NotNull MethodArgumentNotValidException exception) {
        return ResultVO.failure();
    }

}
