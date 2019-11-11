#import "ButtonPressPlugin.h"
#import <button_press/button_press-Swift.h>

@implementation ButtonPressPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftButtonPressPlugin registerWithRegistrar:registrar];
}
@end
