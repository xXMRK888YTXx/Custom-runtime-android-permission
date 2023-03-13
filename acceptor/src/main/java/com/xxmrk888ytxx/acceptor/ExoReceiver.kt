package com.xxmrk888ytxx.acceptor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.xxmrk888ytxx.share.Const
import com.xxmrk888ytxx.share.Const.EXO_ACTION

class ExoReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if(intent?.action == EXO_ACTION) {
            Log.d(Const.ACCEPTOR_LOG_TAG,intent.getStringExtra(Const.EXO_DATA_KEY).toString())
        }
    }
}