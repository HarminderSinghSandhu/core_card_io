import 'dart:async';

import 'package:flutter/services.dart';

class CoreCardIo {
  static const MethodChannel _channel =
      const MethodChannel('core_card_io');

  static Future<dynamic> scanCard(Map<String, dynamic> args) {
    return _channel.invokeMethod('scanCard', args);
  }


  static Future<dynamic> getPlatformVersion() {
    return _channel.invokeMethod('getPlatformVersion', null);
  }
}
