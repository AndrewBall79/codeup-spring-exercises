package com.codeup.springblogapp.repositories;

import com.codeup.springblogapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
//    Post findByTitle(String title);
//    Post getOne(String title);
//Post editPost(String title);
}
