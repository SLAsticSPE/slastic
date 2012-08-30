#!/bin/bash

#
# Wrapper script needs improvement!
#

BINDIR=$(dirname $0)

L4JPROPS="${BINDIR}/log4j.properties"
L4JPROPSEXAMPLE="${BINDIR}/log4j.properties.example"

JAVAARGS="-Dlog4j.configuration=${L4JPROPS} -Xms56m -Xmx2048m"
MAINCLASSNAME=kieker.tools.slastic.plugins.starter.kieker.AnalysisStarterFileSystem
CLASSPATH=$(ls lib/*.jar | tr "\n" ":")$(ls dist/*.jar | tr "\n" ":")

echo
echo "Reading log4j configuration from file ${L4JPROPS} ... "
if [ ! -f "${L4JPROPS}" ]; then
    echo "No log4j configuration. Using default. The file '${L4JPROPSEXAMPLE}' includes a template."
fi
echo

java ${JAVAARGS} -cp "${CLASSPATH}" ${MAINCLASSNAME} $*