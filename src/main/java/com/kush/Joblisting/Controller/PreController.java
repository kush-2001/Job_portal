package com.kush.Joblisting.Controller;

import java.io.IOException;
//import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kush.Joblisting.Model.Post;
import com.kush.Joblisting.Repository.PostRepository;
import com.kush.Joblisting.Repository.SearchRepoImpl;

import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

@RestController
public class PreController 
{
    @Autowired
    PostRepository repo;

    @Autowired
    SearchRepoImpl repos;

    @ApiIgnore
    @GetMapping("/")
    public String redirectToSwagger() {
        return "redirect:/swagger-ui.html";
    }
    // @RequestMapping(value = "/")
    // public void redirect(HttpServletResponse response) throws IOException
    // {
    //     response.sendRedirect("/swagger-ui.html");
    // }

    @GetMapping("/posts")
    public List<Post> getallposts()
    {
        // List<Post> l = new ArrayList<>();
        // l.add(new Post("Expert Datascientist","Senior Data Scientist",15, null));
        // return l;
        return repo.findAll();
    }

    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text)
    {
        return repos.findbytext(text);
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post)
    {
        return repo.save(post);
    }
}
