package com.example.kmpcomposecontactsapp.contacts.data

import com.example.kmpcomposecontactsapp.contacts.domain.ContactDataSource
import com.example.kmpcomposecontactsapp.contacts.domain.ContactItem
import com.example.kmpcomposecontactsapp.database.ContactDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightContactDataSource(
    db: ContactDatabase
) : ContactDataSource {

    private val queries = db.contactQueries
    override fun getContacts(): Flow<List<ContactItem>> {
        return queries
            .getContacts()
            .asFlow()
            .mapToList()
            .map { contactEntities ->
                contactEntities.map { it.toContactItem() }
            }
    }

    override fun getRecentContacts(amount: Int): Flow<List<ContactItem>> {
        return queries
            .getRecentContacts(amount.toLong())
            .asFlow()
            .mapToList()
            .map { contactEntities ->
                contactEntities.map { it.toContactItem() }
            }
    }

    override suspend fun insertContact(contactItem: ContactItem) {
        queries.insertContactEntity(
            id = contactItem.id,
            firstName = contactItem.firstName,
            lastName = contactItem.lastName,
            phoneNubmer = contactItem.phoneNumber,
            email = contactItem.email,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            imagePath = null
        )
    }

    override suspend fun deleteContact(id: Long) {
        queries.deleteContact(id)
    }
}