package com.example.miracleplan.data.network

import com.example.miracleplan.data.model.AccessTokenResponse
import com.example.miracleplan.data.model.CalendarStatusResponse
import com.example.miracleplan.data.model.GroupCreateRequest
import com.example.miracleplan.data.model.GroupResponse
import com.example.miracleplan.data.model.RefreshTokenRequest
import com.example.miracleplan.data.model.TodoCreateRequest
import com.example.miracleplan.data.model.TodoResponse
import com.example.miracleplan.data.model.TodoUpdateRequest
import com.example.miracleplan.data.model.TokenRequest
import com.example.miracleplan.data.model.UserCreateRequest
import com.example.miracleplan.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("/token")
    fun login(@Body tokenRequest: TokenRequest): Call<AccessTokenResponse>

    @POST("/token/refresh")
    fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): Call<AccessTokenResponse>

    @POST("/user")
    fun register(@Body userCreate: UserCreateRequest): Call<UserResponse>

    @POST("/todo")
    fun createTodo(
        @Header("Authorization") token: String,
        @Body todoCreate: TodoCreateRequest
    ): Call<TodoResponse>

    @GET("/todo")
    fun getTodos(@Header("Authorization") token: String): Call<List<TodoResponse>>

    @PUT("/todo/{todo_id}/complete")
    fun updateTodo(
        @Path("todo_id") todoId: Int,
        @Header("Authorization") token: String,
        @Body todoUpdate: TodoUpdateRequest
    ): Call<TodoResponse>

    @POST("/group")
    fun createGroup(
        @Header("Authorization") token: String,
        @Body groupCreate: GroupCreateRequest
    ): Call<GroupResponse>

    @DELETE("/group/{group_id}")
    fun deleteGroup(
        @Path("group_id") groupId: Int,
        @Header("Authorization") token: String
    ): Call<GroupResponse>

    @POST("/group/{group_id}/join")
    fun joinGroup(
        @Path("group_id") groupId: Int,
        @Header("Authorization") token: String
    ): Call<GroupResponse>

    @POST("/group/{group_id}/leave")
    fun leaveGroup(
        @Path("group_id") groupId: Int,
        @Header("Authorization") token: String
    ): Call<GroupResponse>

    @GET("/group/joined")
    fun getJoinedGroups(@Header("Authorization") token: String): Call<List<GroupResponse>>

    @GET("/group/not-joined")
    fun getNotJoinedGroups(@Header("Authorization") token: String): Call<List<GroupResponse>>

    @GET("/group/{group_id}/members")
    fun getGroupMembers(
        @Path("group_id") groupId: Int,
        @Header("Authorization") token: String
    ): Call<List<UserResponse>>

    @GET("/calendar-status")
    fun getCalendarStatus(@Header("Authorization") token: String): Call<List<CalendarStatusResponse>>
}
