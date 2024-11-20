package com.example.newsarticlemanagementsystem.Service;

import com.example.newsarticlemanagementsystem.Model.NewsArticle;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class NewsArticleService {

    // Create list of news articles
    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    // 1. Get all NewsArticles
    public ArrayList<NewsArticle> getNewsArticles(){
        return newsArticles;
    }

    // 2. Add a NewsArticle
    public void addNewsArticle(NewsArticle newsArticle){
        newsArticles.add(newsArticle);
    }

    // 3. Update a NewsArticle
    public boolean updateNewsArticle(String ID, NewsArticle newsArticle){
        for (int i = 0; i < newsArticles.size(); i++) {
            if(newsArticles.get(i).getID().equalsIgnoreCase(ID)){
                newsArticles.set(i,newsArticle);
                return true;
            }
        }
        return false;
    }

    // 4. Delete a NewsArticle
    public boolean deleteNewsArticle(String ID){
        for (int i = 0; i < newsArticles.size(); i++) {
            if(newsArticles.get(i).getID().equalsIgnoreCase(ID)){
                newsArticles.remove(i);
                return true;
            }
        }
        return false;
    }

    // 5. Publish NewsArticles
    public boolean publishNewsArticle(String ID){
        for (int i = 0; i < newsArticles.size(); i++) {
            if(newsArticles.get(i).getID().equalsIgnoreCase(ID) && !newsArticles.get(i).isPublished()){
                newsArticles.get(i).setPublished(true);
                return true;
            }
        }
        return false;
    }

    // 6. Get all Published NewsArticles
    public ArrayList<NewsArticle> getPublishedNewsArticles(){
        ArrayList<NewsArticle> publishedNewsArticlesList = new ArrayList<>();
        for (NewsArticle newsArticle: newsArticles){
            if(newsArticle.isPublished()){
                publishedNewsArticlesList.add(newsArticle);
            }
        }
        if(publishedNewsArticlesList.isEmpty()){
            return null;
        }
        return publishedNewsArticlesList;
    }


    // 7. Get NewsArticles by Category
    public ArrayList<NewsArticle> getNewsArticlesByCategory(String category){
        ArrayList<NewsArticle> newsArticlesWithSameCategory = new ArrayList<>();
        for (NewsArticle newsArticle: newsArticles) {
            if(newsArticle.getCategory().equalsIgnoreCase(category)){
                newsArticlesWithSameCategory.add(newsArticle);
            }
        }
        if(newsArticlesWithSameCategory.isEmpty()){
            return null;
        }
        return newsArticlesWithSameCategory;
    }
}