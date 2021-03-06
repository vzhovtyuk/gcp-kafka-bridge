#!/usr/bin/env bash
java -Xms2g -Xmx2g -Djava.net.preferIPv4Stack=true -Dorg.jboss.resolver.warning=true -Dsun.rmi.dgc.client.gcInterval=3600000 -Dsun.rmi.dgc.server.gcInterval=3600000 -XX:+DisableExplicitGC -verbose:GC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.log -jar ../gcp-2.5/gcp-cli.jar -t 4 -m 2000m -d ../workdir/
