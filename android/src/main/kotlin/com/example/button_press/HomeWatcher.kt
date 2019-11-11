package com.example.button_press

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log

class HomeWatcher(private val mContext: Context) {
    private val mFilter: IntentFilter
    private var mListener: OnHomePressedListener? = null
    private var mReceiver: InnerReceiver? = null

    init {
        mFilter = IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
    }

    fun setOnHomePressedListener(listener: OnHomePressedListener) {
        mListener = listener
        mReceiver = InnerReceiver()
    }

    fun startWatch() {
        if (mReceiver != null) {
            mContext.registerReceiver(mReceiver, mFilter)
        }
    }

    fun stopWatch() {
        if (mReceiver != null) {
            mContext.unregisterReceiver(mReceiver)
        }
    }

    internal inner class InnerReceiver : BroadcastReceiver() {
        val SYSTEM_DIALOG_REASON_KEY = "reason"
        val SYSTEM_DIALOG_REASON_GLOBAL_ACTIONS = "globalactions"
        val SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps"
        val SYSTEM_DIALOG_REASON_HOME_KEY = "homekey"

        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action == Intent.ACTION_CLOSE_SYSTEM_DIALOGS) {
                val reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY)
                if (reason != null) {
                    Log.e(TAG, "action:$action,reason:$reason")
                    if (mListener != null) {
                        if (reason == SYSTEM_DIALOG_REASON_HOME_KEY) {
                            mListener!!.onHomePressed()
                        } else if (reason == SYSTEM_DIALOG_REASON_RECENT_APPS) {
                            mListener!!.onHomeLongPressed()
                        }
                    }
                }
            }
        }
    }

    companion object {

        internal val TAG = "hg"
    }
}

interface OnHomePressedListener {
    fun onHomePressed()
    fun onHomeLongPressed()
}

