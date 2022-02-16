package br.com.fausto.marvelapplication.ui.screens.comics.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.data.dtos.ComicsDTO
import br.com.fausto.marvelapplication.ui.constants.GeneralConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_comics.view.*

class ComicsAdapter(
    private val comicsList: List<ComicsDTO>,
    private val context: Context,
) : RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        return ComicsViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_comics, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bind(comicsList[position])
    }

    override fun getItemCount() = comicsList.size

    inner class ComicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comicsDTO: ComicsDTO) {
            itemView.comics_title.text = comicsDTO.title
            Picasso.get()
                .load(
                    comicsDTO.imagePath
                            + GeneralConstants.THUMBNAIL_FULL_SIZE
                ).into(itemView.comics_thumbnail)
        }
    }
}