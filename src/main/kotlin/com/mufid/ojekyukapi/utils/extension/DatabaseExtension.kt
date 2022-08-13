package com.mufid.ojekyukapi.utils.extension

import com.mongodb.client.MongoCollection
import com.mufid.ojekyukapi.database.DatabaseComponent
import org.litote.kmongo.util.KMongoUtil

inline fun <reified T : Any> DatabaseComponent.collection(): MongoCollection<T> =
    database
        .getDatabase(DatabaseComponent.DATABASE_NAME)
        .getCollection(KMongoUtil.defaultCollectionName(T::class), T::class.java)