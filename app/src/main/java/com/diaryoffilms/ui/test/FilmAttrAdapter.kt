package com.diaryoffilms.ui.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.diaryoffilms.data.FilmAttr
import com.diaryoffilms.databinding.ItemFilmAttributesBinding

import com.diaryoffilms.ui.common.DataBoundListAdapter

class FilmAttrAdapter(private val itemCallBack: (FilmAttr) -> Unit):
        DataBoundListAdapter<FilmAttr, ItemFilmAttributesBinding>(
                diffCallback = object : DiffUtil.ItemCallback<FilmAttr>(){
                    override fun areItemsTheSame(oldItem: FilmAttr, newItem: FilmAttr)
                            = oldItem.title == newItem.title

                    override fun areContentsTheSame(oldItem: FilmAttr, newItem: FilmAttr)
                            = oldItem == newItem
                }
        ) {

    override fun createBinding(parent: ViewGroup): ItemFilmAttributesBinding {
      return ItemFilmAttributesBinding.inflate(LayoutInflater.from(parent.context),
              parent,false)
    }

    override fun bind(binding: ItemFilmAttributesBinding, item: FilmAttr) {
        binding.item = item
        binding.root.setOnClickListener { itemCallBack }
    }


}