package com.awma.seljukempire.presentation.ui

import android.app.AlertDialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.awma.seljukempire.R
import com.awma.seljukempire.data.model.Characters
import com.awma.seljukempire.data.model.Events
import com.awma.seljukempire.databinding.ActivityPageBinding
import com.bumptech.glide.Glide

class PageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPageBinding
    private lateinit var tvContent: TextView
    private var character: Characters? = null
    private var event: Events? = null
    private var textContent: String? = "null"
    private var imageUrl: String? = "null"
    private var title: String? = "null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvContent = findViewById(R.id.tvContent)
        val toolbar = binding.toolbar
        val toolbarLayout = binding.toolbarLayout
        toolbarLayout.setExpandedTitleMarginTop(0)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
        toolbarLayout.title = title

        @Suppress("DEPRECATION")
        character = intent.getParcelableExtra<Characters>("char")
        @Suppress("DEPRECATION")
        event = intent.getParcelableExtra<Events>("event")



        if (character != null) {
            textContent = character!!.text!!.get("ar-العربية")
            imageUrl = character!!.imageUrl!!
            title = character!!.titleText!!.get("ar-العربية")
            binding.fab.setOnClickListener { view ->
                showSingleItemListAlert(character!!.text!!)
            }
        } else if (event != null) {
            textContent = event!!.text!!.get("ar-العربية")
            imageUrl = event!!.imageUrl!!
            title = event!!.titleText!!.get("ar-العربية")
            binding.fab.setOnClickListener { view ->
                showSingleItemListAlert(event!!.text!!)
            }
        }

        tvContent.text = textContent
        Glide.with(this).load(imageUrl).into(binding.ivToolbarLayout)
        toolbarLayout.title = title
        toolbarLayout.bringToFront()
    }

    fun showSingleItemListAlert(text: Map<String, String>) {
        val keys = text.keys.toTypedArray()
        AlertDialog.Builder(this)
            .setTitle("Choose an option")
            .setItems(keys) { dialog, which ->
                val selectedKey = keys[which]
                tvContent.text = text[selectedKey]
                dialog.dismiss()
            }
            .show()
    }
}