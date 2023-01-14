package com.example.jpapractice.controller;

import com.example.jpapractice.model.Post;
import com.example.jpapractice.model.PostRespoonse;
import com.example.jpapractice.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class PostRequestController {
    @Autowired
    private PostService postService;

    @PostMapping(path = "/posts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostRespoonse> createPost(@RequestBody Post post) {
        PostRespoonse postRespoonse = postService.createPost(post);
        return new ResponseEntity<>(postRespoonse, HttpStatus.OK);

    }

    @GetMapping(path = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        Post post = postService.getPost(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    /*  @PutMapping(path = "/posts/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<PostRespoonse> updatePost(@PathVariable Long id,@RequestBody PostRespoonse postRespoonse){
           PostRespoonse postUpdate=postService.updatePost(id,postRespoonse);
           return new ResponseEntity<>(postRespoonse,HttpStatus.OK);
      }*/
    @PutMapping(path = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostRespoonse> updateBook(@PathVariable Long id, @RequestBody PostRespoonse postRespoonse) {
        PostRespoonse updatePostRespoonse = postService.updatePost(id, postRespoonse);
        log.info("employee update successfully.......");
        return new ResponseEntity<>(updatePostRespoonse, HttpStatus.OK);
    }

    @DeleteMapping(path = "/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        postService.deleteById(id);
        log.info("successfully deleted");
        return ResponseEntity.ok().build();
    }
}
