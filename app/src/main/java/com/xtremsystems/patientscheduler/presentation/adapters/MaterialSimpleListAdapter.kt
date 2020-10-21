package com.xtremsystems.patientscheduler.presentation.adapters

import android.graphics.PorterDuff
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.internal.MDAdapter
import kotlinx.android.synthetic.main.md_simplelist_item.view.*
import com.xtremsystems.patientscheduler.R

class MaterialSimpleListAdapter(var callback: Callback) :
    RecyclerView.Adapter<MaterialSimpleListAdapter.SimpleListVH>(), MDAdapter {
    private var dialog: MaterialDialog? = null
    private lateinit var items: MutableList<MaterialSimpleListItem>

    fun add(item: MaterialSimpleListItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun getItem(index: Int): MaterialSimpleListItem {
        return items[index]
    }

    override fun setDialog(dialog: MaterialDialog) {
        this.dialog = dialog
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleListVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.md_simplelist_item, parent, false)
        return SimpleListVH(view, this)
    }

    override fun onBindViewHolder(holder: SimpleListVH, position: Int) {
        if (dialog != null) {
            val item = items[position]
            if (item.getIcon() != null) {
                holder.icon.setImageDrawable(item.getIcon())
                holder.icon.setPadding(
                    item.getIconPadding(),
                    item.getIconPadding(),
                    item.getIconPadding(),
                    item.getIconPadding()
                )
                holder
                    .icon
                    .background
                    .setColorFilter(item.getBackgroundColor(), PorterDuff.Mode.SRC_ATOP)
            } else {
                holder.icon.visibility = View.GONE
            }
            holder.title.setTextColor(dialog!!.builder.itemColor)
            holder.title.text = item.getContent()
            dialog!!.setTypeface(holder.title, dialog!!.builder.regularFont)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface Callback {

        fun onMaterialListItemSelected(dialog: MaterialDialog?, index: Int, item: MaterialSimpleListItem)
    }

    class SimpleListVH(itemView: View, val adapter: MaterialSimpleListAdapter) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val icon: ImageView = itemView.icon
        val title: TextView = itemView.title

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            adapter.callback.onMaterialListItemSelected(
                adapter.dialog, adapterPosition, adapter.getItem(adapterPosition)
            )

        }
    }
}