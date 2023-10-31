package com.example.kmpcomposecontactsapp.di

import android.content.Context
import com.example.kmpcomposecontactsapp.contacts.data.SqlDelightContactDataSource
import com.example.kmpcomposecontactsapp.contacts.domain.ContactDataSource
import com.example.kmpcomposecontactsapp.core.data.DatabaseDriverFactory
import com.example.kmpcomposecontactsapp.database.ContactDatabase

actual class AppModule(
    private val context: Context
) {

    actual val contactDataSource: ContactDataSource by lazy {
        SqlDelightContactDataSource(
            db = ContactDatabase(
                driver = DatabaseDriverFactory(context).create(),
            )
        )
    }
}