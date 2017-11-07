package com.moekr.blog.web.controller.api;

import com.moekr.blog.logic.service.ArticleService;
import com.moekr.blog.util.ToolKit;
import com.moekr.blog.web.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/articles")
    public Map<String, Object> createArticle(@RequestBody @Valid ArticleDto articleDto){
        return ToolKit.assemblyResponseBody(articleService.createArticle(articleDto));
    }

    @GetMapping("/articles")
    public Map<String, Object> getArticles(){
        return ToolKit.assemblyResponseBody(articleService.getArticles());
    }

    @GetMapping("/article/{articleId}")
    public Map<String, Object> getArticle(@PathVariable int articleId){
        return ToolKit.assemblyResponseBody(articleService.getArticle(articleId));
    }

    @PutMapping("/article/{articleId}")
    public Map<String, Object> updateArticle(@PathVariable int articleId, @RequestBody @Valid ArticleDto articleDto){
        return ToolKit.assemblyResponseBody(articleService.updateArticle(articleId, articleDto));
    }

    @DeleteMapping("/article/{articleId}")
    public Map<String, Object> deleteArticle(@PathVariable int articleId){
        articleService.deleteArticle(articleId);
        return ToolKit.emptyResponseBody();
    }
}
