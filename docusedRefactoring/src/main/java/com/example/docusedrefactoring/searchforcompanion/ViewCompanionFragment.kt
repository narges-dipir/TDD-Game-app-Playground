
package com.example.docusedrefactoring.searchforcompanion


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.docusedrefactoring.GlideApp

import com.example.docusedrefactoring.R
import com.example.docusedrefactoring.models.Animal
import com.synnapps.carouselview.CarouselView

class ViewCompanionFragment : Fragment() {

  companion object {
    val ANIMAL: String = "ANIMAL"
  }

  private lateinit var animal: Animal
  private lateinit var petPhotos: ArrayList<String>
  private lateinit var petCaroselView: CarouselView
  private lateinit var viewCompanionFragment: ViewCompanionFragment

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    animal = arguments?.getSerializable(ANIMAL) as Animal
    viewCompanionFragment = this
    return inflater.inflate(R.layout.fragment_view_companion, container, false)
  }

  override fun onResume() {
    populatePet()
    super.onResume()
  }

  private fun populatePet() {
    populateTextField(R.id.petName, animal.name)
    populateTextField(
      R.id.city,
      animal.contact.address.city + ", " + animal.contact.address.state
    )
    populateTextField(R.id.age, animal.age)
    populateTextField(R.id.sex, animal.gender)
    populateTextField(R.id.size, animal.size)
    populateTextField(R.id.meetTitlePlaceholder, "Meet " + animal.name)
    populateTextField(R.id.description, animal.description)
    populatePhotos()

    populateTextField(R.id.breed, animal.breeds.primary)
    populateTextField(R.id.email, animal.contact.email)
    populateTextField(R.id.telephone, animal.contact.phone)
  }

  private fun populatePhotos() {
    petPhotos = ArrayList()
    animal.photos.forEach { photo ->
      petPhotos.add(photo.full)
    }

    view?.let {
      petCaroselView = it.findViewById(R.id.petCarouselView)
      petCaroselView.setViewListener { position ->
        val carouselItemView = layoutInflater.inflate(R.layout.companion_photo_layout, null)
        val imageView = carouselItemView.findViewById<ImageView>(R.id.petImage)
        GlideApp.with(viewCompanionFragment).load(petPhotos[position])
          .fitCenter()
          .into(imageView)
        carouselItemView
      }
      petCaroselView.pageCount = petPhotos.size
    }
  }

  private fun populateTextField(id: Int, stringValue: String) {
    view?.let {
      (it.findViewById(id) as TextView).text = stringValue
    }
  }

}
