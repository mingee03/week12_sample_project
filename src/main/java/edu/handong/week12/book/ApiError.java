package edu.handong.week12.book;

public record ApiError(
        String code,
        String message
) {}