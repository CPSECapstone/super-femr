# Software Update Script

## Overview
The current method for a software update utilizes Docker. The femr application container consists of two containers; one 
for the database and one for the project itself. When the 'update' button found in an admin's updates panel is
clicked, an update script (update.sh) is executed. This script is meant to re-build the project's container with the latest
docker image, and restart the application.

The update script uses docker-compose commands to execute an update.
## update.sh snippet:
```java
    docker-compose down
    docker-compose pull
    docker-compose up
```
The most important of the three commands is `docker-compose up`, as it re-builds and re-starts the container with the latest 
docker image. `docker-compose down` clears and deletes any existing containers, and `docker-compose pull` pulls the most recent db and femr images.


## Setup
This task requires docker to be installed. Have docker running before attempting to test any functionality.

To see which docker repository the image is pulled from, check the "image" section located the configuration file for docker-compose
(docker-compose.yml). 


## docker-compose.yml snippet:
```java
  femr:
    image: spencerklawans/femr:latest
```

While testing update functionality, I created my own docker account and connected it to a github repo that 
is a copy of the super-femr repository. This way I was able to make changes to the code and rebuild the image without interfering with 
the working project repo. 

## TODO
Figure out how to install docker and docker-compose into the container's project. The issue we could not resolve was the 
docker-compose commands not executing due to command 'not found' errors. If the script is run on its own, then such errors
 do not occur, and it works as expected. However, when the script is triggered by clicking the 'update' button from an admin's 
 updates panel, said errors occur. After investigating, we found that the issue is that neither docker nor docker-compose are installed 
 in the container's project. This was figured out by running `docker -v` and `docker-compose -v` in the app being ran by the 
 container, and seeing no versions exist. Refer to `update.sh` for more details about our attempted solution.