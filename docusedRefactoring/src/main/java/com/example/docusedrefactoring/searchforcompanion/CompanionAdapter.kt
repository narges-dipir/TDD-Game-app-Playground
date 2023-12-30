
package com.example.docusedrefactoring.searchforcompanion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.docusedrefactoring.R
import com.example.docusedrefactoring.models.Animal

class CompanionAdapter(var animals: ArrayList<Animal>, val fragment: Fragment) : RecyclerView.Adapter<CompanionViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanionViewHolder {
    val findPetListLayout = LayoutInflater.from(parent.context)
        .inflate(R.layout.find_pet_list_layout, parent, false)
    return CompanionViewHolder(findPetListLayout, fragment)
  }

  override fun getItemCount(): Int {
    return animals.size
  }

  override fun onBindViewHolder(holder: CompanionViewHolder, position: Int) {
    holder.populatePet(animals[position])
  }
}
