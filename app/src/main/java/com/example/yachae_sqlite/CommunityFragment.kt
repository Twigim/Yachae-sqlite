package com.example.yachae_sqlite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


lateinit var community_write: ImageButton
lateinit var recyclerView: RecyclerView
private val layoutManager: RecyclerView.LayoutManager? = null
lateinit var adapter: PostAdapter
//lateinit var view: View

class CommunityFragment : Fragment() {

    private lateinit var dbManager: DBManager
    //private lateinit var listData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootview = inflater.inflate(R.layout.fragment_community, container, false)

        val adapter = PostAdapter()
        adapter.listData.addAll(dbManager.selectPost())
        adapter.dbManager = dbManager

        //val community_write: ImageButton = rootview.findViewbyID(R.id.btn_community_write) as ImageButton

        recyclerView = requireView().findViewById(R.id.recyclerCommunityView) as RecyclerView

        community_write.setOnClickListener {
            //requireActivity().startActivity(Intent(activity, PostActivity::class.java))

            val intent = Intent(getActivity(), MainActivity::class.java)
            startActivity(intent)

//            activity?.let{
//                val intent = Intent(context, PostActivity::class.java)
//                startActivity(intent)
//            }

        }
        return rootview
    }
}