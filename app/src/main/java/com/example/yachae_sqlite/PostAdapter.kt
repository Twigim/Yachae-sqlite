package com.example.yachae_sqlite

import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.text.SimpleDateFormat

//RecyclerView.Adapter 상속받은 다음 <> 안에 holder 객체를 받음
class PostAdapter: RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    var listData = ArrayList<PostList>()

    internal lateinit var dbManager: DBManager
    private lateinit var database: SQLiteDatabase

    //var helper:SqliteHelper? = null

    //뷰홀더 생성 (레이아웃 생성)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_recyclerview,parent,false)
        return ViewHolder(view)
    }

    //뷰홀더가 재활용될 때 실행되는 메서드
    //리스트 데이터 holder에 넣기
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val postlist = listData.get(position)
//        holder.setPost(postlist)
        val postlist = listData[position]
        holder.apply{
            bind(postlist)
            itemView.tag = postlist
        }
    }

    //리스트 사이즈 반환
    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(item:PostList){
            //val user: TextView = view.findViewById(R.id.textId)
            //val datetime: TextView = view.findViewById(R.id.textDatetime)
            val postcontent: TextView = view.findViewById(R.id.textContent)

            postcontent.text = item.postContent
        }
    }

}