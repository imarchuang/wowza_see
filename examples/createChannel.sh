#!/bin/sh
if [ -d /Library/WowzaStreamingEngine ]
then
	BASEDIR=/Library/WowzaStreamingEngine/examples
	"$BASEDIR/LiveDVRStreaming/install.command" all
else
	BASEDIR=/usr/local/WowzaStreamingEngine/examples
	if [ -d $BASEDIR/$1 ]
	then
		#cp -R liveO/* $1/	
		$BASEDIR/$1/install.sh $1
	else
		cd $BASEDIR
		mkdir "$1"
		cp -R ./liveO/* ./$1/
		mv ./$1/conf/liveO ./$1/conf/$1
		java ModifyApplicationXML $1 
		$BASEDIR/$1/install.sh $1 
	fi
fi
#echo "If Wowza Streaming Engine is running, you must restart it to see the installed examples."
