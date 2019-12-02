package com.example.cleancoding.data.model

import com.google.gson.annotations.SerializedName


class PaginatedResult<T> (
    @SerializedName("info") val information: Information,
    @SerializedName("results") val results: List<T>
    ) {
        data class Information(
            @SerializedName("count") val count: Int,
            @SerializedName("pages") val pages: Int,
            @SerializedName("next") val next: String,
            @SerializedName("prev") val prev: String
        )
}