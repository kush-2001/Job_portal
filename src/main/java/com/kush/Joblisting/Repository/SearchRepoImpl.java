package com.kush.Joblisting.Repository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
//import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.kush.Joblisting.Model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.*;


@Component
public class SearchRepoImpl
{
    @Autowired
    MongoClient client ;

    @Autowired
    MongoConverter converter;

    public List<Post> findbytext(String text)
    {
        List<Post> posts  = new ArrayList<>();

        MongoDatabase database = client.getDatabase("kushdb");
        MongoCollection<Document> collection = database.getCollection("Jobpost");
        
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
            new Document("text", 
            new Document("query", text)
                        .append("path", "desc"))), 
            new Document("$sort", 
            new Document("desc", 1L)), 
            new Document("$limit", 2L)));

        result.forEach(doc -> posts.add(converter.read(Post.class, doc)));

        return posts;
    }
}
