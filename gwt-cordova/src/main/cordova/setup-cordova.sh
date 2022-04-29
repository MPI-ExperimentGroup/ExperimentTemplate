#!/bin/bash
pwd
cd "$(dirname "$0")"
pwd

if [[ -f /openjdk8/switch_jdk8.sh ]]; then
    bash /openjdk8/switch_jdk8.sh # switch back in time for Cordova
    PATH=$(echo $PATH | sed 's#/usr/local/openjdk-11/bin#/openjdk8/jdk8u265-b01/bin#g');
    #PATH=$(echo $PATH | sed 's#$JAVA_HOME#$JAVA8_HOME#g');
    JAVA_HOME=$JAVA8_HOME
fi

#mvn install
#cd target
appname=@experiment.configuration.name@-@project.artifactId@-@project.version@
rm -rf $appname-cordova
unzip $appname-cordova.zip -d $appname-cordova
cd $appname-cordova

# this webjars copy is not required anymore since it is done in the pom and the directory has changed
#mv www/webjars/jquery/*/*.* www/webjars/jquery/
#mv www/webjars/stomp-websocket/*/*.* www/webjars/stomp-websocket/
#mv www/webjars/sockjs-client/*/*.* www/webjars/sockjs-client/

#bash ../generate-icons.sh

if [ -f www/static/icon.png ];
then
    #cp www/static/icon.png ./icon.png
    convert -resize 512x512^ -gravity center -extent 512x512 -quality 100 www/static/icon.png ./icon.png
else
    echo "icon.png not found";
    cd "$(dirname "$0")"
    rm -rf $appname-cordova
    exit 1
fi
if [ -f www/static/splash.png ];
then
    file www/static/splash.png;
else
    echo "splash.png not found";
    cd "$(dirname "$0")"
    rm -rf $appname-cordova
    exit 1
fi

#/usr/bin/npm config set prefix '/srv/ExperimentTemplate/.npm-global'
#PATH=/srv/ExperimentTemplate/.npm-global/bin:$PATH
#/usr/bin/npm install -g cordova@9.0.0
#cordova requirements

cordova platform add ios
#cordova platform add windows
cordova platform add android
#cordova plugin add https://github.com/danwilson/google-analytics-plugin.git
#cordova plugin add org.apache.cordova.device
#cordova plugin add org.apache.cordova.statusbar
#cordova plugin add org.apache.cordova.splashscreen
#cordova plugin add org.apache.cordova.inappbrowser
#cordova plugin add https://github.com/dawsonloudon/VideoPlayer.git
#cordova plugin add cordova-plugin-media
# (this plugin did not function correctly) cordova plugin add https://github.com/leecrossley/cordova-plugin-social-message.git
#cordova plugin add https://github.com/EddyVerbruggen/SocialSharing-PhoneGap-Plugin.git #cordova plugin add nl.x-services.plugins.socialsharing
#cordova plugin add https://github.com/driftyco/ionic-plugins-keyboard.git # this provides events for keyboard hide and show which are needed to resize the app window area
#cordova plugin add https://github.com/etiennea/phonegap-keyboard.git
#cordova plugin install org.apache.cordova.labs.keyboard
#cordova plugin add https://github.com/GetJobber/cordova-plugin-keyboard.git

#export PATH=${PATH}:/Users/petwit/Library/Android/sdk/platform-tools:/Users/petwit/Library/Android/sdk/tools
#export ANDROID_HOME=/Users/petwit/Library/Android/sdk


#export PATH=$PATH:/usr/local/bin
#export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
#launchctl setenv STUDIO_JDK /library/Java/JavaVirtualMachines/jdk1.8.0_25.jdk
#export ANDROID_HOME="/Users/petwit/Library/Android/sdk"
#export ANDROID_TOOLS="/Users/petwit/Library/Android/sdk/tools"
#export ANDROID_PLATFORM_TOOLS="/Users/petwit/Library/Android/sdk/platform-tools"
#PATH=$PATH:$ANDROID_HOME:$ANDROID_TOOLS:$ANDROID_PLATFORM_TOOLS

