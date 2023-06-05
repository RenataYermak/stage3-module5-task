package com.mjc.school.service.exception;

public enum ErrorCode {

    BAD_REQUEST("400", "Bad Request"),
    NOT_FOUND("404", "Not Found"),
    NEWS_NOT_EXIST("001", "News with id %d does not exist."),
    AUTHOR_NOT_EXIST("002", "Author with id %d does not exist."),
    TAG_NOT_EXIST("003", "Tag with id %d does not exist."),
    COMMENT_NOT_EXIST("004", "Comment with id %d does not exist."),
    AUTHOR_NOT_EXIST_FOR_NEWS_ID("005", "Author not found for news with id %d."),
    COMMENT_NOT_EXIST_FOR_NEWS_ID("006", "Comment not found for news with id %d."),
    STRING_VALIDATION("007",
            "Text %s does not meet requirements: null, less or more than necessary %s."),
    NUMBER_VALIDATION("008",
            "Number %d does not meet requirements: negative, null, less or more than necessary %s."),
    VALIDATION("009", "Validation failed: %s");

    private final String errorMessage;

    ErrorCode(String errorCode, String message) {
        this.errorMessage = String.format("Error code: %s, Error message: %s", errorCode,message);
    }

    public String getMessage() {
        return errorMessage;
    }
}
