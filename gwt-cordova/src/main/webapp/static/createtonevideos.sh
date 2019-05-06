# @since 3 May 2015 10:23 AM (creation date)
# @author Peter Withers <peter.withers@mpi.nl>

for periodMs in 100 500 1000 2000 5000
do
    fileNameOut=$periodMs'ms'
    echo $fileNameOut
    convert -gravity center -size 128x128 -background black -fill white -font /Library/Fonts/Arial\ Black.ttf -pointsize 9 label:"$fileNameOut" "$fileNameOut.jpg"
    periodS=$(echo "scale=2 ; $periodMs / 1000" | bc)
    echo $periodS
    ffmpeg -n -f lavfi -i "sine=frequency=1000:duration=0$periodS" $fileNameOut"_a.mp3"
    ffmpeg -n -f lavfi -i "sine=frequency=1000:duration=0$periodS" $fileNameOut"_a.ogg"
    ffmpeg -n  -i "$fileNameOut.jpg" -f lavfi -i "sine=frequency=1000:duration=0$periodS" $fileNameOut"_v.ogv"
    ffmpeg -n  -i "$fileNameOut.jpg" -f lavfi -i "sine=frequency=1000:duration=0$periodS" $fileNameOut"_v.mp4"
    echo "<stimulus audioPath=\""$fileNameOut"_a\" code=\"$fileNameOut\" identifier=\"$fileNameOut\" imagePath=\"$fileNameOut.jpg\" label=\"$fileNameOut\" pauseMs=\"$periodMs\" tags=\"ToneVideos\" videoPath=\""$fileNameOut"_v\"/>" >> stimulus.txt
done