package core.cardio.core_card_io_example


import android.app.Activity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.plugin.common.MethodChannel
import android.content.Intent
import android.util.Log
import io.flutter.embedding.android.FlutterFragmentActivity
import io.flutter.plugin.common.MethodCall
import org.json.JSONObject


class MainActivity : FlutterFragmentActivity() {

    private var result: MethodChannel.Result? = null

    companion object {
        private const val CHANNEL = "nativeConnection"
        var dataStrings: MethodCall?=null
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
            .setMethodCallHandler { call, result ->


            }
    }

    private fun startNewActivity() {
//        val intent = Intent(this, SwipeCardEditActivity::class.java)
//        startActivityForResult(intent, 200)
    }



}

