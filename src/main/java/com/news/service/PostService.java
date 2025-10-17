package com.news.service;

import com.news.dto.PostCreateDto;
import com.news.dto.PostResponseDto;
import com.news.entity.Post;
import com.news.entity.User;
import com.news.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Page<PostResponseDto> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAllWithAuthorOrderByCreatedAtDesc(pageable);
        
        return posts.map(this::convertToDto);
    }

    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAllWithAuthorOrderByCreatedAtDesc(Pageable.unpaged()).getContent();
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto createPost(PostCreateDto postCreateDto, User author) {
        Post post = Post.builder()
                .title(postCreateDto.getTitle())
                .content(postCreateDto.getContent())
                .author(author)
                .build();
        
        Post savedPost = postRepository.save(post);
        return convertToDto(savedPost);
    }

    private PostResponseDto convertToDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .authorUsername(post.getAuthor().getUsername())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
