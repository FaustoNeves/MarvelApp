package br.com.fausto.marvelapplication.ui.screens.categories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.data.dtos.CategoryDTO
import br.com.fausto.marvelapplication.ui.constants.GeneralConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_category.view.*

class CategoriesAdapter(
    private val categoryList: List<CategoryDTO>,
    private val context: Context,
) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount() = categoryList.size

    inner class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categoryDTO: CategoryDTO) {
            itemView.category_title.text = categoryDTO.title
            Picasso.get()
                .load(
                    categoryDTO.imagePath
                            + GeneralConstants.THUMBNAIL_SIZE_PORTRAIT_UNCANNY
                            + GeneralConstants.THUMBNAIL_IMAGE_TYPE
                ).into(itemView.category_thumbnail)
        }
    }
}