package com.example.kmpcomposecontactsapp.contacts.data

import com.example.kmpcomposecontactsapp.contacts.domain.ContactItem
import com.example.kmpcomposecontactsapp.core.data.ImageStorage
import database.ContactEntity

suspend fun ContactEntity.toContactItem(imageStorage: ImageStorage): ContactItem = ContactItem(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    phoneNumber = phoneNubmer,
    photoBytes = imagePath?.let { imageStorage.getImage(it) }
)