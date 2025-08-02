package com.project.Capstone.blog.repository;

import com.project.Capstone.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
