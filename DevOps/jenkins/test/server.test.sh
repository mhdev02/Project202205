#!/bin/bash

echo "****************************************"
echo "***** Testing ScriptCrawler/server *****"
echo "****************************************"

docker run --rm $ID/server:$BUILD_TAG npm run test