if egrep -q "logToSdCard=\"true\"|loadSdCardStimulus|recordingFormat=\"wav\"" www/@experiment.configuration.name@.xml; then
    echo true > logToSdCard-true.txt
    echo "logToSdCard true"
    cordova plugin add /ExperimentTemplate/FieldKitRecorder/
    cordova plugin add cordova-plugin-file
else
    echo false > logToSdCard-false.txt
    echo "logToSdCard false"
fi
if egrep -q "requestNotification" www/@experiment.configuration.name@.xml; then
    echo true > requestNotification-true.txt
    echo "requestNotification true"
    #cordova plugin add cordova-plugin-local-notification
    cordova plugin add https://github.com/katzer/cordova-plugin-local-notifications.git
else
    echo false > requestNotification-false.txt
    echo "requestNotification false"
fi
cordova plugin add cordova-plugin-statusbar
cordova plugin add cordova-plugin-fullscreen 
#cordova plugin add cordova-plugin-media-capture
#cordova plugin add cordova-plugin-camera
cordova plugin add cordova-plugin-whitelist
cordova plugin add cordova-plugin-device
#cordova plugin add cordova-plugin-splashscreen
cordova plugin add cordova-plugin-inappbrowser

#splashResourcesDir=./platforms/ios/@experiment.configuration.name@/Images.xcassets/LaunchImage.launchimage
splashResourcesDir=./platforms/ios/res/Images.xcassets/LaunchImage.launchimage
echo $splashResourcesDir
mkdir -p $splashResourcesDir
# TODO: update iOS icons including Default@2x~universal~anyany.png which cannot be found, config.xml says res/screen/ios/ but this script says ./platforms/ios/@experiment.configuration.name@/Images.xcassets/
splashImage="www/static/splash.png" #"images/splash.gif" 
#iconResourcesDir=./platforms/ios/@experiment.configuration.name@/Images.xcassets/AppIcon.appiconset
iconResourcesDir=./platforms/ios/res/Images.xcassets/AppIcon.appiconset
mkdir -p $iconResourcesDir
iconImage="www/static/icon.png"

echo $splashImage
#file ./platforms/ios/$appname/Resources/splash/*

#echo "making 9 patch splash images"
#convert -background none images/LiI_logo_rgb.jpg -resize 320x320 -matte -bordercolor "rgb(0,158,200)" -border 2 -fill black -draw "line 1,0 1,0" -draw "line 0,1 0,1" -draw "line 0,67 0,67" -draw "line 322,0 322,0" platforms/splash320x320.9.png
#draw9patch platforms/splash320x320.9.png
#convert -background none images/LiI_logo_rgb.jpg -resize 150x150 -matte -bordercolor "rgb(0,158,200)" -border 2 -fill black -draw "line 1,0 1,0" -draw "line 0,1 0,1" -draw "line 0,32 0,32" -draw "line 152,0 152,0" platforms/splash150x150.9.png
#draw9patch platforms/splash150x150.9.png

