
package com.example.docusedrefactoring.searchforcompanion

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.example.docusedrefactoring.MainActivity

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.example.docusedrefactoring.R
import com.example.docusedrefactoring.testhooks.IdlingEntity
import org.greenrobot.eventbus.EventBus

class SearchForCompanionFragment : Fragment() {

  private lateinit var accessToken: String

  private var petRecyclerView: RecyclerView? = null

  private lateinit var companionAdapter: CompanionAdapter

  private lateinit var viewManager: RecyclerView.LayoutManager

  override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_search_for_companion, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    view?.findViewById<MaterialButton>(R.id.searchButton)?.setOnClickListener {
      try {
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager!!.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
      } catch (e: Exception) {
        // only happens when the keyboard is already closed
      }
      searchForCompanions()
    }

    super.onActivityCreated(savedInstanceState)
  }

  private fun searchForCompanions() {
    val companionLocation = view?.findViewById<TextInputEditText>(R.id.searchFieldText)?.text.toString()
    val noResultsTextView = view?.findViewById<TextView>(R.id.noResults)
    val searchForCompanionFragment = this

    GlobalScope.launch {
      accessToken = (activity as MainActivity).accessToken
      (activity as MainActivity).petFinderService?.let { petFinderService ->
        // increment the IdlingResources
        EventBus.getDefault().post(IdlingEntity(1))
        val searchForPetResponse = petFinderService.getAnimals(accessToken, location = companionLocation)

        if (searchForPetResponse.isSuccessful) {
          searchForPetResponse.body()?.let {
            GlobalScope.launch(Dispatchers.Main) {
              if (it.animals.size > 0) {
                noResultsTextView?.visibility = INVISIBLE
                viewManager = LinearLayoutManager(context)
                companionAdapter = CompanionAdapter(it.animals, searchForCompanionFragment)
                petRecyclerView = view?.let {
                  it.findViewById<RecyclerView>(R.id.petRecyclerView).apply {
                    layoutManager = viewManager
                    adapter = companionAdapter
                  }
                }
              } else {
                noResultsTextView?.visibility = VISIBLE
              }
            }
          }
        } else {
          noResultsTextView?.visibility = VISIBLE
        }
        // Decrement the idling resources.
        EventBus.getDefault().post(IdlingEntity(-1))
      }
    }
  }

}
