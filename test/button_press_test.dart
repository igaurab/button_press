import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:button_press/button_press.dart';

void main() {
  const MethodChannel channel = MethodChannel('button_press');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await ButtonPress.platformVersion, '42');
  });
}
