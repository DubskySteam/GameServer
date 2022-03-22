# SE Game Backend

### Frontend
![](https://img.shields.io/badge/App-Canceled-red?style=for-the-badge&logo=Android)
![](https://img.shields.io/badge/Desktop-1.2-green?style=for-the-badge&logo=java)

### Backend
![](https://img.shields.io/badge/Website-1.0-red?style=for-the-badge&logo=Bootstrap)
![](https://img.shields.io/badge/API-1.0-green?style=for-the-badge&logo=Spring)
![](https://img.shields.io/badge/Backend-1.1-green?style=for-the-badge&logo=Java)

![](https://img.shields.io/badge/Database-MySQL-blue?style=for-the-badge&logo=mysql)
![](https://img.shields.io/badge/Build-Gradle-blue?style=for-the-badge&logo=Gradle)
![](https://img.shields.io/badge/Deployment-Docker-blue?style=for-the-badge&logo=Docker)
![](https://img.shields.io/badge/Server-Ubuntu%2020.04%20LTS-orange?style=for-the-badge&logo=Ubuntu)
___
### Tracker
![](https://img.shields.io/github/last-commit/DubskySteam/GameServer/dev?style=for-the-badge)

![](https://img.shields.io/github/issues-raw/DubskySteam/GameServer?style=for-the-badge)
![](https://img.shields.io/github/issues-pr-raw/DubskySteam/GameServer?style=for-the-badge)
___
### CI Pipeline
![](https://img.shields.io/badge/CI/CD-TeamCity-green?style=for-the-badge&logo=Teamcity)

# Installation instructions for linux

## Install the neccessary prerequisites
- Java 11+
___
## Port forwarding
- Needed: 25565(TCP)
- Optional: 25566(TCP)
___
## Create the service file
```
[Unit]
Description=SE Game Server
[Service]
User=root
WorkingDirectory=/home/GameServer/
ExecStart=/home/GameServer/script.sh

SuccessExitStatus=143
TimeoutStopSec=10
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
```

Save the file as "GameService.service" in the /etc/systemd/system/ folder.

You have to change 2 parameters in the script. 1st being the working directory. The working directory has to be the main folder where you're storing the .jar files.
The 2nd parameter is the 'ExecStart' path, which leads to the script that starts the .jar it-self.
___
## Create the start script
```
#!/bin/sh
sudo /usr/bin/java -jar GameServer.jar
```

The first path of the script has to lead to your java installation. Change the name of the jar to whatever name you will save the files as.
Save the script, so that the path of the service file matches.
___
## Enable the service
```
sudo systemctl enable GameService.service
```
___
## Run the service
```
sudo systemctl start GameService.service
```
