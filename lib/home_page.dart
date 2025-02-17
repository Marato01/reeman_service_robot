import 'package:flutter/material.dart';
import 'package:plugin_remman/logging_plugin.dart';

class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Logging Plugin Example')),
      body: Center(
        child: ElevatedButton(
          onPressed: () async {
            await LoggingPlugin.log(
              level: 'debug',
              tag: 'MyApp',
              message: 'This is a debug message!',
            );
            print('Log sent to native system!');
          },
          child: Text('Send Log'),
        ),
      ),
    );
  }
}
