package br.com.fausto.marvelapplication.ui.screens.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fausto.marvelapplication.R
import br.com.fausto.marvelapplication.data.dtos.MarvelCharacterDTO
import br.com.fausto.marvelapplication.ui.constants.GeneralConstants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_character.view.*

class HomeAdapter(
    private val charactersList: List<MarvelCharacterDTO>,
    private val context: Context,
    private val clickListener: ((Int, String, String, String, String, Int) -> Unit)

) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item_character, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(charactersList[position], position)
    }

    override fun getItemCount() =
        charactersList.size

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(marvelCharacterDTO: MarvelCharacterDTO, itemPosition: Int) {

            itemView.character_name.text = marvelCharacterDTO.name
            itemView.setOnClickListener {
                clickListener(
                    marvelCharacterDTO.id,
                    marvelCharacterDTO.imagePath + GeneralConstants.THUMBNAIL_IMAGE_TYPE,
                    marvelCharacterDTO.name,
                    marvelCharacterDTO.description,
                    marvelCharacterDTO.urlDetail,
                    itemPosition
                )
            }
            Picasso.get()
                .load(
                    marvelCharacterDTO.imagePath
                            + GeneralConstants.THUMBNAIL_SIZE_PORTRAIT_UNCANNY
                            + GeneralConstants.THUMBNAIL_IMAGE_TYPE
                )
                .into(itemView.character_image)
        }
    }
}