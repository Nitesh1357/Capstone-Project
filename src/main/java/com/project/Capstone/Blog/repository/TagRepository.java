package com.project.Capstone.blog.repository;

import com.project.Capstone.blog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