#echo "making iOS splash images"
convert -resize 640x1136^ -gravity center -extent 640x1136 -quality 100 $splashImage $splashResourcesDir/Default-568h@2x~iphone.png
convert -resize 1334x1334^ -gravity center -extent 750x1334 -quality 100 $splashImage $splashResourcesDir/Default-667h.png
convert -resize 2208x2208^ -gravity center -extent 1242x2208 -quality 100 $splashImage $splashResourcesDir/Default-736h.png
convert -crop +0-100 -resize 2208x1242^ -gravity center -extent 2208x1242 -quality 100 $splashImage $splashResourcesDir/Default-Landscape-736h.png
#convert -resize 1136x1136^ -gravity center -extent 640x1136 -quality 100 $splashImage $splashResourcesDir/Default-568h@2x~iphone.png
convert -resize 2048x1536^ -gravity center -extent 2048x1536 -quality 100 $splashImage $splashResourcesDir/Default-Landscape@2x~ipad.png
convert -resize 1024x768^ -gravity center -extent 1024x768 -quality 100 $splashImage $splashResourcesDir/Default-Landscape~ipad.png
convert -resize 1536x2048^ -gravity center -extent 1536x2048 -quality 100 $splashImage $splashResourcesDir/Default-Portrait@2x~ipad.png
convert -resize 768x1024^ -gravity center -extent 768x1024 -quality 100 $splashImage $splashResourcesDir/Default-Portrait~ipad.png
convert -resize 640x960^ -gravity center -extent 640x960 -quality 100 $splashImage $splashResourcesDir/Default@2x~iphone.png
convert -resize 320x480^ -gravity center -extent 320x480 -quality 100 $splashImage $splashResourcesDir/Default~iphone.png
convert -resize 2436x1125^ -gravity center -extent 2436x1125 -quality 100 $splashImage $splashResourcesDir/Default-Landscape-2436h.png
convert -resize 1125x2436^ -gravity center -extent 1125x2436 -quality 100 $splashImage $splashResourcesDir/Default-2436h.png
#echo "making iOS Single-image launch screen images" 
convert -flatten -resize 2732x2732^ -gravity center -extent 2732x2732 -quality 100 $splashImage $splashResourcesDir/Default@2x~universal~anyany.png
convert -flatten -resize 1278x2732^ -gravity center -extent 1278x2732 -quality 100 $splashImage $splashResourcesDir/Default@2x~universal~comany.png
convert -flatten -resize 1334x750^ -gravity center -extent 1334x750 -quality 100 $splashImage $splashResourcesDir/Default@2x~universal~comcom.png
convert -flatten -resize 2208x2208^ -gravity center -extent 2208x2208 -quality 100 $splashImage $splashResourcesDir/Default@3x~universal~anyany.png
convert -flatten -resize 2208x1242^ -gravity center -extent 2208x1242 -quality 100 $splashImage $splashResourcesDir/Default@3x~universal~anycom.png
convert -flatten -resize 1242x2208^ -gravity center -extent 1242x2208 -quality 100 $splashImage $splashResourcesDir/Default@3x~universal~comany.png
#echo "making Android splash images"
mkdir -p platforms/android/res/drawable-land-hdpi
mkdir -p platforms/android/res/drawable-land-ldpi
mkdir -p platforms/android/res/drawable-land-mdpi
mkdir -p platforms/android/res/drawable-land-xhdpi
mkdir -p platforms/android/res/drawable-port-hdpi
mkdir -p platforms/android/res/drawable-port-ldpi
mkdir -p platforms/android/res/drawable-port-mdpi
mkdir -p platforms/android/res/drawable-port-xhdpi
#cp platforms/splash320x320.9.png platforms/android/res/drawable-land-hdpi/screen.png
#cp platforms/splash150x150.9.png platforms/android/res/drawable-land-ldpi/screen.png
#cp platforms/splash150x150.9.png platforms/android/res/drawable-land-mdpi/screen.png
#cp platforms/splash320x320.9.png platforms/android/res/drawable-land-xhdpi/screen.png
#cp platforms/splash320x320.9.png platforms/android/res/drawable-port-hdpi/screen.png
#cp platforms/splash150x150.9.png platforms/android/res/drawable-port-ldpi/screen.png
#cp platforms/splash150x150.9.png platforms/android/res/drawable-port-mdpi/screen.png
#cp platforms/splash320x320.9.png platforms/android/res/drawable-port-xhdpi/screen.png

