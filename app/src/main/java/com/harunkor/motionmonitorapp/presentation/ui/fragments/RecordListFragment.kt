package com.harunkor.motionmonitorapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harunkor.motionmonitorapp.BR
import com.harunkor.motionmonitorapp.R
import com.harunkor.motionmonitorapp.databinding.FragmentRecordListBinding
import com.harunkor.motionmonitorapp.presentation.ui.adapter.RecordListAdapter
import com.harunkor.motionmonitorapp.presentation.viewmodel.move.MoveViewModel
import com.harunkor.motionmonitorapp.utils.Response
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecordListFragment : Fragment() {

    private val moveViewModel by viewModels<MoveViewModel>()
    private lateinit var recordListAdapter:RecordListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recordListBinding: FragmentRecordListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recordListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_record_list
            ,container,false)
        return recordListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        when(val resp = moveViewModel.allMovements.value){
            is Response.Loading -> {
                // Load
            }
            is Response.Success -> {
                // resp.data
                recordListAdapter = RecordListAdapter(resp.data,{ movemodel ->

                    val action = RecordListFragmentDirections
                        .actionRecordListFragmentToHomeFragment().setMovemodel(movemodel)
                    findNavController().navigate(action)

                })
                linearLayoutManager = LinearLayoutManager(context)
                recordListBinding.recylerRecord.layoutManager=linearLayoutManager
                recordListBinding.setVariable(BR.adapterRecordList,recordListAdapter)
            }
            is Response.Error -> {
                //resp.message
            }
        }

    }

}