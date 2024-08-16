package com.example.miracleplan.data.model

data class TokenRequest(
    val username: String,
    val password: String
)

data class AccessTokenResponse(
    val access_token: String,
    val refresh_token: String,
    val token_type: String
)

data class RefreshTokenRequest(
    val refresh_token: String
)

data class UserCreateRequest(
    val username: String,
    val password: String,
    val profile: String?
)

data class UserResponse(
    val id: Int,
    val username: String,
    val profile: String?
)

data class TodoCreateRequest(
    val title: String,
    val start_date: String,
    val end_date: String
)

data class TodoUpdateRequest(
    val completed: Boolean
)

data class TodoResponse(
    val id: Int,
    val title: String,
    val start_date: String,
    val end_date: String,
    val creator_id: Int,
    val completed: Boolean
)

data class GroupCreateRequest(
    val name: String
)

data class GroupResponse(
    val id: Int,
    val name: String,
    val creator_id: Int,
    val members: List<UserResponse>
)

data class CalendarStatusResponse(
    val date: String,
    val status: String
)

data class ProfileUpdateResponse(
    val filename: String,
    val file_path: String
)

data class ProfileDeleteResponse(
    val filename: String?,
    val file_path: String?
)