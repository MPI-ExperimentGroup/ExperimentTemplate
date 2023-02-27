#!/bin/bash
pwd
cd "$(dirname "$0")"
pwd
appname=@experiment.configuration.name@-@project.artifactId@-@project.version@
rm -rf $appname-electron
unzip $appname-electron.zip -d $appname-electron
cd $appname-electron
pwd
ls

if [ -f src/renderer/icon.png ];
then
    convert -resize 512x512^ -gravity center -extent 512x512 -quality 100 src/renderer/icon.png ./icon.png
else
    echo "icon.png not found (512x512)";
    cd "$(dirname "$0")"
    rm -rf $appname-electron
    exit 1
fi
touch src/renderer/index.js

#/usr/bin/npm config set prefix '/srv/ExperimentTemplate/.npm-global'
#NPM_CONFIG_PREFIX=/srv/ExperimentTemplate/.npm-global
#PATH=/srv/ExperimentTemplate/.npm-global/bin:$PATH
#npm install -g electron-forge 
#npm install electron-compile
#npm install
pwd
#ls /srv/ExperimentTemplate/gwt-cordova/target/with_stimulus_example-frinex-gui-1.1-stable-SNAPSHOT-electron/node_modules/
#ls -l /srv/ExperimentTemplate/gwt-cordova/target/with_stimulus_example-frinex-gui-1.1-stable-SNAPSHOT-electron/node_modules/electron-compile/lib/
#electron-forge init $appname
#cd $appname
#electron-forge package --platform=win32 --arch=x64

#cp -r /init-setup-project/node_modules /target/$appname-electron/

# this webjars copy is not required anymore since it is done in the pom and the directory has changed
#mv /target/$appname-electron/src/renderer/webjars/jquery/*/*.* /target/$appname-electron/src/renderer/webjars/jquery/
#mv /target/$appname-electron/src/renderer/webjars/stomp-websocket/*/*.* /target/$appname-electron/src/renderer/webjars/stomp-websocket/
#mv /target/$appname-electron/src/renderer/webjars/sockjs-client/*/*.* /target/$appname-electron/src/renderer/webjars/sockjs-client/

#asar pack /target/$appname-electron/ ../$appname.asar

#unzip /electron/win32-x64.zip -d $appname-win32-x64
#unzip /electron/darwin-x64.zip -d $appname-darwin-x64

#rm $appname-darwin-x64/Electron.app/Contents/Resources/default_app.asar
#rm $appname-win32-x64/resources/default_app.asar

#cp ../$appname.asar $appname-darwin-x64/Electron.app/Contents/Resources/default_app.asar
#cp ../$appname.asar $appname-win32-x64/resources/default_app.asar

#mv $appname-darwin-x64/Electron.app $appname-darwin-x64/@experiment.configuration.name@.app
#mv $appname-win32-x64/electron.exe $appname-win32-x64/@experiment.configuration.name@.exe

yarn
yarn dist --win portable
yarn dist --mac zip --dir -c.compression=store -c.mac.identity=null
yarn dist --mac dmg --dir -c.compression=store -c.mac.identity=null
yarn dist --linux snap

cd dist
zip -r ../../@experiment.configuration.name@-win32-x64.zip ./@experiment.configuration.name@*.exe
cp @experiment.configuration.name@*.dmg ../../@experiment.configuration.name@-mac.dmg
cp @experiment.configuration.name@*-mac.zip ../../@experiment.configuration.name@-darwin-x64.zip
cd ..

#electron-forge make --platform=linux --arch=x64
#electron-forge make --platform=linux --arch=ia32
#electron-forge make --platform=darwin
# the win32 target requires wine mono-devel to be available on the build server
#electron-forge make --platform=win32 --arch=x64
#electron-forge make --platform=win32 --arch=ia32

# generate online only versions which do not use the express local server 
# remove previous build output
rm -rf ./dist/*
# remove offline Frinex parts
rm -rf ./src/renderer/ExperimentTemplate
rm -rf ./src/renderer/css
rm -rf ./src/renderer/scss
rm -rf ./src/renderer/static
rm -rf ./src/renderer/webrtc-adaptor
rm -rf ./src/renderer/stomp-websocket
rm -rf ./src/renderer/sockjs-client
rm -rf ./src/renderer/bootstrap
rm -rf ./src/renderer/opus-recorder
rm -rf ./src/renderer/jquery
# replace the express server for online only access via the relevant server
sed -i "s|//ONLINE_OPTION||g" ./src/main/index.js

# build a second set of binaries for online only use
yarn
yarn dist --win portable
yarn dist --mac zip --dir -c.compression=store -c.mac.identity=null
yarn dist --mac dmg --dir -c.compression=store -c.mac.identity=null
yarn dist --linux snap

cd dist
zip -r ../../@experiment.configuration.name@-win32-x64-lt.zip ./@experiment.configuration.name@*.exe
cp @experiment.configuration.name@*.dmg ../../@experiment.configuration.name@-mac.dmg
cp @experiment.configuration.name@*-mac.zip ../../@experiment.configuration.name@-darwin-x64-lt.zip
cd ..

find . -iname '*.zip'

#mkdir /srv/target/electron
#cp out/make/*linux*.zip ../@experiment.configuration.name@-linux.zip
#cp out/make/*win32*.zip ../@experiment.configuration.name@-win32.zip
#cp out/make/*darwin*.zip ../@experiment.configuration.name@-darwin.zip
#cp out/make/*.zip ../

# allow other users (outside the docker image) to modify the resulting files in target, eg mvn clean 
chmod -R a+rwx "$(dirname "$0")"