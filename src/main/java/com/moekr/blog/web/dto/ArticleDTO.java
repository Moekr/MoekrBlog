package com.moekr.blog.web.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ArticleDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String summary;
    @NotBlank
    private String content;
    @NotBlank
    private String category;
    @NotNull
    private List<String> tags;
}