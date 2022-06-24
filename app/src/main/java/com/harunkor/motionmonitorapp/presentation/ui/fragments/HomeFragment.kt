package com.harunkor.motionmonitorapp.presentation.ui.fragments

import android.content.res.Resources
import android.opengl.Visibility
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.harunkor.motionmonitorapp.R
import com.harunkor.motionmonitorapp.databinding.FragmentHomeBinding
import com.harunkor.motionmonitorapp.domain.model.MoveEntity
import com.harunkor.motionmonitorapp.domain.usecase.SensorUseCase
import com.harunkor.motionmonitorapp.presentation.viewmodel.move.MoveViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {


    val args: HomeFragmentArgs by navArgs()
    lateinit var jobPreview:Job
    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private val moveViewModel by viewModels<MoveViewModel>()

    @Inject
    lateinit var sensorUseCase: SensorUseCase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding = DataBindingUtil
            .inflate(inflater,R.layout.fragment_home,container,false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(args.movemodel==null){
            setUseCase()
            init()
        }else{
            setPreviewUseCase(args.movemodel!!)
            previewInit()
            onBackPressed()
        }

    }
    private fun  setUseCase(){
        fragmentHomeBinding.hockeyball.x=100.0f
        fragmentHomeBinding.hockeyball.y=100.0f
        sensorUseCase.setCricketImageView(fragmentHomeBinding.hockeyball)
        sensorUseCase.setCricketBallRadius(25)
        sensorUseCase.setSceneFrameWidth(fragmentHomeBinding.frame.width)
        sensorUseCase.setSceneFrameHeight(fragmentHomeBinding.frame.height)
        sensorUseCase.setRecord(moveViewModel)
        setDisplayMetrics()
    }

    private fun setDisplayMetrics(){
        val metrics = Resources.getSystem().getDisplayMetrics()
        sensorUseCase.setScreenWidth(metrics.widthPixels)
        sensorUseCase.setScreenHeight(metrics.heightPixels)
    }

    private fun init(){
        fragmentHomeBinding.recordCheckButton.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (compoundButton.id == R.id.record_check_button && isChecked) {
                sensorUseCase.startSensor()
            } else {
                sensorUseCase.stopSensor()
            }
        }

        fragmentHomeBinding.listButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recordListFragment)
        }

    }

    private fun  setPreviewUseCase(moveEntity: MoveEntity){
        fragmentHomeBinding.hockeyball.x=100.0f
        fragmentHomeBinding.hockeyball.y=100.0f
        sensorUseCase.setCricketImageView(fragmentHomeBinding.hockeyball)
        sensorUseCase.setCricketBallRadius(25)
        sensorUseCase.setSceneFrameWidth(fragmentHomeBinding.frame.width)
        sensorUseCase.setSceneFrameHeight(fragmentHomeBinding.frame.height)
        sensorUseCase.setRecord(moveViewModel)
        setDisplayMetrics()

        jobPreview=GlobalScope.launch(Dispatchers.Main) {
            sensorUseCase.playPreviewRecord(moveEntity)
        }
    }

    private fun previewInit(){
        fragmentHomeBinding.recordCheckButton.visibility = View.GONE
        fragmentHomeBinding.listButton.visibility = View.GONE
    }

    private fun onBackPressed(){
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                jobPreview.cancel()
                findNavController().popBackStack()
            }
        })
    }



}