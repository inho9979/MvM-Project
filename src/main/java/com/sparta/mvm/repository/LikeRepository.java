package com.sparta.mvm.repository;

import com.sparta.mvm.entity.Comment;
import com.sparta.mvm.entity.Like;
import com.sparta.mvm.entity.Post;
import com.sparta.mvm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByUserAndPost(User user, Post post);

    boolean existsByUserAndComment(User user, Comment comment);

    Optional<Like> findByUserAndPost(User user, Post post);

    Optional<Like> findByUserAndComment(User user, Comment comment);
}
