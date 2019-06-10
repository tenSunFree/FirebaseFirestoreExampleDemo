package com.home.firebasefirestoreexampledemo

data class RestaurantData(
    val id: String,
    val name: String,
    val introduction: String,
    val discount: String,
    val like: String,
    val imageUrl: String,
    val open: Boolean
) {
    /**
     * 如果想透過代碼創建firestore的資料, 這個方法是必需的
     */
    constructor() : this("", "", "", "", "", "", false)
}