package com.mufid.ojekyukapi.database

import com.mongodb.client.MongoClient
import org.litote.kmongo.KMongo
import org.springframework.stereotype.Component

@Component
class DatabaseComponent {
//    companion object {
//        private const val DATABASE_URL = "mongodb+srv://imam:12345@cluster0.na9rg.mongodb.net/?retryWrites=true&w=majority"
//    }

    private val DATABASE_URL = System.getenv("DATABASE_URL")

    val database: MongoClient = KMongo.createClient(DATABASE_URL)
}