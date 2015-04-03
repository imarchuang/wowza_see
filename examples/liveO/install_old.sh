#!/bin/sh
if [ -d /Library/WowzaStreamingEngine ]
then
	if [ -d /Library/WowzaStreamingEngine/examples/$1 ] 
	then
		cd /Library/WowzaStreamingEngine/examples/$1
	else 
		mkdir ./examples/$1
		cd ./examples/$1
	fi
else
	if [ -d /usr/local/WowzaStreamingEngine/examples/$1 ] 
	then
		cd /usr/local/WowzaStreamingEngine/examples/$1
	else
		mkdir ./exmaples/$1
		cd ./exmaples/$1
	fi	
fi

if [ -f ../../conf/$1/Application.xml ] 
then
	#echo "Skipping LiveVideoStreaming.  Already configured."
	echo 0 
else
	#echo "Installing LiveVideoStreaming..."
	cp -R ../liveO/* $1
	mv ./conf/liveO ./conf/$1 
	cp -R conf/* ../../conf/
	if [ $? -eq 0 ]; then	
		echo 1
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
