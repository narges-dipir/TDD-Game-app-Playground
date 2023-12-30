
package com.example.docusedrefactoring.randomcompanion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.docusedrefactoring.MainActivity
import com.example.docusedrefactoring.R
import com.example.docusedrefactoring.GlideApp
import com.example.docusedrefactoring.models.Animal
import com.synnapps.carouselview.CarouselView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RandomCompanionFragment : Fragment() {

  lateinit var animal: Animal
  lateinit var key: String
  lateinit var petPhotos: ArrayList<String>
  lateinit var petCaroselView: CarouselView
  lateinit var randomCompanionFragment: RandomCompanionFragment

  override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    randomCompanionFragment = this
    return inflater.inflate(R.layout.fragment_random_companion, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    GlobalScope.launch {
      key = (activity as MainActivity).accessToken
      (activity as MainActivity).petFinderService?.let { petFinderService ->

        val animalsResponse = petFinderService.getAnimals((activity as MainActivity).accessToken, 1)
        if (animalsResponse.isSuccessful) {
          animalsResponse.body()?.let {animalResult ->
            if (animalResult.animals.size > 0) {
                animalResult.animals.first().let {
                  animal = it
                  GlobalScope.launch(Dispatchers.Main) {
                    populatePet()
                  }
                }

            }
          }
        }
      }
    }
    super.onActivityCreated(savedInstanceState)
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
  }

  private fun populatePhotos() {
    petPhotos = ArrayList()
    animal.photos.forEach { photo ->
      if (photo.full != null)
        petPhotos.add(photo.full)
    }

    view?.let {
      petCaroselView = it.findViewById(R.id.petCarouselView)
      petCaroselView.setViewListener { position ->
        val carouselItemView = layoutInflater.inflate(R.layout.companion_photo_layout, null)
        val imageView = carouselItemView.findViewById<ImageView>(R.id.petImage)
        GlideApp.with(randomCompanionFragment).load(petPhotos[position])
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
