package com.kush.Joblisting.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.kush.Joblisting.Model.Post;


@Repository
public interface PostRepository extends MongoRepository<Post,String>
{
    
}