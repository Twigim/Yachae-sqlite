package com.example.yachae_sqlite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction


class PostActivity : AppCompatActivity() {

    private lateinit var btnpost: Button
    //private lateinit var btnPostClose : Button
    private lateinit var edtContent: EditText

    private lateinit var dbManager: DBManager
    private lateinit var database: SQLiteDatabase

    private val OPEN_GALLERY = 1
    private lateinit var openGallery: ImageView

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        dbManager = DBManager(this)
        database = dbManager.writableDatabase

        btnpost = findViewById(R.id.btnPost)
        //btnPostClose = findViewById(R.id.btnPostClose)
        edtContent = findViewById(R.id.post_content)

        btnpost.setOnClickListener {
            val postContent = edtContent.text.toString()

            val saveData = ContentValues()
            saveData.put("post_content", postContent)

            database = dbManager.writableDatabase
            database.execSQL("INSERT INTO post VALUES ('" + postContent + "');")
            database.close()

            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.postActivity, CommunityFragment()).commit()
        }

//        btnPostClose.setOnClickListener {
//
//            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.postActivity, CommunityFragment()).commit()
//
//        }

        //openGallery = findViewById(R.id.openGallery)
        //openGallery.setOnClickListener{  }
    }
}

    //private fun FragmentView(fragment: Int) {

//        //FragmentTransactiom를 이용해 프래그먼트를 사용합니다.
//        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//        when (fragment) {
//                // 첫번 째 프래그먼트 호출
//                val CommunityFragment = Fragment1()
//                transaction.replace(R.id.fragment_container, fragment1)
//                transaction.commit()
//        }

//    private fun openGallery(){
//
//        var writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        var readPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//
//        //권한 없음 -> 요청
//        if (writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE
//                    Manifest.permission.READ_EXTERNAL_STORAGE), REQ_STORAGE_PERMISSION)
//        }//권한 있음
//        else{
//            var openGalleryintent: Intent = Intent(Intent.ACTION_GET_CONTENT)
//            openGalleryintent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//            intent.type = "image/*"
//            startActivityForResult(openGalleryintent, REQ_GALLERY)
//        }

//        val openGalleryintent: Intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.setType("image/*")
        // 갤러리 액티비티로부터 가져온 결과 데이터를 처리하기 위해
        // StartActivityForResult() 함수를 통해 액티비티를 실행
        //startActivityForResult(intent, OPEN_GALLERY)
        //launcher.launch(openGalleryintent)
 //   }

//    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            //This is path to the selected image
//            photoUri = result.data?.data
//            addphoto_image.setImageURI(photoUri)
//
//        } else {
//            // Exit the addPhotoActivity if you leave the album without selecting it
//            finish()
//        }
//    }

//    @Override
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(requestCode == OPEN_GALLERY){
//            if(resultCode == Activity.RESULT_OK){
//
//                //var currentImageUri : Uri? = data?.data
//                photoUri = data?.datas
//
//                try{
//                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUri)
//                    showImage_ImageView.setImageBitmap(bitmap)
//                }
//            }
//        }
//    }
//}