package com.apper.theblogservice;

import com.apper.theblogservice.exceptions.BloggerNotFoundException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.payload.*;
import com.apper.theblogservice.service.BloggerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("blogger")
public class BloggerApi {

    private final BloggerService bloggerService;

    public BloggerApi(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

   // create blogger response
    @PostMapping("blogger")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBloggerResponse createBlogger(@RequestBody @Valid CreateBloggerRequest request) {
        System.out.println(request);

        Blogger createdBlogger = bloggerService.createBlogger(request.getEmail(), request.getName(), request.getPassword());

        CreateBloggerResponse response = new CreateBloggerResponse();
        response.setId(createdBlogger.getId());
        response.setDateRegistration(createdBlogger.getCreatedAt());

        return response;
    }

    // get blogger response
    @GetMapping("blogger/{id}")
    public BloggerDetails getBlogger(@PathVariable String id) throws BloggerNotFoundException {
        Blogger blogger = bloggerService.getBlogger(id);

        BloggerDetails bloggerDetails = new BloggerDetails();
        bloggerDetails.setId(blogger.getId());
        bloggerDetails.setName(blogger.getName());
        bloggerDetails.setEmail(blogger.getEmail());
        bloggerDetails.setDateRegistration(blogger.getCreatedAt());

        return bloggerDetails;
    }

    // get all bloggers response
    @GetMapping("blogger")
    public List<BloggerDetails> getAllBloggers() {
        List<Blogger> bloggers = bloggerService.getAllBloggers();
        List<BloggerDetails> bloggerDetailsList = new ArrayList<>();

        for (Blogger blogger : bloggers) {
            BloggerDetails bloggerDetails = new BloggerDetails();
            bloggerDetails.setId(blogger.getId());
            bloggerDetails.setName(blogger.getName());
            bloggerDetails.setEmail(blogger.getEmail());
            bloggerDetails.setDateRegistration(blogger.getCreatedAt());

            bloggerDetailsList.add(bloggerDetails);
        }

        return bloggerDetailsList;

    }

    // create blog response
    @PostMapping("blog")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBlogResponse createBlog(@RequestBody @Valid CreateBlogRequest request) throws BloggerNotFoundException {
        System.out.println(request);

        Blog createdBlog = bloggerService.createBlog(request.getTitle(), request.getBody(), request.getBloggerId());

        CreateBlogResponse response = new CreateBlogResponse();
        response.setTitle(createdBlog.getTitle());
        response.setBody(createdBlog.getBody());
        response.setBloggerId(createdBlog.getBlogger().getId());
        response.setId(createdBlog.getId());
        response.setCreatedAt(createdBlog.getCreatedAt());
        response.setLastUpdated(createdBlog.getLastUpdate());

        return response;
    }

    // update blog
    @PutMapping("/blog/{id}")
    // @ResponseStatus(HttpStatus.NO_CONTENT) - test
    public BlogDetails updateBlog(@PathVariable String id, @RequestBody @Valid UpdateBlogRequest request) {
        bloggerService.updateBlog(id, request.getTitle(), request.getBody());

        Blog blog = bloggerService.getBlog(id);

        BlogDetails updateBlog = new BlogDetails();
        updateBlog.setId(blog.getId());
        updateBlog.setTitle(blog.getTitle());
        updateBlog.setBody(blog.getBody());
        updateBlog.setBloggerId(blog.getBlogger().getId());
        updateBlog.setCreatedAt(blog.getCreatedAt());
        updateBlog.setLastUpdated(blog.getLastUpdate());

        return updateBlog;
    }


    // get blog response
    @GetMapping("blog/{id}")
    public BlogDetails getBlog(@PathVariable String id) {
        Blog blog = bloggerService.getBlog(id);

        BlogDetails blogDetails = new BlogDetails();
        blogDetails.setId(blog.getId());
        blogDetails.setTitle(blog.getTitle());
        blogDetails.setBody(blog.getBody());
        blogDetails.setBloggerId(blog.getBlogger().getId());
        blogDetails.setCreatedAt(blog.getCreatedAt());
        blogDetails.setLastUpdated(blog.getLastUpdate());

        return blogDetails;

    }

    // get all blogs response
    @GetMapping("blog")
    public List<BlogDetails> getAllBlogs() {
        List<Blog> blogs = bloggerService.getAllBlogs();
        List<BlogDetails> blogDetailsList = new ArrayList<>();

        for (Blog blog : blogs) {
            BlogDetails blogDetails = new BlogDetails();
            blogDetails.setId(blog.getId());
            blogDetails.setTitle(blog.getTitle());
            blogDetails.setBody(blog.getBody());
            blogDetails.setBloggerId(blog.getBlogger().getId());
            blogDetails.setCreatedAt(blog.getCreatedAt());
            blogDetails.setLastUpdated(blog.getLastUpdate());

            blogDetailsList.add(blogDetails);
        }

        return blogDetailsList;
    }

    // Get all blogs by blogger

    // another implementation: This returns all blogger details but shows everything
    //        @GetMapping("blog/blogger/{bloggerId}")
    //        public List<Blog> getAllBlogsByBlogger(@PathVariable String bloggerId) {
    //            return bloggerService.getAllBlogsByBlogger(bloggerId);
    //        }

    @GetMapping("blog/blogger/{bloggerId}")
    public ResponseEntity<List<BlogDetails>> getAllBlogsByBlogger(@PathVariable String bloggerId) {
        List<Blog> blogs = bloggerService.getAllBlogsByBlogger(bloggerId);
        List<BlogDetails> blogDetailsList = new ArrayList<>();

        for (Blog blog : blogs) {
            BlogDetails blogDetails = new BlogDetails();
            blogDetails.setId(blog.getId());
            blogDetails.setTitle(blog.getTitle());
            blogDetails.setBody(blog.getBody());
            blogDetails.setBloggerId(blog.getBlogger().getId());
            blogDetails.setCreatedAt(blog.getCreatedAt());
            blogDetails.setLastUpdated(blog.getLastUpdate());

            blogDetailsList.add(blogDetails);
        }

        return ResponseEntity.ok(blogDetailsList);
    }

}