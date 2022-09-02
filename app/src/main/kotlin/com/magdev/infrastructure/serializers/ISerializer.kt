package com.magdev.infrastructure.serializers

interface ISerializer {

    fun serialize(entity: Any?): String

    fun <T> deserialize(json: String, cls: Class<T>): T
}
