#!/bin/bash

cd $(dirname "$0")

if [ ! -e "videoA.y4m" ]; then
    ffmpeg -f lavfi -i color=c=red:s=640x480:d=30 -vf "drawtext=fontsize=48:fontcolor=white:font=Arial:text='WebCam A':x=20:y=20, drawtext=fontsize=28:fontcolor=white:text='%{pts\:hms}':x=20:y=100" -pix_fmt yuv420p videoA.y4m
    ffmpeg -f lavfi -i color=c=green:s=640x480:d=30 -vf "drawtext=fontsize=48:fontcolor=white:font=Arial:text='WebCam B':x=20:y=20, drawtext=fontsize=28:fontcolor=white:text='%{pts\:hms}':x=20:y=100" -pix_fmt yuv420p videoB.y4m
    ffmpeg -f lavfi -i color=c=blue:s=640x480:d=30 -vf "drawtext=fontsize=48:fontcolor=white:font=Arial:text='WebCam C':x=20:y=20, drawtext=fontsize=28:fontcolor=white:text='%{pts\:hms}':x=20:y=100" -pix_fmt yuv420p videoC.y4m
    ffmpeg -f lavfi -i "sine=frequency=1000:duration=30" -af "volume=0.5" audioA.wav
    ffmpeg -f lavfi -i "sine=frequency=800:duration=30" -af "volume=0.5" audioB.wav
    ffmpeg -f lavfi -i "sine=frequency=600:duration=30" -af "volume=0.5" audioC.wav
fi
