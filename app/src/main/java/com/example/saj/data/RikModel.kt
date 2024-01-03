package com.example.saj.data

import java.io.Serializable


data class RikModel(
    val results: List<Result>):Serializable
    {
        data class Result(
            val id: Int,
            val image: String,
            val name: String,
            val location: Location,
            val status: String,
            val species: String,
            val origin: Origin,
        ):Serializable{
            data class Location(
                val name: String,
                val url: String
            ):Serializable
            data class Origin(
                val name: String,
                val url: String
            ):Serializable
        }
    }

