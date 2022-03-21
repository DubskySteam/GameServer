# Installation instructions for linux

## Install the neccessary prerequisites
- Java 11+

## Port forwarding
- Needed: 25565(TCP)
- Optional: 25566(TCP)

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

## Create the start script
```
#!/bin/sh
sudo /usr/bin/java -jar GameServer.jar
```

The first path of the script has to lead to your java installation. Change the name of the jar to whatever name you will save the files as.
Save the script, so that the path of the service file matches.

## Enable the service
```
sudo systemctl enable GameService.service
```

## Run the service
```
sudo systemctl start GameService.service
```
