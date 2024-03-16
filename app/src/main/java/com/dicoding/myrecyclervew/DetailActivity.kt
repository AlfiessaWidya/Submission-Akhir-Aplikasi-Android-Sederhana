package com.dicoding.myrecyclervew

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_DESC = "extra_desc"
        const val EXTRA_RATING = "extra_rating"
        const val EXTRA_RELEASED = "extra_released"
        const val EXTRA_ACTOR = "extra_actor"
        const val EXTRA_LINK = "extra_link"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionbar = supportActionBar
        actionbar!!.title = intent.getStringExtra(EXTRA_NAME)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val ivDetailPhoto: ImageView = findViewById(R.id.img_detail_photo)
        val tvDetailDesc: TextView = findViewById(R.id.tv_detail_desc)
        val tvDetailRate: TextView = findViewById(R.id.tv_detail_rating)
        val tvDetailReleased: TextView = findViewById(R.id.tv_detail_released)
        val tvDetailActor: TextView = findViewById(R.id.tv_detail_actor)
        val btnDetailShare: Button = findViewById(R.id.btn_detail_share)

        val DetailName = intent.getStringExtra(EXTRA_NAME)
        val DetailPhoto = intent.getStringExtra(EXTRA_PHOTO)
        val DetailDesc = intent.getStringExtra(EXTRA_DESC)
        val DetailRate = intent.getStringExtra(EXTRA_RATING)
        val DetailReleased = intent.getStringExtra(EXTRA_RELEASED)
        val DetailActor = intent.getStringExtra(EXTRA_ACTOR)
        val DetailLink = intent.getStringExtra(EXTRA_LINK)

        tvDetailName.text = DetailName
        Glide.with(this)
            .load(DetailPhoto)
            .apply(RequestOptions())
            .into(ivDetailPhoto)
        tvDetailDesc.text = DetailDesc
        tvDetailRate.text = DetailRate
        tvDetailReleased.text = DetailReleased
        tvDetailActor.text = DetailActor

        btnDetailShare.setOnClickListener {
            val goShare = Intent()
            goShare.action = Intent.ACTION_SEND
            goShare.putExtra(Intent.EXTRA_TEXT, "Hey look at this Genshin Character: $DetailLink")
            goShare.type = "text/plain"
            startActivity(Intent.createChooser(goShare, "Share To:"))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}