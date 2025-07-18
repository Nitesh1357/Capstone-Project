package com.project.Capstone.blog.repository;

import com.project.Capstone.blog.model.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogCommentRepository extends JpaRepository<BlogComment, Long> {
    List<BlogComment> findByPostId(Long postId);
}
