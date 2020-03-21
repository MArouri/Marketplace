#!/bin/bash
cd /tmp/CodeDeployExample

echo "The ApplicationStart deployment lifecycle event successfully completed." > application-start.txt

mv marketplace-0.0.1-SNAPSHOT.war ROOT.war
#java -jar ROOT.war >/dev/null 2>&1