package com.filos

import movie_graph.model.types.User

fun User.extractDataUser(): User {
    return this.copy(posts = mutableListOf())
}


