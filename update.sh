#!/bin/sh
# TODO: Resolve docker-compose 'not found' error by installing docker and docker-compose if needed.

# If docker is installed this will print "Update docker"
# else docker needs to be installed and attempt installation.
if [ -x "$(command -v docker)" ]; then
    echo "Update docker"
    # command
else
    echo "Install docker"
    # TODO: insert command(s) to install docker here.
fi

# If docker-compose is installed this will print "Update docker"
# else docker needs to be installed and attempt installation.
if [ -x "$(command -v docker-compose)" ]; then
    echo "Update docker-compose"
else
    echo "Install docker-compose"
    # TODO: insert command(s) to install docker-compose here.
fi

# The following docker-compose commands are what execute the update.
# Delete existing container.
docker-compose down
# Pull the most recent femr and db images.
docker-compose pull
# Build and start the container.
docker-compose up

# Check the versions of docker and docker-compose to ensure they've been installed.
docker -v
docker-compose -v
