package com.alist.service

import com.alist.data.Post
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PostService {
    private val posts = mutableListOf<Post>()

    fun getAll(): List<Post> =
        posts.sortedByDescending { it.createdDate }

    fun add(dto: Post): Post {
        val p = Post(dto.text, LocalDateTime.now())
        posts.add(p)
        return p
    }
}
