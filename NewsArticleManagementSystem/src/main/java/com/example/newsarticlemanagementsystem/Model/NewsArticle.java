package com.example.newsarticlemanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewsArticle {
    @NotEmpty(message = "ID can't be null.")
    private String ID;
    @NotEmpty(message = "Title can't be null.")
    @Size(max = 100, message = "Maximum length of title should be 100 characters.")
    private String title;
    @NotEmpty(message = "Author can't be null.")
    @Size(min = 5, max = 20, message = "Author must be between 5-20 characters.")
    private String author;
    @NotEmpty(message = "Content can't be null.")
    @Size(min = 201, message = "Content must be more than 200 characters.")
    private String content;
    @NotEmpty(message = "Category can't be null.")
    @Pattern(regexp = "^(?i)(Politics|Sports|Technology)$" , message = "Category Must be either 'politics', 'sports' or 'technology' only.")
    private String category;
    @NotEmpty(message = "Image url can't be null.")
    private String imageUrl;
    private boolean isPublished = false;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate publishDate;
}