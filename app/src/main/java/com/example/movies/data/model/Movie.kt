package com.example.movies.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies_tb")
data class Movie(
    @PrimaryKey val id: Int?,
    val url: String?,
    val name: String?,
    val season: Int?,
    val number: Int?,
    val type: String?,
    val airDate: String?,
    val airTime: String?,
    val airStamp: String?,
    val runTime: Int?,
    @ColumnInfo(name="rating")
    val _rating: Float?,
    val mediumImage: String?,
    val originalImage: String?,
    val summary: String?,
    @Ignore
    @SerializedName("rating")
    val rating: Rating? = Rating(0f),
    @Ignore
    val image: Image? = Image("", "")
) {
    constructor(
        id: Int,
        url: String,
        name: String,
        season: Int,
        number: Int,
        type: String,
        airDate: String,
        airTime: String,
        airStamp: String,
        runTime: Int,
        _rating: Float,
        mediumImage: String,
        originalImage: String,
        summary: String

    ) : this(
        id,
        url,
        name,
        season,
        number,
        type,
        airDate,
        airTime,
        airStamp,
        runTime,
        _rating,
        mediumImage,
        originalImage,
        summary,
        rating = Rating(0f),
        image = Image("", ""),
    )

    data class Rating(
        val average: Float?
    )

    data class Image(
        val medium: String?,
        val original: String?
    )
}

