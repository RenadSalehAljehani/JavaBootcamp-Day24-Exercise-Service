package com.example.newsarticlemanagementsystem.Controller;

import com.example.newsarticlemanagementsystem.ApiResponse.ApiResponse;
import com.example.newsarticlemanagementsystem.Model.NewsArticle;
import com.example.newsarticlemanagementsystem.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/newsArticleSystem")
@RequiredArgsConstructor
public class NewsArticleController {

    // 1. Create a variable of type NewsArticleService
    private final NewsArticleService newsArticleService;

    // 2. Now, create the endPoints
    // 2.1 Get all NewsArticles
    @GetMapping("/getNewsArticles")
    public ResponseEntity getNewsArticles(){
        return ResponseEntity.status(200).body(newsArticleService.getNewsArticles());
    }

    // 2.2 Add a NewsArticle
    @PostMapping("/addNewsArticle")
    public ResponseEntity addNewsArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        newsArticleService.addNewsArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("NewsArticle Added."));
    }

    // 2.3  Update a NewsArticle
    @PutMapping("/updateNewsArticle/{ID}")
    public ResponseEntity updateNewsArticle(@PathVariable String ID, @RequestBody @Valid NewsArticle newsArticle, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if(newsArticleService.updateNewsArticle(ID, newsArticle)){
            return ResponseEntity.status(200).body(new ApiResponse("NewsArticle Updated."));
        }
        return ResponseEntity.status(400).body("ID Not Found.");
    }

    // 2.4 Delete a NewsArticle
    @DeleteMapping("/deleteNewsArticle/{ID}")
    public ResponseEntity deleteNewsArticle(@PathVariable String ID){
        if (newsArticleService.deleteNewsArticle(ID)){
            return ResponseEntity.status(200).body(new ApiResponse("NewsArticle Deleted."));
        }
        return ResponseEntity.status(400).body("ID Not Found.");
    }

    // 2.5 Publish NewsArticle
    @PutMapping("/publishNewsArticle/{ID}")
    public ResponseEntity publishNewsArticle(@PathVariable String ID){
        if(newsArticleService.publishNewsArticle(ID)){
            return ResponseEntity.status(200).body(new ApiResponse("NewsArticle with ID (" + ID + ") Has Been Published."));
        }
        return ResponseEntity.status(400).body("ID Not Found.");
    }

    // 2.6 Get all Published NewsArticles
    @GetMapping("/getPublishedNewsArticles")
    public ResponseEntity getPublishedNewsArticles(){
        if(newsArticleService.getPublishedNewsArticles() != null){
            return ResponseEntity.status(200).body(newsArticleService.getPublishedNewsArticles());
        }
        return ResponseEntity.status(400).body("There Are No Published NewsArticles.");
    }

    // 2.7 Get NewsArticles by Category
    @GetMapping("/getNewsArticlesByCategory/{category}")
    public ResponseEntity getNewsArticlesByCategory(@PathVariable String category){
        if(newsArticleService.getNewsArticlesByCategory(category) != null){
            return ResponseEntity.status(200).body(newsArticleService.getNewsArticlesByCategory(category));
        }
        return ResponseEntity.status(400).body("There Are No NewsArticles With Category (" + category + ").");
    }
}