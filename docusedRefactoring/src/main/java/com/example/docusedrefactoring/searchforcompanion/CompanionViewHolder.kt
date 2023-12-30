
package com.example.docusedrefactoring.searchforcompanion

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.docusedrefactoring.GlideApp
import com.example.docusedrefactoring.R
import com.example.docusedrefactoring.models.Animal

class CompanionViewHolder(val view: View, val fragment: Fragment) : RecyclerView.ViewHolder(view) {

  fun populatePet(animal: Animal) {
    populateTextField(R.id.name, animal.name)
    populateTextField(R.id.sex, animal.gender)
    populateTextField(R.id.breed, animal.breeds.primary)
    animal.photos?.let {
      if (it.size > 0) {
        val imageView = view.findViewById<ImageView>(R.id.petImage)
        GlideApp.with(view.context).load(it[0].full)
            .fitCenter()
            .into(imageView)
      }
    }

    setupClickEvent(animal)
  }

  private fun populateTextField(id: Int, stringValue: String) {
    (view.findViewById(id) as TextView).text = stringValue
  }

  private fun setupClickEvent(animal: Animal){
    view.setOnClickListener {
      val viewCompanionFragment = ViewCompanionFragment()
      val bundle = Bundle()
      bundle.putSerializable(ViewCompanionFragment.ANIMAL, animal)
      viewCompanionFragment.arguments = bundle
      val transaction = fragment.childFragmentManager.beginTransaction()
      transaction.replace(R.id.viewCompanion, viewCompanionFragment).addToBackStack("companionView").commit()
    }
  }
}
