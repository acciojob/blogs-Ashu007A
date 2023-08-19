package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository;
    @Autowired
    ImageRepository imageRepository;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog

        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog != null) {
            Image image = new Image();
            image.setDescription(description);
            image.setDimensions(dimensions);
            image.setBlog(blog);
            return imageRepository.save(image);
        }
        return null;

    }

    public void deleteImage(Integer id){

        imageRepository.deleteById(id);

    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

        String[] dimensions = screenDimensions.split("X");
        int screenWidth = Integer.parseInt(dimensions[0]);
        int screenHeight = Integer.parseInt(dimensions[1]);

        Image image = imageRepository.findById(id).orElse(null);
        if (image != null) {
            String[] imageDimensions = image.getDimensions().split("X");
            int imageWidth = Integer.parseInt(imageDimensions[0]);
            int imageHeight = Integer.parseInt(imageDimensions[1]);

            int horizontalCount = screenWidth / imageWidth;
            int verticalCount = screenHeight / imageHeight;

            return horizontalCount * verticalCount;
        }

        return 0;

    }
}
