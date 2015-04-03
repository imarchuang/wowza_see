#!/bin/sh
if [ -d /Library/WowzaStreamingEngine ]
then
	cd /Library/WowzaStreamingEngine/examples/$1
else
	cd /usr/local/WowzaStreamingEngine/examples/$1
fi
if [ -f ../../conf/$1/Application.xml ] 
then
	#echo "Skipping LiveVideoStreaming.  Already configured."
	echo 1
else
	#echo "Installing LiveVideoStreaming..."
	cp -R conf/* ../../conf/
	if [ $? -eq 0 ] 
	then
		echo 1
	else
		echo 0
	fi
fi
if [ ! -d ../../applications/$1 ]
then
	mkdir ../../applications/$1
fi
#if [ "$1" != "all" ]
#	then
#		echo "Restart Wowza Streaming Engine to complete example installation."
#fi
