# Created on : 22 January 2025, 11:25 AM
# Author     : Peter Withers <peter.withers@mpi.nl>

# this script generates a matrix of stimuli 60 x 60 with 60 sentences

outputDirectory=$(dirname $(readlink -f $0))
echo $outputDirectory
echo "" > $outputDirectory/stimuli_fragment.xml
for animal in Elephant Tiger Lion Bear Giraffe Zebra Kangaroo Panda Koala Dolphin Whale Shark Crocodile Alligator Hippopotamus Rhinoceros Cheetah Leopard Wolf Fox Deer Moose Bison Gorilla Chimpanzee Orangutan Sloth Hedgehog Rabbit Squirrel Raccoon Skunk Otter Beaver Bat Owl Eagle Falcon Parrot Penguin Swan Crane Flamingo Ostrich Peacock Pigeon Crow Seagull Turtle Tortoise Snake Lizard Frog Salamander Antelope Buffalo Camel Horse Donkey Sheep; do
    echo $animal
    echo "<stimulus identifier=\"animal_$animal\" code=\"$animal\" tags=\"animal\" label=\"$animal\" />" >> $outputDirectory/stimuli_fragment.xml
done
for verb in Accept Allow Appreciate Assist Authorize Beware Celebrate Cherish Commend Compliment Concede Confirm Consider Consult Contribute Defer Deliver Deny Describe Encourage Engage Enlighten Entertain Examine Excuse Explain Express Facilitate Favor Grant Help Honor Identify Imagine Inquire Inform Inspire Invite Join Mention Observe Offer Permit Polish Pray Promise Provide Recommend Refine Regard Respect Respond Support Thank Understand Uphold Welcome Wish Advise; do
    echo $verb
    echo "<stimulus identifier=\"verb_$verb\" code=\"$verb\" tags=\"verb\" label=\"$verb\" />" >> $outputDirectory/stimuli_fragment.xml
    for animal in Elephant Tiger Lion Bear Giraffe Zebra Kangaroo Panda Koala Dolphin Whale Shark Crocodile Alligator Hippopotamus Rhinoceros Cheetah Leopard Wolf Fox Deer Moose Bison Gorilla Chimpanzee Orangutan Sloth Hedgehog Rabbit Squirrel Raccoon Skunk Otter Beaver Bat Owl Eagle Falcon Parrot Penguin Swan Crane Flamingo Ostrich Peacock Pigeon Crow Seagull Turtle Tortoise Snake Lizard Frog Salamander Antelope Buffalo Camel Horse Donkey Sheep; do
        echo $animal
        if [ ! -f $outputDirectory/$animal'_'$verb.png ]; then
            convert -gravity center -size 128x128 -background blue -fill white -pointsize 18 label:"$animal\n$verb" $outputDirectory/$animal'_'$verb.png
        fi
    done
done

for sentence in "The [animal] [verb] the fruit. " "The [animal] will [verb] you to pass. " "The [animal] [verb] the quiet. " "The [animal] can [verb] you with the task. " "The [animal] will [verb] the move. " "The [animal] [verb] of predators. " "The [animal] will [verb] the victory. " "The [animal] [verb] the bamboo. " "The [animal] will [verb] your efforts. " "The [animal] [verb] your swimming. " "The [animal] [verb] the point. " "The [animal] will [verb] the details. " "The [animal] [verb] the threat. " "The [animal] will [verb] with others. " "The [animal] [verb] to the group. " "The [animal] will [verb] to the leader. " "The [animal] [verb] the message fast. " "The [animal] will [verb] the accusation. " "The [animal] will [verb] the situation. " "The [animal] [verb] you to be clever. " "The [animal] will [verb] with the group. " "The [animal] will [verb] you on the path. " "The [animal] will [verb] your idea. " "The [animal] [verb] the tools. " "The [animal] will [verb] your absence. " "The [animal] [verb] the concept. " "The [animal] will [verb] gratitude. " "The [animal] will [verb] the process. " "The [animal] [verb] the best route. " "The [animal] will [verb] you access. " "The [animal] will [verb] with the food. " "The [animal] will [verb] your decision. " "The [animal] [verb] the problem. " "The [animal] will [verb] a new way. " "The [animal] will [verb] about the details. " "The [animal] will [verb] you soon. " "The [animal] will [verb] your actions. " "The [animal] will [verb] you to join. " "The [animal] [verb] the conversation. " "The [animal] will [verb] the fish. " "The [animal] will [verb] the pond. " "The [animal] will [verb] assistance. " "The [animal] will [verb] the approach. " "The [animal] will [verb] the plan. " "The [animal] will [verb] for good fortune. " "The [animal] will [verb] to return. " "The [animal] will [verb] some guidance. " "The [animal] will [verb] the beach. " "The [animal] will [verb] your opinion. " "The [animal] will [verb] your space. " "The [animal] will [verb] quickly. " "The [animal] will [verb] you for help. " "The [animal] will [verb] your choice. " "The [animal] will [verb] the tradition. " "The [animal] will [verb] the travelers. " "The [animal] will [verb] you well. " "The [animal] will [verb] you to rest. " "The [animal] will [verb] your efforts."; do
    echo $sentence
    sentenceLabel=$(echo $sentence | sed "s/\[/::metadataField_/g" | sed "s/\]/::/g")
    echo "<stimulus identifier=\"$sentence\" tags=\"sentence\" label=\"$sentenceLabel\" />" >> $outputDirectory/stimuli_fragment.xml
done 
