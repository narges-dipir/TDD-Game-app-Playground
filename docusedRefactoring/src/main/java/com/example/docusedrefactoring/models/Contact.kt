
package com.example.docusedrefactoring.models

import com.example.docusedrefactoring.models.Address

data class Contact(
  val phone: String = "",
  val email: String = "",
  val address: Address
)
