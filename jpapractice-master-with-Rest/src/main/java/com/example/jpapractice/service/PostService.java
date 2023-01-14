package com.example.jpapractice.service;


import com.example.jpapractice.model.Post;
import com.example.jpapractice.model.PostRespoonse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@Log4j2
public class PostService {
    private final RestTemplate restTemplate;

    @Autowired
    public PostService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${base.url}")
    private String fakeUrl;

    public PostRespoonse createPost(Post post) {
        HttpEntity<Post> http = new HttpEntity<>(post);

        ResponseEntity<PostRespoonse> postRespoonse = restTemplate.exchange(fakeUrl + "/posts", HttpMethod.POST, http, PostRespoonse.class);
        return postRespoonse.getBody();
    }


    public Post getPost(Long id) {

        Post post = restTemplate.getForObject(fakeUrl + "/posts/{id}", Post.class, id);
        return post;
    }

    public void deleteById(Long id) {
        restTemplate.delete(fakeUrl + "/posts/{id}", id);
    }

    /*
        public PostRespoonse updatePost(Long id, PostRespoonse postRespoonse) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<PostRespoonse> entity = new HttpEntity<>(postRespoonse,httpHeaders);
            return restTemplate.exchange(fakeUrl + "/posts/{id}", HttpMethod.PUT, entity, PostRespoonse.class).getBody();

        }*/
    public PostRespoonse updatePost(Long id, PostRespoonse postRespoonse) {
        HttpEntity http = new HttpEntity<>(postRespoonse);
        ResponseEntity<PostRespoonse> response = restTemplate.exchange(fakeUrl + "/posts/" + id, HttpMethod.PUT, http, PostRespoonse.class, id);
        return response.getBody();
    }
}
