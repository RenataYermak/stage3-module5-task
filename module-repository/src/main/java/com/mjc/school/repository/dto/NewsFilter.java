package com.mjc.school.repository.dto;

import java.util.List;

public record NewsFilter(
        String title,
        String content,
        String authorName,
        List<String> tagNames,
        List<Long> tagIds) {
}
