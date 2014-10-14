#!/bin/bash
waitCounter=0
until [ "`curl --silent --show-error --connect-timeout 1 -I http://localhost:4567/login/auth | grep '200 OK'`" != "" ];
do
	waitCounter=$((waitCounter+1))
  	echo --- sleeping for 5 seconds
  	sleep 5
  	if [ $waitCounter -gt 120 ]
		then
  		echo "Timed out waiting for OpenTele"
  		exit -1
	fi
done

echo OpenTele is ready!