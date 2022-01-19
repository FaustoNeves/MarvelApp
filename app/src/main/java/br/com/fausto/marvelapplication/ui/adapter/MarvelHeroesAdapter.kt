package br.com.fausto.marvelapplication.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.data.dtos.MarvelHeroDTO
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_hero.view.*

class MarvelHeroesAdapter(
    private val listOfMarvelHeroes: List<MarvelHeroDTO>,
    private val context: Context,
    private val clickListener: ((Int) -> Unit)?

) : RecyclerView.Adapter<MarvelHeroesAdapter.MarvelHeroesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelHeroesViewHolder {
        return MarvelHeroesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_hero, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MarvelHeroesViewHolder, position: Int) {
        holder.bind(listOfMarvelHeroes[position])
    }

    override fun getItemCount() =
        listOfMarvelHeroes.size

    inner class MarvelHeroesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(marvelHeroDTO: MarvelHeroDTO) {

            itemView.hero_name.text = marvelHeroDTO.name
            itemView.setOnClickListener {
                clickListener?.let { view -> view(marvelHeroDTO.id) }
            }
            Picasso.get().load(marvelHeroDTO.imagePath + "/portrait_uncanny.jpg")
                .into(itemView.hero_image)
        }
    }
}