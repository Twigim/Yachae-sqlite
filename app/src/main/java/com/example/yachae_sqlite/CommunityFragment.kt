package com.example.yachae_sqlite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


lateinit var community_write: ImageButton
lateinit var recyclerView: RecyclerView
private val layoutManager: RecyclerView.LayoutManager? = null
lateinit var adapter: PostAdapter

class CommunityFragment : Fragment() {

    private lateinit var dbManager: DBManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_community, container, false)
        val rootview = inflater.inflate(R.layout.fragment_community, container, false)

        dbManager= DBManager(container?.context)

        val adapter = PostAdapter()
        adapter.listData.addAll(dbManager.selectPost())
        adapter.dbManager = dbManager

        //recyclerView.adapter = adapter
        recyclerView = rootview.findViewById(R.id.recyclerCommunityView)
        recyclerView.adapter = adapter

        //recyclerView 화면에 보이는 레이아웃 매니저 연결결
       recyclerView.layoutManager = LinearLayoutManager(container?.context)

        //글쓰기 Activity로 전환
        val community_write: ImageButton = rootview.findViewById(R.id.btn_community_write) as ImageButton
        community_write.setOnClickListener {
            requireActivity().startActivity(Intent(activity, PostActivity::class.java))
            val intent = Intent(getActivity(), PostActivity::class.java)
            startActivity(intent)
        }
        return rootview
    }
}