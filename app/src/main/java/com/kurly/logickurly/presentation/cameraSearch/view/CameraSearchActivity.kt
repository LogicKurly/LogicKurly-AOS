package com.kurly.logickurly.presentation.cameraSearch.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.provider.MediaStore
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.tabs.TabLayoutMediator
import com.kurly.logickurly.R
import com.kurly.logickurly.data.model.Preferences
import com.kurly.logickurly.databinding.ActivityCameraSearchBinding
import com.kurly.logickurly.databinding.ActivityKurlyMainBinding
import com.kurly.logickurly.presentation.addRefrigerator.adapter.PopularAdapter
import com.kurly.logickurly.presentation.base.BaseActivity
import com.kurly.logickurly.presentation.cameraSearch.viewModel.CameraSearchViewModel

class CameraSearchActivity : BaseActivity<ActivityCameraSearchBinding>(R.layout.activity_camera_search) {

    val REQUEST_IMAGE_CAPTURE = 0
    private var toast: Toast? = null

    private val mainVM: CameraSearchViewModel by lazy {
        ViewModelProvider(this)[CameraSearchViewModel::class.java]
    }

    /** dataBinding 객체에 추가적인 작업을 할 때 사용한다 */
    override fun extraSetupBinding() {
        binding.vm = mainVM
    }

    override fun setup() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    override fun setupViews() {
        binding.header.tvTitle?.text ="AI카메라로 인식하기"
        binding.header.ivAdd?.setImageResource(R.drawable.camera)
        binding.header.ivAdd?.setColorFilter(Color.parseColor("#000000"))
        binding.header.ivBack?.setOnClickListener{
            onBackPressed()
        }
        binding.header.ivAdd?.setOnClickListener{
            takePicture()
            Preferences.getInstance(this).putBooleanItem("takePicture",true)
        }
        binding.tvSearchText?.setOnClickListener{
            onBackPressed()
        }
        binding.tvSearchText2?.setOnClickListener{
            onBackPressed()
        }

        checkPermission()
        if(!Preferences.getInstance(this).getBooleanItem("takePicture",false)){
            takePicture()
            Preferences.getInstance(this).putBooleanItem("takePicture",true)
        }
        else{
            binding.resultContainer.visibility = View.GONE
            binding.noResultContainer.visibility = View.VISIBLE
        }

        //사람들이 많이 검색한 재료
        var itemList = mutableListOf("당근", "고추","마늘")
        var selectedItemList = mutableListOf<Int>()
        //기존에 냉장고에 담겨 있는 재료는 선택된 상태
        var myRefrigerator = Preferences.getInstance(this).getStringItem("MyRefrigerator","")
        var itemList2 = myRefrigerator?.split(",")
        var tempList = mutableListOf<String>()
        for(i in 0 until itemList2!!.size){
            if(itemList2[i]!="" && itemList2[i]!=","){
                tempList.add(itemList2[i].replace(",",""))
            }
        }
        itemList2 = tempList
        for(i in 0 until itemList.size){
            if(itemList2.contains(itemList[i])){
                selectedItemList.add(1)
            }
            else{
                selectedItemList.add(0)
            }
        }

        var listManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        var listAdapter = PopularAdapter(this, itemList as ArrayList<String>, selectedItemList as ArrayList<Int>)
        var recyclerList = binding.resultRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = listManager
            adapter = listAdapter
        }

        listAdapter.setItemClickListener(object : PopularAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                if (selectedItemList[position] == 0) {
                    selectedItemList[position] = 1
                    makeToast("선택하신 식재료가 담겼습니다")
                    Preferences.getInstance(this@CameraSearchActivity).putStringItem("MyRefrigerator",Preferences.getInstance(this@CameraSearchActivity).getStringItem("MyRefrigerator","")+",${itemList[position]}")
                } else {
                    selectedItemList[position] = 0
                    makeToast("선택하신 식재료를 꺼냈습니다")
                    Preferences.getInstance(this@CameraSearchActivity).putStringItem("MyRefrigerator",Preferences.getInstance(this@CameraSearchActivity).getStringItem("MyRefrigerator","").toString().replace(itemList[position],""))
                }
                listAdapter.notifyDataSetChanged()
            }
        })

    }

    fun takePicture(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
    }

    private fun checkPermission() {
        var permission = mutableMapOf<String, String>()
        permission["camera"] = Manifest.permission.CAMERA

        var denied = permission.count { ContextCompat.checkSelfPermission(this, it.value)  == PackageManager.PERMISSION_DENIED }

        if(denied > 0 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permission.values.toTypedArray(), REQUEST_IMAGE_CAPTURE)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_IMAGE_CAPTURE) {
            var count = grantResults.count { it == PackageManager.PERMISSION_DENIED }

            if(count != 0) {
                makeToast("권한을 동의해주세요.")
                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap

            Glide.with(this)
                .load(imageBitmap)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .fitCenter()
                .override(binding.ivPhoto.width, binding.ivPhoto.height)
                .into(binding.ivPhoto)

        }
    }


    override fun onSubscribe() {

    }

    override fun release() {

    }

    override fun onBackPressed() {
        toast?.cancel()
        finish()
    }

    private fun makeToast(message: String) {
        toast?.cancel()
        toast = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
        toast?.show()
    }
}