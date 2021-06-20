package com.alist.rest

import com.alist.data.Post
import com.alist.service.PostService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/rest/post")
class PostController(val postService: PostService) {
    @GetMapping("/hello")
    fun hello(): String =
        "hello world"

    @GetMapping("/all")
    fun getAll(): List<Post> =
        postService.getAll()

    @PostMapping("/add")
    fun add(@RequestBody post: Post): Post =
        postService.add(post)
}
