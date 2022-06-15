package core.cardio.sdk

import android.app.Activity
import android.content.Intent
import android.os.Build
import io.card.payment.CardIOActivity
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class CoreCardIoPlugin() : FlutterPlugin, MethodChannel.MethodCallHandler, ActivityAware {
      var pendingResult: MethodChannel.Result?=null
    lateinit var activity: Activity
    private lateinit var methodCall: MethodCall

    companion object {
        const val MY_SCAN_REQUEST_CODE = 100
    }

    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    var channel: MethodChannel? = null

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "core_card_io")
        channel!!.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        if (pendingResult != null) {
            result.error("ALREADY_ACTIVE", "Scan card is already active", null)
            return
        }


        if (activity == null) {
            result.error("no_activity", "core_card_io plugin requires a foreground activity.", null)
            return
        }
        android.util.Log.e("onMethodCall","${call.method}")
        pendingResult = result
        methodCall = call

        if (call.method == "getPlatformVersion") {
            result.success("Android " + Build.VERSION.RELEASE)
        } else if (call.method == "scanCard"
        ) {
            android.util.Log.e("Inside scanCard","scanCard")
            val scanIntent = Intent(activity, CardIOActivity::class.java)
            var requireExpiry = false
            if (methodCall.hasArgument("requireExpiry")) {
                requireExpiry = methodCall?.argument<Boolean>("requireExpiry") == true
            }
            var requireCVV = false
            if (methodCall!!.hasArgument("requireCVV")) {
                requireCVV = methodCall?.argument<Boolean>("requireCVV") == true
            }
            var requirePostalCode = false
            if (methodCall!!.hasArgument("requirePostalCode")) {
                requirePostalCode = methodCall?.argument<Boolean>("requirePostalCode") == true
            }
            var requireCardHolderName = false
            if (methodCall!!.hasArgument("requireCardHolderName")) {
                requireCardHolderName = methodCall?.argument<Boolean>("requireCardHolderName") == true
            }
            var restrictPostalCodeToNumericOnly = false
            if (methodCall!!.hasArgument("restrictPostalCodeToNumericOnly")) {
                restrictPostalCodeToNumericOnly = methodCall?.argument<Boolean>("restrictPostalCodeToNumericOnly") == true
            }
            var scanExpiry = true
            if (methodCall!!.hasArgument("scanExpiry")) {
                scanExpiry = methodCall?.argument<Boolean>("scanExpiry") == true
            }
            var scanInstructions: String? = null
            if (methodCall!!.hasArgument("scanInstructions")) {
                scanInstructions = methodCall?.argument<String>("scanInstructions")
            }
            var suppressManualEntry = false
            if (methodCall!!.hasArgument("suppressManualEntry")) {
                suppressManualEntry = methodCall?.argument<Boolean>("suppressManualEntry") == true
            }
            var suppressConfirmation = false
            if (methodCall!!.hasArgument("suppressConfirmation")) {
                suppressConfirmation = methodCall?.argument<Boolean>("suppressConfirmation") == true
            }
            var useCardIOLogo = false
            if (methodCall!!.hasArgument("useCardIOLogo")) {
                useCardIOLogo = methodCall?.argument<Boolean>("useCardIOLogo") == true
            }
            var hideCardIOLogo = false
            if (methodCall!!.hasArgument("hideCardIOLogo")) {
                hideCardIOLogo = methodCall?.argument<Boolean>("hideCardIOLogo") == true
            }
            var usePayPalActionbarIcon = true
            if (methodCall!!.hasArgument("usePayPalActionbarIcon")) {
                usePayPalActionbarIcon = methodCall?.argument<Boolean>("usePayPalActionbarIcon") == true
            }
            var keepApplicationTheme = false
            if (methodCall!!.hasArgument("keepApplicationTheme")) {
                keepApplicationTheme = methodCall?.argument<Boolean>("keepApplicationTheme") == true
            }

            // customize these values to suit your needs.
            scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, requireExpiry) // default: false
            scanIntent.putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, scanExpiry)
            scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, requireCVV) // default: false
            scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, requirePostalCode) // default: false
            scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, requireCardHolderName)
            scanIntent.putExtra(CardIOActivity.EXTRA_RESTRICT_POSTAL_CODE_TO_NUMERIC_ONLY, restrictPostalCodeToNumericOnly)
            scanIntent.putExtra(CardIOActivity.EXTRA_SCAN_INSTRUCTIONS, scanInstructions)
            scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, suppressManualEntry)
            scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_CONFIRMATION, suppressConfirmation)
            scanIntent.putExtra(CardIOActivity.EXTRA_USE_CARDIO_LOGO, useCardIOLogo)
            scanIntent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, hideCardIOLogo)
            scanIntent.putExtra(CardIOActivity.EXTRA_USE_PAYPAL_ACTIONBAR_ICON, usePayPalActionbarIcon)
            scanIntent.putExtra(CardIOActivity.EXTRA_KEEP_APPLICATION_THEME, keepApplicationTheme)

            // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
            activity.startActivityForResult(scanIntent, CoreCardIoPlugin.MY_SCAN_REQUEST_CODE)
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPluginBinding) {
        channel!!.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        this.activity = binding.activity
    }

    override fun onDetachedFromActivityForConfigChanges() {
        TODO("Not yet implemented")
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        TODO("Not yet implemented")
    }

    override fun onDetachedFromActivity() {
    }
}
