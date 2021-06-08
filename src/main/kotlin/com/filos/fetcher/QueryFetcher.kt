package com.filos.fetcher

import com.filos.data.UsersData
import com.filos.extractDataUser
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import movie_graph.model.DgsConstants
import movie_graph.model.types.User

@DgsComponent
class QueryFetcher(
    private val usersData: UsersData
) {
    @DgsQuery(field = DgsConstants.QUERY.User)
    fun findUserById(@InputArgument id: String): User? {
        return usersData.users.filter { user -> user.id == id }
            .firstNotNullOfOrNull { user -> user.extractDataUser() }
    }

    @DgsQuery(field = DgsConstants.QUERY.All)
    fun getAllUsers() = usersData.users
}