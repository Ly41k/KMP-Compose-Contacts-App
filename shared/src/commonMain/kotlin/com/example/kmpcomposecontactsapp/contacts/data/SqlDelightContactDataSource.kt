package com.example.kmpcomposecontactsapp.contacts.data

import com.example.kmpcomposecontactsapp.contacts.domain.ContactDataSource
import com.example.kmpcomposecontactsapp.contacts.domain.ContactItem
import com.example.kmpcomposecontactsapp.core.data.ImageStorage
import com.example.kmpcomposecontactsapp.database.ContactDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock

class SqlDelightContactDataSource(
    db: ContactDatabase,
    private val imageStorage: ImageStorage
) : ContactDataSource {

    private val queries = db.contactQueries
    override fun getContacts(): Flow<List<ContactItem>> {
        return queries
            .getContacts()
            .asFlow()
            .mapToList()
            .map { contactEntities ->
                supervisorScope {
                    contactEntities.map { async { it.toContactItem(imageStorage) } }.map { it.await() }
                }
            }
    }

    override fun getRecentContacts(amount: Int): Flow<List<ContactItem>> {
        return queries
            .getRecentContacts(amount.toLong())
            .asFlow()
            .mapToList()
            .map { contactEntities ->
                supervisorScope {
                    contactEntities.map { async { it.toContactItem(imageStorage) } }.map { it.await() }
                }
            }
    }

    override suspend fun insertContact(contactItem: ContactItem) {
        val imagePath = contactItem.photoBytes?.let {
            imageStorage.saveImage(it)
        }
        queries.insertContactEntity(
            id = contactItem.id,
            firstName = contactItem.firstName,
            lastName = contactItem.lastName,
            phoneNubmer = contactItem.phoneNumber,
            email = contactItem.email,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            imagePath = imagePath
        )
    }

    override suspend fun deleteContact(id: Long) {
        val entity = queries.getContactById(id).executeAsOne()
        entity.imagePath?.let { imageStorage.deleteImage(it) }
        queries.deleteContact(id)
    }
}