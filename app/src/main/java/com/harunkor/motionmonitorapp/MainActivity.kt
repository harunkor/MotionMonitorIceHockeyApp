package com.harunkor.motionmonitorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /*
      private fun getAllMoves(){
          when(val resp = moveViewModel.allMovements.value){
              is Response.Loading -> {
                  // Load
              }
              is Response.Success -> {
                 // resp.data
                  val list = resp.data
                  Log.v("DENEME",list.size.toString())
              }
              is Response.Error -> {
                  //resp.message
              }
          }
      }*/

    /*
    private fun addMove(moveEntity: MoveEntity){
        moveViewModel.addMove(moveEntity)
        when(val resp= moveViewModel.isAddMoveState.value){
            is Response.Loading -> {

            }
            is Response.Success -> {
                //resp.data
            }
            is Response.Error -> {
               // resp.message
            }
        }
    }*/

}