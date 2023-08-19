package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    @Autowired
    ImageRepository imageRepository;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time

        User user = userRepository1.findById(userId).orElse(null);
        if (user != null) {
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setContent(content);
            blog.setPubDate(new Date()); // Set the publication date
            blog.setUser(user);

            user.getBlogList().add(blog); // Add blog to user's blog list

            return blogRepository1.save(blog);
        }

        return null;

    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images

        Blog blog = blogRepository1.findById(blogId).orElse(null);
        if (blog != null) {
            // Delete associated images
            List<Image> images = blog.getImageList();
            for (Image image : images) {
                imageRepository.delete(image);
            }

            // Delete the blog
            blogRepository1.deleteById(blogId);
        }

    }
}
