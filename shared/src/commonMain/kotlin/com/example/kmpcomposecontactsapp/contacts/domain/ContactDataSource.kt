package com.example.kmpcomposecontactsapp.contacts.domain

import kotlinx.coroutines.flow.Flow

interface ContactDataSource {
    fun getContacts(): Flow<List<ContactItem>>
    fun getRecentContacts(amount: Int): Flow<List<ContactItem>>
    suspend fun insertContact(contactItem: ContactItem)
    suspend fun deleteContact(id: Long)
}