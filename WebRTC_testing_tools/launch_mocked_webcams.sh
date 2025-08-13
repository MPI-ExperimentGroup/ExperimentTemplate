#!/bin/bash

cd $(dirname "$0")

if [ ! -e "videoA.y4m" ]; then
    ffmpeg -f lavfi -i color=c=red:s=320x240:d=30 -vf "drawtext=fontsize=48:fontcolor=white:font=Arial:text='WebCam A':x=20:y=20, drawtext=fontsize=28:fontcolor=white:text='%{pts\:hms}':x=20:y=100" -pix_fmt yuv420p videoA.y4m
    ffmpeg -f lavfi -i color=c=green:s=320x240:d=30 -vf "drawtext=fontsize=48:fontcolor=white:font=Arial:text='WebCam B':x=20:y=20, drawtext=fontsize=28:fontcolor=white:text='%{pts\:hms}':x=20:y=100" -pix_fmt yuv420p videoB.y4m
    ffmpeg -f lavfi -i color=c=blue:s=320x240:d=30 -vf "drawtext=fontsize=48:fontcolor=white:font=Arial:text='WebCam C':x=20:y=20, drawtext=fontsize=28:fontcolor=white:text='%{pts\:hms}':x=20:y=100" -pix_fmt yuv420p videoC.y4m
    ffmpeg -f lavfi -i "sine=frequency=1000:duration=30" -ar 48000 -ac 1 -sample_fmt s16 audioA.wav
    ffmpeg -f lavfi -i "sine=frequency=800:duration=30" -ar 48000 -ac 1 -sample_fmt s16 audioB.wav
    ffmpeg -f lavfi -i "sine=frequency=600:duration=30" -ar 48000 -ac 1 -sample_fmt s16 audioC.wav
fi

mkdir -p /tmp/mocked-webcams-memberA /tmp/mocked-webcams-memberB /tmp/mocked-webcams-memberC

"/Applications/Google Chrome.app/Contents/MacOS/Google Chrome" \
  --user-data-dir="/tmp/mocked-webcams-memberA" \
  --no-sandbox \
  --use-fake-device-for-media-stream \
  --use-file-for-fake-video-capture="$(pwd)/videoA.y4m" \
  --use-file-for-fake-audio-capture="$(pwd)/audioA.wav" \
  "https://frinexstaging.mpi.nl/group_webcam_example/?mockuser=mock_cams_a&group=mock_cams&member=A" &
#   "https://frinexstaging.mpi.nl/recording_indicator_example/#start"&
#   "https://frinexstaging.mpi.nl/group_webcam_example/webrtctestpage.html"&

"/Applications/Google Chrome.app/Contents/MacOS/Google Chrome" \
  --user-data-dir="/tmp/mocked-webcams-memberB" \
  --no-sandbox \
  --use-fake-device-for-media-stream \
  --use-file-for-fake-video-capture="$(pwd)/videoB.y4m" \
  --use-file-for-fake-audio-capture="$(pwd)/audioB.wav" \
  "https://frinexstaging.mpi.nl/group_webcam_example/?mockuser=mock_cams_b&group=mock_cams&member=B" &

"/Applications/Google Chrome.app/Contents/MacOS/Google Chrome" \
  --user-data-dir="/tmp/mocked-webcams-memberC" \
  --no-sandbox \
  --use-fake-device-for-media-stream \
  --use-file-for-fake-video-capture="$(pwd)/videoC.y4m" \
  --use-file-for-fake-audio-capture="$(pwd)/audioC.wav" \
  "https://frinexstaging.mpi.nl/group_webcam_example/?mockuser=mock_cams_c&group=mock_cams&member=C" &
#   "https://frinexstaging.mpi.nl/group_webcam_example/webrtctestpage.html"&
