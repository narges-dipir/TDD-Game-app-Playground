
package com.example.docusedrefactoring.models

import com.example.docusedrefactoring.models.Breeds
import com.example.docusedrefactoring.models.Contact
import com.example.docusedrefactoring.models.Photo
import java.io.Serializable

data class Animal(
    val id: Int,
    var contact: Contact,
    var age: String,
    var size: String,
    var photos: ArrayList<Photo>,
    var breeds: Breeds,
    var name: String,
    var gender: String,
    var description: String
) : Serializable