convert -flatten -crop +0-100 -resize 800x480^ -gravity center -extent 800x480 -quality 100 $splashImage platforms/android/res/drawable-land-hdpi/screen.png
convert -flatten -crop +0-50 -resize 320x200^ -gravity center -extent 320x200 -quality 100 $splashImage platforms/android/res/drawable-land-ldpi/screen.png
convert -flatten -crop +0-50 -resize 480x320^ -gravity center -extent 480x320 -quality 100 $splashImage platforms/android/res/drawable-land-mdpi/screen.png
convert -flatten -crop +0-100 -resize 1280x720^ -gravity center -extent 1280x720 -quality 100 $splashImage platforms/android/res/drawable-land-xhdpi/screen.png
convert -flatten -resize 480x800^ -gravity center -extent 480x800 -quality 100 $splashImage platforms/android/res/drawable-port-hdpi/screen.png
convert -flatten -resize 200x320^ -gravity center -extent 200x320 -quality 100 $splashImage platforms/android/res/drawable-port-ldpi/screen.png
convert -flatten -resize 320x480^ -gravity center -extent 320x480 -quality 100 $splashImage platforms/android/res/drawable-port-mdpi/screen.png
convert -flatten -resize 720x1280^ -gravity center -extent 720x1280 -quality 100 $splashImage platforms/android/res/drawable-port-xhdpi/screen.png

# copy the ant.properties file with the android key store and alias (key.store= key.alias=) information so that the APK can be signed
#cp ~/android-keys/ant.properties platforms/android/
#cp ~/android-keys/release-signing.properties platforms/android/

echo "building"
cordova prepare
#cordova compile
#cordova build -release
cordova build android --packageType=apk --release --buildConfig /android-keys/frinex-build.json
cordova build android --packageType=bundle --release --buildConfig /android-keys/frinex-build.json
#cordova emulate ios --target="iPad"
#cordova emulate ios --target="iPhone"
#cordova emulate android 

rm platforms/android/release-signing.properties
#echo "make the iOS icons"
convert -resize 180x180! -strip -quality 100 $iconImage $iconResourcesDir/icon-60@3x.png
convert -resize 60x60! -strip -quality 100 $iconImage $iconResourcesDir/icon-60.png
convert -resize 120x120! -strip -quality 100 $iconImage $iconResourcesDir/icon-60@2x.png
convert -resize 76x76! -strip -quality 100 $iconImage $iconResourcesDir/icon-76.png
convert -resize 152x152! -strip -quality 100 $iconImage $iconResourcesDir/icon-76@2x.png
convert -resize 40x40! -strip -quality 100 $iconImage $iconResourcesDir/icon-40.png
convert -resize 80x80! -strip -quality 100 $iconImage $iconResourcesDir/icon-40@2x.png
convert -resize 57x57! -strip -quality 100 $iconImage $iconResourcesDir/icon.png
convert -resize 114x114! -strip -quality 100 $iconImage $iconResourcesDir/icon@2x.png
convert -resize 72x72! -strip -quality 100 $iconImage $iconResourcesDir/icon-72.png
convert -resize 144x144! -strip -quality 100 $iconImage $iconResourcesDir/icon-72@2x.png
convert -resize 29x29! -strip -quality 100 $iconImage $iconResourcesDir/icon-small.png
convert -resize 58x58! -strip -quality 100 $iconImage $iconResourcesDir/icon-small@2x.png
convert -resize 50x50! -strip -quality 100 $iconImage $iconResourcesDir/icon-50.png
convert -resize 100x100! -strip -quality 100 $iconImage $iconResourcesDir/icon-50@2x.png
convert -resize 88x88! -strip -quality 100 $iconImage $iconResourcesDir/icon-44@2x.png
convert -resize 40x40! -strip -quality 100 $iconImage $iconResourcesDir/icon-20@2x.png
#echo "make more iOS icons"
convert -resize 20x20! -strip -quality 100 $iconImage $iconResourcesDir/icon-20.png
convert -resize 60x60! -strip -quality 100 $iconImage $iconResourcesDir/icon-20@3x.png
convert -resize 29x29! -strip -quality 100 $iconImage $iconResourcesDir/icon-29.png
convert -resize 29x29! -strip -quality 100 $iconImage $iconResourcesDir/icon-29@1x.png
convert -resize 58x58! -strip -quality 100 $iconImage $iconResourcesDir/icon-29@2x.png
convert -resize 87x87! -strip -quality 100 $iconImage $iconResourcesDir/icon-29@3x.png
convert -resize 66x66! -strip -quality 100 $iconImage $iconResourcesDir/icon-33@2x.png
convert -resize 92x92! -strip -quality 100 $iconImage $iconResourcesDir/icon-46@2x.png
convert -resize 102x102! -strip -quality 100 $iconImage $iconResourcesDir/icon-51@2x.png
convert -resize 234x234! -strip -quality 100 $iconImage $iconResourcesDir/icon-117@2x.png
convert -resize 167x167! -strip -quality 100 $iconImage $iconResourcesDir/icon-83.5@2x.png
convert -resize 1024x1024! -gravity center -extent 1024x1024 -strip -quality 100 $iconImage $iconResourcesDir/icon-1024.png
convert -resize 48x48! -strip -quality 100 $iconImage $iconResourcesDir/icon-24@2x.png
convert -resize 55x55! -strip -quality 100 $iconImage $iconResourcesDir/icon-27.5@2x.png
convert -resize 172x172! -strip -quality 100 $iconImage $iconResourcesDir/icon-86@2x.png
convert -resize 196x196! -strip -quality 100 $iconImage $iconResourcesDir/icon-98@2x.png
convert -resize 216x216! -strip -quality 100 $iconImage $iconResourcesDir/icon-108@2x.png

