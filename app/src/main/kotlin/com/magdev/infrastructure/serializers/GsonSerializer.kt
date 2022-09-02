package com.magdev.infrastructure.serializers

import com.google.gson.Gson

class GsonSerializer(private val gson: Gson) : ISerializer {

    override fun serialize(entity: Any?): String {
        return gson.toJson(entity)
    }

    override fun <T> deserialize(json: String, cls: Class<T>): T {
        return gson.fromJson(json, cls)
    }
}
