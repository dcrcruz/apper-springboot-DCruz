package com.apper.theblogservice.service;

import com.apper.theblogservice.exceptions.BloggerNotFoundException;
import com.apper.theblogservice.exceptions.DuplicateEmailException;
import com.apper.theblogservice.exceptions.EmailFormatException;
import com.apper.theblogservice.exceptions.PasswordLengthException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BlogRepository;
import com.apper.theblogservice.repository.BloggerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BloggerService {

    private final BloggerRepository bloggerRepository;
    private final List<Blogger> bloggers = new ArrayList<>();

    private final BlogRepository blogRepository;

    public BloggerService(BloggerRepository bloggerRepository, BlogRepository blogRepository) {
        this.bloggerRepository = bloggerRepository;
        this.blogRepository = blogRepository;
    }

    // register blogger
    public Blogger createBlogger(String email, String name, String password) throws
            DuplicateEmailException,
            PasswordLengthException,
            EmailFormatException {

        if (isEmailAlreadyRegistered(email)) {
            throw new DuplicateEmailException("Email is already registered: " + email);
        }

        if (password.length() < 5) {
            throw new PasswordLengthException("Password must be at least 5 characters long.");
        }

        if (!isValidEmailFormat(email)) {
            throw new EmailFormatException("Invalid email format: " + email);
        }

        Blogger blogger = new Blogger();
        blogger.setEmail(email);
        blogger.setName(name);
        blogger.setPassword(password);

        return bloggerRepository.save(blogger);
    }

    private boolean isValidEmailFormat( String email) {
        String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        return email.matches(emailRegex);
    }

    private boolean isEmailAlreadyRegistered(String email) {
        return bloggerRepository.existsByEmail(email);
    }


    // get one blogger
    public Blogger getBlogger(String id) throws BloggerNotFoundException {
        Optional<Blogger> bloggerResult = bloggerRepository.findById(id);

        return bloggerResult.orElseThrow(() -> new BloggerNotFoundException("Blogger NOT FOUND | ID: " + id));
    }


    // get all bloggers
    public List<Blogger> getAllBloggers() {
        return (List<Blogger>) bloggerRepository.findAll();
    }

    // create blog
    public Blog createBlog(String title, String body, String bloggerId) throws BloggerNotFoundException {
        Blog blog = new Blog();
        Blogger blogger = getBlogger(bloggerId);

        blog.setTitle(title);
        blog.setBody(body);
        blog.setCreatedAt(LocalDateTime.now());
        blog.setLastUpdate(LocalDateTime.now());
        blog.setBlogger(blogger);

        return blogRepository.save(blog);
    }

    // update blog
    public Blog updateBlog(String id, String title, String body) {
        Blog blog = getBlog(id);

        blog.setTitle(title);
        blog.setBody(body);
        blog.setLastUpdate(LocalDateTime.now());

        return blogRepository.save(blog);
    }

    // get one blog
    public Blog getBlog(String id) {
        Optional<Blog> blogResult = blogRepository.findById(id);

        return blogResult.get();
    }

    // get all blogs
    public List<Blog> getAllBlogs() {
        return (List<Blog>) blogRepository.findAll();
    }

    // Get all blogs by blogger
    public List<Blog> getAllBlogsByBlogger(String bloggerId) {
        return blogRepository.findByBloggerId(bloggerId);
    }

}