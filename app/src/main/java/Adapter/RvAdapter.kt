package Adapter

import Models.Contact
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilhomjon.kontaktdanoqibolish.R
import kotlinx.android.synthetic.main.item.view.*

class RvAdapter(val list: List<Contact>)
    :RecyclerView.Adapter<RvAdapter.Vh>(){

    inner class Vh(var itemView:View): RecyclerView.ViewHolder(itemView){
        fun onBind(contact: Contact){
            itemView.txt_name.text = contact.name
            itemView.txt_phone.text = contact.phone
            if (contact.image != null){
            itemView.image.setImageBitmap(contact.image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}