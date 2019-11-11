import 'dart:async';

import 'package:flutter/services.dart';

class ButtonPress {
  static const MethodChannel _channel =
      const MethodChannel('button_press');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  EventChannel _onHomeButtonPress =  EventChannel('onHomeButtonPress');

  Stream<bool> onHomeButtonPress () {
    Stream<bool> data;
    if(data == null ){
      data = _onHomeButtonPress.receiveBroadcastStream().map<bool>(
          (element) => element
      );
    }
    return data;

  }
}
