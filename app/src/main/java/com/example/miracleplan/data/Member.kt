package com.example.miracleplan.data

import com.example.miracleplan.R

data class Member(
    val name: String,
    val profileImageResId: Int,
    val status: String,
    val points: Int
)

val members = listOf(
    Member("나제준", R.drawable.profile1, "성공", 90),
    Member("이주영", R.drawable.profile2, "도전 중", 20),
    Member("이정우", R.drawable.profile3, "성공", 30),
    Member("김태경", R.drawable.profile4, "실패", 30),
    Member("나정우", R.drawable.profile1, "도전 중", 40),
    Member("이태경", R.drawable.profile2, "실패", 35)
)


