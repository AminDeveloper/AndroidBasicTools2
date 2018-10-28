package com.paraxco.basictools

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.paraxco.basictools.listTools.ListToolsTest
import com.paraxco.basictools.commontools.observers.ObserverList
import com.paraxco.basictools.commontools.observers.TestObserver
import com.paraxco.basictools.imageTools.dialog.MyCustomDialog
import com.paraxco.commontools.Activities.BaseActivity
import com.paraxco.commontools.Observers.RetryHelper
import com.paraxco.commontools.Utils.SmartLogger
import kotlinx.android.synthetic.main.main_activity.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 *
 */

class MainActivity : BaseActivity(), TestObserver.ObserverTest {
    var retryHelper: RetryHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SmartLogger.initLogger(applicationContext)
        setContentView(R.layout.main_activity)
        showListToolsTest.setOnClickListener({
            startListToolsTest()
        })
        showNotificationTest.setOnClickListener({
            startNotificationBadgeTest()
        })
        showImageToolsTest.setOnClickListener({
            startImageToolsTest()
        })
        ObserverTest.setOnClickListener({
            ObserverList.getTestObserver().informObservers(listOf("abc", "cde"))
        })
        ObserverList.getTestObserver().addObserver(this)

        RetryHelperTest.setOnClickListener {
            retryHelper = RetryHelper.getInstanceAndCall(this, {
                SmartLogger.logDebug("doing ...")
                Thread.sleep(500)
                retry()
            })
        }
        DialogTest.setOnClickListener {
            var myCustomDialog = MyCustomDialog()
            myCustomDialog.showDialog(this)
        }

        progressButton.setOnClickListener {
            doAsync {
                Thread.sleep(2000)
//                uiThread {
                progressButton.showButton()
//                }
            }

        }
    }

    private fun retry() {
        doAsync {
            uiThread {
                retryHelper!!.retry()

            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        ObserverList.getTestObserver().removeObserver(this)

    }

    override fun observeChanges(list: MutableList<String>?) {
        Toast.makeText(this, list!!.size.toString(), Toast.LENGTH_LONG).show()
    }

    private fun startListToolsTest() {
        val myIntent = Intent(this, ListToolsTest::class.java)
//        myIntent.putExtra("key", value) //Optional parameters
        this.startActivity(myIntent)

    }

    private fun startImageToolsTest() {
        val myIntent = Intent(this, ImageToolsTest::class.java)
//        myIntent.putExtra("key", value) //Optional parameters
        this.startActivity(myIntent)
    }

    private fun startNotificationBadgeTest() {
        val myIntent = Intent(this, NotificationBadgeTest::class.java)
//        myIntent.putExtra("key", value) //Optional parameters
        this.startActivity(myIntent)
    }


//    private fun startPage(NextActivity: Class<*>) {
//        val myIntent = Intent(this, NextActivity::class.java)
////        myIntent.putExtra("key", value) //Optional parameters
//        this.startActivity(myIntent)
//    }
}