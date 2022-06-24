package com.harunkor.motionmonitorapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.harunkor.motionmonitorapp.BR
import com.harunkor.motionmonitorapp.R
import com.harunkor.motionmonitorapp.databinding.FragmentRecordCardItemBinding
import com.harunkor.motionmonitorapp.domain.model.MoveEntity

class RecordListAdapter(val allMovements: MutableList<MoveEntity>,
  private val onItemClickHandler: (moveEntity:MoveEntity) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.fragment_record_card_item, parent, false
        )
        return RecordViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RecordViewHolder).onBind(allMovements.get(position),onItemClickHandler)
    }

    override fun getItemCount(): Int {
       return allMovements.size
    }

    class RecordViewHolder(private  val itemBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun onBind(moveEntity: MoveEntity,onItemClickHandler: (moveEntity: MoveEntity) -> Unit){
            val binding = itemBinding as FragmentRecordCardItemBinding
            binding.setVariable(BR.moveEntity, moveEntity)
            binding.recordItem.setOnClickListener { onItemClickHandler(moveEntity) }
        }

    }
}


