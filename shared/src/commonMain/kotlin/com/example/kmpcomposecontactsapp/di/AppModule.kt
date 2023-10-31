package com.example.kmpcomposecontactsapp.di

import com.example.kmpcomposecontactsapp.contacts.domain.ContactDataSource

expect class AppModule {
    val contactDataSource: ContactDataSource
}