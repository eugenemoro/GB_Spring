package ru.emorozov.springbootapp.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.emorozov.springbootapp.entity.Post;
import ru.emorozov.springbootapp.repository.PostRepository;
import ru.emorozov.springbootapp.repository.UserRepository;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Post createPost
            (@NotNull final String userID,
             @NotNull final String title,
             @NotNull final String description,
             @NotNull final String content) {
        final Post post = new Post();
        post.setUser(userRepository.getOne(userID));
        post.setTitle(title);
        post.setDescription(description);
        post.setContent(content);
        postRepository.save(post);
        return post;
    }

    public List<Post> getListPost() {
        return postRepository.findAll();
    }

    @Transactional
    public Post removePostById(@Nullable final String postId) {
        if (postId == null || postId.isEmpty()) return null;
        final Post post = postRepository.getOne(postId);
        postRepository.delete(post);
        return post;
    }

}
