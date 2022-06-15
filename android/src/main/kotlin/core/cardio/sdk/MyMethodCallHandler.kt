//package core.cardio.core_card_io_sdk
//
//import android.app.Activity
//import android.content.Intent
//import io.card.payment.CardIOActivity
//import io.flutter.embedding.engine.plugins.FlutterPlugin
//import io.flutter.plugin.common.MethodCall
//import io.flutter.plugin.common.MethodChannel
//
//class MyMethodCallHandler(private var flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) : MethodChannel.MethodCallHandler {
//
//
//    lateinit var activity: Activity
//    private var methodCall: MethodCall? = null
//    private var pendingResult: MethodChannel.Result? = null
//    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
//        if (pendingResult != null) {
//            result.error("ALREADY_ACTIVE", "Scan card is already active", null)
//            return
//        }
//
//        pendingResult = result
//        methodCall = call
//
//
//        if (call.method == "scanCard" &&
//            methodCall != null
//        ) {
//            val scanIntent = Intent(activity, CardIOActivity::class.java)
//            var requireExpiry = false
//            if (methodCall!!.hasArgument("requireExpiry")) {
//                requireExpiry = methodCall?.argument<Boolean>("requireExpiry") == true
//            }
//            var requireCVV = false
//            if (methodCall!!.hasArgument("requireCVV")) {
//                requireCVV = methodCall?.argument<Boolean>("requireCVV") == true
//            }
//            var requirePostalCode = false
//            if (methodCall!!.hasArgument("requirePostalCode")) {
//                requirePostalCode = methodCall?.argument<Boolean>("requirePostalCode") == true
//            }
//            var requireCardHolderName = false
//            if (methodCall!!.hasArgument("requireCardHolderName")) {
//                requireCardHolderName = methodCall?.argument<Boolean>("requireCardHolderName") == true
//            }
//            var restrictPostalCodeToNumericOnly = false
//            if (methodCall!!.hasArgument("restrictPostalCodeToNumericOnly")) {
//                restrictPostalCodeToNumericOnly = methodCall?.argument<Boolean>("restrictPostalCodeToNumericOnly") == true
//            }
//            var scanExpiry = true
//            if (methodCall!!.hasArgument("scanExpiry")) {
//                scanExpiry = methodCall?.argument<Boolean>("scanExpiry") == true
//            }
//            var scanInstructions: String? = null
//            if (methodCall!!.hasArgument("scanInstructions")) {
//                scanInstructions = methodCall?.argument<String>("scanInstructions")
//            }
//            var suppressManualEntry = false
//            if (methodCall!!.hasArgument("suppressManualEntry")) {
//                suppressManualEntry = methodCall?.argument<Boolean>("suppressManualEntry") == true
//            }
//            var suppressConfirmation = false
//            if (methodCall!!.hasArgument("suppressConfirmation")) {
//                suppressConfirmation = methodCall?.argument<Boolean>("suppressConfirmation") == true
//            }
//            var useCardIOLogo = false
//            if (methodCall!!.hasArgument("useCardIOLogo")) {
//                useCardIOLogo = methodCall?.argument<Boolean>("useCardIOLogo") == true
//            }
//            var hideCardIOLogo = false
//            if (methodCall!!.hasArgument("hideCardIOLogo")) {
//                hideCardIOLogo = methodCall?.argument<Boolean>("hideCardIOLogo") == true
//            }
//            var usePayPalActionbarIcon = true
//            if (methodCall!!.hasArgument("usePayPalActionbarIcon")) {
//                usePayPalActionbarIcon = methodCall?.argument<Boolean>("usePayPalActionbarIcon") == true
//            }
//            var keepApplicationTheme = false
//            if (methodCall!!.hasArgument("keepApplicationTheme")) {
//                keepApplicationTheme = methodCall?.argument<Boolean>("keepApplicationTheme") == true
//            }
//
//            // customize these values to suit your needs.
//            scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, requireExpiry) // default: false
//            scanIntent.putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, scanExpiry)
//            scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, requireCVV) // default: false
//            scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, requirePostalCode) // default: false
//            scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, requireCardHolderName)
//            scanIntent.putExtra(CardIOActivity.EXTRA_RESTRICT_POSTAL_CODE_TO_NUMERIC_ONLY, restrictPostalCodeToNumericOnly)
//            scanIntent.putExtra(CardIOActivity.EXTRA_SCAN_INSTRUCTIONS, scanInstructions)
//            scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, suppressManualEntry)
//            scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_CONFIRMATION, suppressConfirmation)
//            scanIntent.putExtra(CardIOActivity.EXTRA_USE_CARDIO_LOGO, useCardIOLogo)
//            scanIntent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, hideCardIOLogo)
//            scanIntent.putExtra(CardIOActivity.EXTRA_USE_PAYPAL_ACTIONBAR_ICON, usePayPalActionbarIcon)
//            scanIntent.putExtra(CardIOActivity.EXTRA_KEEP_APPLICATION_THEME, keepApplicationTheme)
//
//            // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
//            activity.startActivityForResult(scanIntent, CoreCardIoPlugin.MY_SCAN_REQUEST_CODE)
//        } else {
//            result.notImplemented()
//        }
//    }
//}