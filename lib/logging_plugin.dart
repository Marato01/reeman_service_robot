import 'package:flutter/services.dart';

class LoggingPlugin {
  static const MethodChannel _channel = MethodChannel('logging_plugin');

  /// Logs a message to the native logging system.
  static Future<void> log({
    required String level,
    required String tag,
    required String message,
  }) async {
    await _channel.invokeMethod('log', {
      'level': level,
      'tag': tag,
      'message': message,
    });
  }
}
