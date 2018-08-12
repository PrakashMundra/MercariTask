package com.mercaritask.data.model

class TabDataKt {
    var id: String? = null
    var name: String? = null
    var status: String? = null
    var photo: String? = null
    private val num_likes: String? = null
    private val num_comments: String? = null
    private val price: String? = null

    fun getLikes(): String? {
        return if (!num_likes.isNullOrEmpty()) num_likes else "0"
    }

    fun getComments(): String? {
        return if (!num_comments.isNullOrEmpty()) num_comments else "0"
    }

    fun getPrice(): String? {
        return "$ " + if (!price.isNullOrEmpty()) price else "0"
    }
}
