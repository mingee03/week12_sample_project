package edu.handong.week12.book.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record BookRequest(
        @NotBlank(message = "No title")
        String title,
        @NotBlank(message = "No author")
        String author,
        @Min(value = 0, message = "Price must be >= 0")
        int price
){}