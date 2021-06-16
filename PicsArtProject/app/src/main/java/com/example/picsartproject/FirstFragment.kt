package com.example.picsartproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import kotlin.properties.Delegates

class FirstFragment : Fragment(), KoinComponent {

    val mainViewModel: MainViewModel by sharedViewModel()

    var photoIdFromFragment = 0L
    var photoTitleFromFragment = "photoTitleFromFragment"
    var photoUrlFromFragment = "photoUrlFromFragment"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("redmi", "from fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val createdView = inflater.inflate(R.layout.first_fragment, container, false)
        return createdView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("redmi", "onViewCreated")

        var emailFromFragment = view.findViewById<TextView>(R.id.emailFromFragment)
        var nameFromFragment = view.findViewById<TextView>(R.id.nameFromFragment)
        val elements = mutableListOf<PhotoItem>()


        mainViewModel.user.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                emailFromFragment.text = it.email
                nameFromFragment.text = it.name
            }

        })

        mainViewModel.photo.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                for (getList in it) {
                    photoIdFromFragment = getList.id!!
                    photoTitleFromFragment = getList.title.toString()
                    photoUrlFromFragment = getList.url.toString()
                    elements.add(
                        PhotoItem(
                            photoIdFromFragment,
                            photoUrlFromFragment,
                            photoTitleFromFragment
                        )
                    )
                }


                var recyclerAdapter = RecyclerAdapter(this, elements)
                Log.d("redmi", "from recycler")

                val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = recyclerAdapter
//
//                       recyclerAdapter.notifyDataSetChanged()
            }
        })

    }


    private fun closefragment() {
        activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
    }


}