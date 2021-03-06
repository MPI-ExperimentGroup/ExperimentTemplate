# Copyright (C) 2018 Max Planck Institute for Psycholinguistics
#
# This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License
# as published by the Free Software Foundation; either version 2
# of the License, or (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
#

#
# @since September 11, 2018 18:48 PM (creation date)
# @author Peter Withers <peter.withers@mpi.nl>
#

FROM openjdk:8
RUN curl -sL https://deb.nodesource.com/setup_12.x | bash -
RUN apt-get update # --fix-missing
RUN apt-get -y upgrade # --fix-missing
RUN apt-get -y install unzip zip mono-devel build-essential gradle imagemagick maven nodejs npm vim
RUN dpkg --add-architecture i386 && apt-get update && apt-get -y install wine32
ENV ANDROID_VERSION=28 \
    ANDROID_HOME=/android-sdk \
    ANDROID_BUILD_TOOLS_VERSION=29.0.0
ENV PATH=${PATH}:/android-sdk/platform-tools:/android-sdk/cmdline-tools/tools
RUN mkdir /android-sdk \
    && cd /android-sdk \
    && curl -o cmdline-tools.zip "https://dl.google.com/android/repository/commandlinetools-linux-6514223_latest.zip"
#    && curl -o sdk-tools.zip "https://dl.google.com/android/repository/sdk-tools-linux-4333796.zip" \
RUN mkdir /android-sdk/cmdline-tools \
    && cd /android-sdk/cmdline-tools \
    && unzip ../cmdline-tools.zip \
    && rm ../cmdline-tools.zip \
    && yes | /android-sdk/cmdline-tools/tools/bin/sdkmanager --licenses
RUN /android-sdk/cmdline-tools/tools/bin/sdkmanager --update
RUN /android-sdk/cmdline-tools/tools/bin/sdkmanager "build-tools;${ANDROID_BUILD_TOOLS_VERSION}" \
    "platforms;android-${ANDROID_VERSION}" \
    "platform-tools"
#RUN apt-get -y install git node.js npm mono-devel
#RUN npm config set strict-ssl false # todo: remove this stale ssl work around 
RUN npm install npm -g # update npm
RUN npm install -g cordova@9.0.0

#RUN npm install -g electron-forge asar
#RUN electron-forge init init-setup-project
RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | apt-key add -
RUN echo "deb https://dl.yarnpkg.com/debian/ stable main" | tee /etc/apt/sources.list.d/yarn.list
RUN apt update && apt install yarn

RUN git clone https://github.com/electron-userland/electron-webpack-quick-start.git
RUN cd electron-webpack-quick-start \
    && yarn \
    && yarn dist

#RUN cd init-setup-project \
#&& npm install express
#RUN sed -i 's/\"squirrel/\"zip/g' init-setup-project/package.json \
# && cat init-setup-project/package.json 
#RUN cd init-setup-project \
#    && electron-forge make --platform=win32
#RUN cd init-setup-project \
#    && electron-forge make --platform=darwin
#RUN cd init-setup-project \
#    && electron-forge make --platform=linux --arch=ia32 
#RUN cd init-setup-project \
#    && electron-forge make --platform=linux --arch=x64
#RUN npm install -g electron-forge
#RUN /usr/bin/npm install -g electron-compile
#CMD ["/bin/bash"] [ls /target]#, "/target/setup-cordova.sh"]
#WORKDIR /home/petwit/docker-testing
COPY android-keys /android-keys


#RUN mkdir /electron
#RUN wget https://github.com/electron/electron/releases/download/v2.0.10/electron-v2.0.10-darwin-x64.zip -O /electron/darwin-x64.zip
#RUN wget https://github.com/electron/electron/releases/download/v2.0.10/electron-v2.0.10-win32-x64.zip -O /electron/win32-x64.zip

RUN git clone --depth 30000 https://github.com/MPI-ExperimentGroup/ExperimentTemplate.git

RUN sed -i 's|<versionCheck.allowSnapshots>true</versionCheck.allowSnapshots>|<versionCheck.allowSnapshots>false</versionCheck.allowSnapshots>|g' /ExperimentTemplate/pom.xml

