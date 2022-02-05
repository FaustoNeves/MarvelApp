package br.com.fausto.marvelapplication.ui.screens.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.data.dtos.MarvelHeroDTO
import br.com.fausto.marvelapplication.ui.constants.GeneralConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_hero.view.*

class HomeAdapter(
    private val marvelHeroesList: List<MarvelHeroDTO>,
    private val context: Context,
    private val clickListener: ((Int, String, String, String, String) -> Unit)

) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_hero, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(marvelHeroesList[position])
    }

    override fun getItemCount() =
        marvelHeroesList.size

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(marvelHeroDTO: MarvelHeroDTO) {

            itemView.hero_name.text = marvelHeroDTO.name
            itemView.setOnClickListener {
                clickListener(
                    marvelHeroDTO.id,
                    marvelHeroDTO.imagePath + GeneralConstants.THUMBNAIL_FULL_SIZE,
                    marvelHeroDTO.name,
                    marvelHeroDTO.description,
                    marvelHeroDTO.urlDetail
                )
            }
            Picasso.get()
                .load(marvelHeroDTO.imagePath + GeneralConstants.CHARACTER_THUMBNAIL_SIZE + GeneralConstants.THUMBNAIL_IMAGE_TYPE)
                .into(itemView.hero_image)
        }
    }
}