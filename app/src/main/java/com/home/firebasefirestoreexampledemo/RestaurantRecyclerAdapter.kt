package com.home.firebasefirestoreexampledemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso

class RestaurantRecyclerAdapter(var dataList: List<RestaurantData>) :
    RecyclerView.Adapter<RestaurantRecyclerAdapter.ConcertViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ConcertViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(
                com.home.firebasefirestoreexampledemo.R.layout.activity_restaurant_recycler_view_item,
                viewGroup,
                false
            )
        return ConcertViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(viewHolder: ConcertViewHolder, position: Int) {
        viewHolder.nameTextView.text = dataList[position].name // 設置餐廳名稱
        viewHolder.discountTextView.text = dataList[position].discount // 設置折抵
        viewHolder.likeTextView.text = dataList[position].like // 設置喜歡數量
        viewHolder.introductionTextView.text = dataList[position].introduction // 設置餐廳簡介
        Picasso.get().load(dataList[position].imageUrl).error(R.drawable.broken_image)
            .into(viewHolder.imageView) // 設置圖片
        if (dataList[position].open) { // 設置是否營業
            viewHolder.openLinearLayout.visibility = View.GONE
        } else {
            viewHolder.openLinearLayout.visibility = View.VISIBLE
        }
        val lastPosition = dataList.size - 1
        if (position == lastPosition) {
            viewHolder.lastPositionView.visibility = View.VISIBLE
        } else {
            viewHolder.lastPositionView.visibility = View.GONE
        }
    }

    class ConcertViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(com.home.firebasefirestoreexampledemo.R.id.imageView)!!
        val discountTextView =
            view.findViewById<TextView>(com.home.firebasefirestoreexampledemo.R.id.discountTextView)!!
        val openLinearLayout =
            view.findViewById<LinearLayout>(com.home.firebasefirestoreexampledemo.R.id.openLinearLayout)!!
        val nameTextView = view.findViewById<TextView>(com.home.firebasefirestoreexampledemo.R.id.nameTextView)!!
        val likeTextView = view.findViewById<TextView>(com.home.firebasefirestoreexampledemo.R.id.likeTextView)!!
        val introductionTextView =
            view.findViewById<TextView>(com.home.firebasefirestoreexampledemo.R.id.introductionTextView)!!
        val lastPositionView = view.findViewById<View>(com.home.firebasefirestoreexampledemo.R.id.lastPositionView)!!
    }
}
