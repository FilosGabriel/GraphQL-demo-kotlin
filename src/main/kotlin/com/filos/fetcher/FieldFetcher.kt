package com.filos.fetcher

import com.filos.data.UsersData
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import movie_graph.model.DgsConstants
import movie_graph.model.types.User

@DgsComponent
class FieldFetcher(private val userData: UsersData) {
    @DgsData(parentType = DgsConstants.USER.TYPE_NAME, field = DgsConstants.USER.Posts)
    fun getPostForUser(dfe: DgsDataFetchingEnvironment) = userData.users
        .filter { user -> user.id == dfe.getSource<User>().id }
        .map { user -> user.posts }
        .flatten()
        .take(dfe.getArgumentOrDefault("size", 3))


    @DgsData(parentType = DgsConstants.USER.TYPE_NAME, field = DgsConstants.USER.Followers)
    fun getFollowers(dfe: DgsDataFetchingEnvironment) = userData.users
        .filter { user -> user.id == dfe.getSource<User>().id }
        .flatMap { it.followers?.toList() ?: mutableListOf() }

}