# list the schemes available 
#xcodebuild -list
# build the iOS package
#cordova build ios -release
# sign the iOS package
#xcrun -sdk iphoneos PackageApplication -v "build/Release-iphoneos/MyApp.app" -o "build/Release-iphoneos/MyApp.ipa" --sign "iPhone Distribution: NAME (ID)" --embed "PROFILE_UUID.mobileprovision"
#xcodebuild -scheme YOURSCHEMENAME -project MyApp.xcodeproj -alltargets -sdk iphoneos7.0 PROVISIONING_PROFILE="PROFILE_UUID.mobileprovision" -configuration Release

pwd
#cp platforms/android/build/outputs/apk/android-release.apk ~/Desktop/FrinexAPKs/$appname.apk
cp platforms/android/app/build/outputs/apk/release/*.apk ../
cp platforms/android/app/build/outputs/apk/release/*.aab ../
zip -r ../$appname-android.zip platforms/android
zip -r ../$appname-ios.zip platforms/ios
#cp -r platforms/android ~/Desktop/FrinexAPKs/$appname-android

#echo "installing on Android"
#adb install -r platforms/android/build/outputs/apk/android-release.apk

#echo "launching xcode"
#open platforms/ios/$appname.xcodeproj&
#echo "launching xcode"
#open platforms/ios/$appname.xcodeproj

# generate the IPA
#cd platforms/ios/CordovaLib/
#pwd
#xcodebuild -alltargets -project CordovaLib.xcodeproj -sdk iphoneos -configuration Release
#xcodebuild -scheme CordovaLib -project CordovaLib.xcodeproj -sdk iphoneos -configuration Release

#cd ..
#pwd
#xcodebuild -alltargets -project $appname.xcodeproj -sdk iphoneos -configuration Release
#xcodebuild -scheme $appname -project $appname.xcodeproj -sdk iphoneos -configuration Release
#cd build/Release-iphoneos
#cd platforms/ios/
#cd build/emulator
#pwd
#xcrun -sdk iphoneos PackageApplication -v "$(pwd)/$appname.app" -o "$(pwd)/$appname.ipa"

# validate the results
#xcrun -verbose -sdk iphoneos Validation "$(pwd)/$appname.ipa"

pwd
#cd ../../../../
# list the APK files that have been built
find .. -iname *.apk
find .. -iname *.aab

# list the IPA files that have been built
find .. -iname *.ipa


# allow other users (outside the docker image) to modify the resulting files in target, eg mvn clean 
chmod -R a+rwx "$(dirname "$0")"

# cordova run android --device

cd "$(dirname "$0")"
rm -rf $appname-cordova
