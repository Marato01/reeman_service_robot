package com.example.logging_plugin;

import androidx.annotation.NonNull;

import com.reeman.log.FileLoggingTree;

import java.util.Collections;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import timber.log.Timber;

/** LoggingPlugin */
public class LoggingPlugin implements FlutterPlugin, MethodCallHandler {
    private MethodChannel channel;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "logging_plugin");
        channel.setMethodCallHandler(this);

        // Initialize Timber with FileLoggingTree
        Timber.plant(new FileLoggingTree(
                Timber.DEBUG, // Log level
                true, // Use Android logcat as well
                flutterPluginBinding.getApplicationContext().getFilesDir().getAbsolutePath(),
                "DefaultTAG",
                Collections.singletonList("logs") // Log subdirectories
        ));
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("log")) {
            String level = call.argument("level");
            String tag = call.argument("tag");
            String message = call.argument("message");

            // Log the message using Timber
            switch (level) {
                case "debug":
                    Timber.tag(tag).d(message);
                    break;
                case "info":
                    Timber.tag(tag).i(message);
                    break;
                case "warn":
                    Timber.tag(tag).w(message);
                    break;
                case "error":
                    Timber.tag(tag).e(message);
                    break;
                default:
                    Timber.tag(tag).v(message);
                    break;
            }
            result.success("Log recorded successfully");
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }
}