RUN cd /ExperimentTemplate \
    && sed -i '/war/{n;s/-testing-SNAPSHOT/.'$(git rev-list --count --all gwt-cordova)'-testing/}' gwt-cordova/pom.xml
RUN cd /ExperimentTemplate \
    && sed -i '/adaptive-vocabulary-assessment-module/{n;s/-testing-SNAPSHOT/.'$(git rev-list --count --all AdaptiveVocabularyAssessmentModule)'-testing/}' /ExperimentTemplate/AdaptiveVocabularyAssessmentModule/pom.xml /ExperimentTemplate/gwt-cordova/pom.xml
RUN cd /ExperimentTemplate \
    && sed -i '/Frinex Experiment Designer/{n;s/-testing-SNAPSHOT/.'$(git rev-list --count --all ExperimentDesigner)'-testing/}' /ExperimentTemplate/ExperimentDesigner/pom.xml
RUN cd /ExperimentTemplate \
    && sed -i '/frinex-admin/{n;s/-testing-SNAPSHOT/.'$(git rev-list --count --all registration)'-testing/}' /ExperimentTemplate/registration/pom.xml
RUN cd /ExperimentTemplate \
    && sed -i '/common/{n;s/-testing-SNAPSHOT/.'$(git rev-list --count --all common)'-testing/}' /ExperimentTemplate/common/pom.xml /ExperimentTemplate/registration/pom.xml /ExperimentTemplate/gwt-cordova/pom.xml \
    && sed -i '/common/{n;s/-testing-SNAPSHOT/.'$(git rev-list --count --all common)'-testing/}' /ExperimentTemplate/AdaptiveVocabularyAssessmentModule/pom.xml
RUN cd /ExperimentTemplate \
    && sed -i '/frinex-parent/{n;s/-testing-SNAPSHOT/.'$(expr $(git rev-list --count --all) - 1)'-testing/}' /ExperimentTemplate/pom.xml /ExperimentTemplate/*/pom.xml \
    && sed -i '/Frinex Parent/{n;s/-testing-SNAPSHOT/.'$(expr $(git rev-list --count --all) - 1)'-testing/}' /ExperimentTemplate/pom.xml /ExperimentTemplate/*/pom.xml

RUN mkdir /ExperimentTemplate/target

RUN cd /ExperimentTemplate \
    && mvn install -Dexperiment.configuration.name=alloptions

RUN mkdir /target

RUN cd /ExperimentTemplate/gwt-cordova \
    && convert -gravity center -size 128x128 -background blue -fill white -pointsize 80 label:"WSE" /ExperimentTemplate/gwt-cordova/src/main/static/with_stimulus_example/icon.png \
    && cp /ExperimentTemplate/gwt-cordova/src/main/static/with_stimulus_example/icon.png /ExperimentTemplate/gwt-cordova/src/main/static/with_stimulus_example/splash.png
RUN cd /ExperimentTemplate/gwt-cordova \
    && mvn clean install -Dexperiment.configuration.name=with_stimulus_example
RUN cd /ExperimentTemplate/gwt-cordova \
    && bash /ExperimentTemplate/gwt-cordova/target/setup-cordova.sh \
    && cp /ExperimentTemplate/gwt-cordova/target/app-release.apk /target/with_stimulus_example.apk

RUN cd /ExperimentTemplate/gwt-cordova \
    && mkdir /ExperimentTemplate/gwt-cordova/src/main/static/rosselfieldkit \
    && convert -gravity center -size 128x128 -background blue -fill white -pointsize 80 label:"RFK" /ExperimentTemplate/gwt-cordova/src/main/static/rosselfieldkit/icon.png \
    && cp /ExperimentTemplate/gwt-cordova/src/main/static/rosselfieldkit/icon.png /ExperimentTemplate/gwt-cordova/src/main/static/rosselfieldkit/splash.png
RUN cd /ExperimentTemplate/gwt-cordova \
    && mvn clean install -Dexperiment.configuration.name=rosselfieldkit
RUN cd /ExperimentTemplate/gwt-cordova \
    && bash /ExperimentTemplate/gwt-cordova/target/setup-cordova.sh \
    && cp /ExperimentTemplate/gwt-cordova/target/app-release.apk /target/rosselfieldkit.apk


WORKDIR /target
VOLUME ["/output"]
