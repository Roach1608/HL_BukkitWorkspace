#!/bin/bash
cd "$( dirname "$0" )"
java -Xmx1024M -Xdebug -Xrunjdwp:transport=dt_socket,address=1000,server=y,suspend=n -jar craftbukkit-1.6.2-R1.0.jar -o true 