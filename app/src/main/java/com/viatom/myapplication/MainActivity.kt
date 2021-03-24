package com.viatom.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class MainActivity : AppCompatActivity() {
    lateinit var mSocket: Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            mSocket = IO.socket("http://192.168.5.100:3000/")
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }

        mSocket.connect()


        val x:TouchView=findViewById(R.id.yes)
        x.setG(object:TouchView.Ga{
            override fun yes(x: Int, y: Int) {
                val yesx=ByteArray(4)
                yesx[0]=x.toByte()
                yesx[1]=(x/256).toByte()
                yesx[2]=y.toByte()
                yesx[3]=(y/256).toByte()
                mSocket.emit("info",yesx)
            }

        })
    }


}