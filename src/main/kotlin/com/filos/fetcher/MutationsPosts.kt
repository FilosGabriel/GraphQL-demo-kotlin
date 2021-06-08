package com.filos.fetcher

import com.filos.data.UsersData
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import movie_graph.model.DgsConstants
import movie_graph.model.types.CreatePost
import movie_graph.model.types.Post
import movie_graph.model.types.User
import java.util.concurrent.atomic.AtomicInteger

@DgsComponent
class MutationsPosts(
    private val userData: UsersData,
) {
    private val idGenerator: AtomicInteger = AtomicInteger(3)

    @DgsMutation(field = DgsConstants.MUTATION.AddPosts)
    fun addPost(@InputArgument id: String, @InputArgument postInput: CreatePost): User {
        val post = Post(
            id = idGenerator.addAndGet(1).toString(),
            content = postInput.content,
            title = postInput.title,
            comments = mutableListOf()
        )
        val user = userData.users.first { it.id == id }
        val posts = user.posts.toMutableList();
        posts.add(post);
        val copyUser = user.copy(posts = posts)
        userData.users.removeIf { it.id == id }
        userData.users.add(copyUser);
        return copyUser;
    }
}