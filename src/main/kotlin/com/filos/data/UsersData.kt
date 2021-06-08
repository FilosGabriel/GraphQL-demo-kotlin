package com.filos.data

import movie_graph.model.types.Adress
import movie_graph.model.types.Post
import movie_graph.model.types.User
import org.springframework.stereotype.Component
import java.util.*


@Component
class UsersData() {
    val users: MutableList<User>


    init {
        val ad1 = Adress("10", "US")
        val ad2 = Adress("101", "RO")
        val ad3 = Adress("102", "UK")
        val p1 = Post(
            UUID.randomUUID()
                .toString(), "First post", "some random stuff", mutableListOf()
        )
        val p2 = Post(
            UUID.randomUUID()
                .toString(), "First post", "some random stuff", mutableListOf()
        )
        val p3 = Post(
            UUID.randomUUID()
                .toString(), "First post", "some random stuff", mutableListOf()
        )
        val john = User("1", "John", ad1, "12 august 2010", mutableListOf(p1), mutableListOf())
        val ana = User("2", "Ana", ad2, "12 01,", mutableListOf(p2, p3), mutableListOf(john))
        val vlad = User(
            "3", "Vlad", ad3, "12 01,", mutableListOf(), mutableListOf(john, ana)
        )
        users = mutableListOf(john, ana, vlad)
    }
}