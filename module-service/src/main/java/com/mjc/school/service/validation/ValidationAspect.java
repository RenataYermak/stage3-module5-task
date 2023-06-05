package com.mjc.school.service.validation;

import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.dto.NewsRequestDto;
import com.mjc.school.service.dto.TagRequestDto;
import com.mjc.school.service.exception.ValidationException;
import com.mjc.school.service.validation.annotation.Validate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import static com.mjc.school.service.exception.ErrorCode.NUMBER_VALIDATION;
import static com.mjc.school.service.exception.ErrorCode.STRING_VALIDATION;
import static com.mjc.school.service.validation.constant.Constant.*;

@Aspect
@Component
public class ValidationAspect {

    @Before(value = "@annotation(com.mjc.school.service.validation.annotation.Validate)&&args(id)")
    public void checkId(JoinPoint joinPoint, Long id) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        methodSignature.getMethod();
        Validate validate = methodSignature.getMethod().getAnnotation(Validate.class);
        String value = validate.value();
        if (value.equals("checkId")) {
            validateNumber(id, NEWS_ID);
        }
    }

    @Before(value = "@annotation(com.mjc.school.service.validation.annotation.Validate)&&args(newsRequestDto)")
    public void checkNewsRequestDto(JoinPoint joinPoint, NewsRequestDto newsRequestDto) throws ValidationException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        methodSignature.getMethod();
        Validate validate = methodSignature.getMethod().getAnnotation(Validate.class);
        String value = validate.value();
        if (value.equals("checkNews")) {
            validateString(newsRequestDto.getTitle(), NEWS_ID, NEWS_TITLE_MIN, NEWS_TITLE_MAX);
            validateString(newsRequestDto.getContent(), NEWS_ID, NEWS_CONTENT_MIN, NEWS_CONTENT_MAX);
            checkId(joinPoint, newsRequestDto.getId());
            checkId(joinPoint, newsRequestDto.getAuthorId());
        }
    }

    @Before(value = "@annotation(com.mjc.school.service.validation.annotation.Validate)&&args(authorRequestDto)")
    public void checkAuthorRequestDto(JoinPoint joinPoint, AuthorRequestDto authorRequestDto) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        methodSignature.getMethod();
        Validate validate = methodSignature.getMethod().getAnnotation(Validate.class);
        String value = validate.value();
        if (value.equals("checkAuthor")) {
            validateString(authorRequestDto.getName(), AUTHOR_ID, AUTHOR_NAME_MIN, AUTHOR_NAME_MAX);
        }
    }

    @Before(value = "@annotation(com.mjc.school.service.validation.annotation.Validate)&&args(tagRequestDto)")
    public void checkTagRequestDto(JoinPoint joinPoint, TagRequestDto tagRequestDto) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        methodSignature.getMethod();
        Validate validate = methodSignature.getMethod().getAnnotation(Validate.class);
        String value = validate.value();
        if (value.equals("checkTag")) {
            validateString(tagRequestDto.getName(), TAG_ID, TAG_NAME_MIN, TAG_NAME_MAX);
        }
    }

//    @Before(value = "@annotation(com.mjc.school.service.validation.annotation.Validate)&&args(commentRequestDto)")
//    public void checkContentDtoRequest(JoinPoint joinPoint, CommentRequestDto commentRequestDto) {
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        methodSignature.getMethod();
//        Validate validate = methodSignature.getMethod().getAnnotation(Validate.class);
//        String value = validate.value();
//        if (value.equals("checkComment")) {
//            validateString(commentRequestDto.getContent(), COMMENT_ID, COMMENT_CONTENT_MIN, COMMENT_CONTENT_MAX);
//        }
//    }

    void validateString(String value, String parameter, int minNumber, int maxNumber) throws ValidationException {
        if (value == null) {
            throw new ValidationException(String.format(STRING_VALIDATION.getMessage(), value, parameter));
        }
        if (value.trim().length() < minNumber || value.trim().length() > maxNumber) {
            throw new ValidationException(String.format(STRING_VALIDATION.getMessage(), value, parameter));
        }
    }

    private void validateNumber(Long id, String parameter) {
        if (id == null || id < 1) {
            throw new ValidationException(String.format(NUMBER_VALIDATION.getMessage(), id, parameter));
        }
    }
}