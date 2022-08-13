package com.mufid.ojekyukapi.database

import com.mongodb.client.MongoClient
import org.litote.kmongo.KMongo
import org.springframework.stereotype.Component

@Component
class DatabaseComponent {
    private val databaseUrl = System.getenv("DATABASE_URL")

    companion object {
        const val DATABASE_NAME = "ojekyuk"
    }

    final val database: MongoClient = KMongo.createClient(databaseUrl)
}