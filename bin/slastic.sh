#!/bin/bash

#
# Wrapper script needs improvement!
#

BINDIR=$(dirname $0)

JAVAARGS="-Dlog4j.configuration=${BINDIR}/log4j.properties -Xms56m -Xmx1024m"
MAINCLASSNAME=org.trustsoft.slastic.plugins.starter.SLAsticStarter
CLASSPATH=$(ls lib/*.jar | tr "\n" ":")$(ls dist/*.jar | tr "\n" ":")

java ${JAVAARGS} -cp "${CLASSPATH}" ${MAINCLASSNAME} $*