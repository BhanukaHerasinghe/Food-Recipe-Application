package com.example.androidcw2

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ImageMealAdp (private val imagePair : List<ImageMeal>) :
        RecyclerView.Adapter<ImageMealAdp.ViewHolder>() {

// . It takes a View as input, and it initializes the "imageView" and "descriptionText" variables by finding the respective views by their ids.
  class ViewHolder (photoview:View) : RecyclerView.ViewHolder(photoview)
  {
      val imageView:ImageView =photoview.findViewById(R.id.photoview)
      val descriptionText: TextView = photoview.findViewById(R.id.text_description)
  }

// function inflates the view from "image_description" layout and returns a new ViewHolder instance.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val display = LayoutInflater.from(parent.context).inflate(R.layout.image_description,parent,false)
        return ViewHolder(display)
    }
//  It sets the image and description of the current "ImageMeal" object to the corresponding ImageView and TextView in the ViewHolder.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = imagePair[position]
        holder.imageView.setImageBitmap(match.image)
        holder.descriptionText.text = (match.description)
    Log.d("==============================================",match.description)
    }

    override fun getItemCount(): Int = imagePair.size


}