package com.example.cocktails

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val drinks: List<Drink>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.list_item, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemNote = drinks[position]
        holder.bindNote(itemNote)
    }

    override fun getItemCount() = drinks.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        val DRINK_KEY = "DRINK_KEY"
        private var view: View = v
        private var drink: Drink? = null
        lateinit var titleTextView: TextView
        lateinit var image: ImageView

        init {
            v.setOnClickListener(this)
        }

        fun bindNote(drink: Drink) {
            this.drink = drink
            titleTextView = view.findViewById(R.id.titleView)
            image = view.findViewById(R.id.imageView3)
            Picasso.with(titleTextView.context)
                .load(drink.image)
                .into(image)

            titleTextView.text = drink.name

        }

        override fun onClick(v: View?) {
            val context = itemView.context
            val myIntent = Intent(context, DetailsActivity::class.java)
            myIntent.putExtra(DRINK_KEY, drink?.id)
            context.startActivity(myIntent)
        }


    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }


}