package com.mufid.ojekyukapi.database

import com.mongodb.client.MongoClient
import org.litote.kmongo.KMongo
import org.springframework.stereotype.Component

@Component
class DatabaseComponent {
    // DATABASE_URL=mongodb+srv://imam:12345@cluster0.na9rg.mongodb.net/?retryWrites=true&w=majority
    private val databaseUrl = "mongodb+srv://imam:12345@cluster0.na9rg.mongodb.net/?retryWrites=true&w=majority"

    companion object {
        const val DATABASE_NAME = "ojekyuk"
    }

    final val database: MongoClient = KMongo.createClient(databaseUrl)
}