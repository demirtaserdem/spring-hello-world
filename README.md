## Spring Boot Hello World Project
This project is basic Spring Boot Application only response String "Hello World" to get request, 
also I try to make efficient deploy process. 
 
   
contact: [demirtaserdem@gmail.com](mailto:demirtaserdem@gmail.com)

### Deploy

- Deploy With Ubuntu 18.04, 
- Certbot
- Nginx Reverse, 
- SpringBoot as a Service

##### 1. Add User

```
adduser {username}
usermod -aG sudo {username}
su - {username}
```

##### 2. Install Java 11

```
sudo apt-get update
sudo apt-get upgrade
sudo apt-get install openjdk-11-jdk
```

##### 3. Create Executable Jar File load to the server

Install the jar file some directory like this

```
/home/{username}/helloworld.erdemdemirtas.com.tr/{hw-0.0.1-SNAPSHOT.jar}
```

##### 4. Give Permisson

On jar file directory,

```
chmod 500 hw-0.0.1-SNAPSHOT.jar
```

##### 5. Create Linux Service 

Create a file /etc/systemd/system/{{yourapp}.service} like this

```
/etc/systemd/system/helloworld.erdemdemirtas.com.tr.service
```
Service file content

```
[Unit]
Description=helloworld.erdemdemirtas.com.tr
After=syslog.target

[Service]
User=demir
ExecStart=/home/demir/helloworld.erdemdemirtas.com.tr/hw-0.0.1-SNAPSHOT.jar
SuccessExitStatus=143
Restart=always
RestartSec=30

[Install]
WantedBy=multi-user.target
```

##### 6. Enable and Run Linux Service

```
sudo systemctl enable helloworld.erdemdemirtas.com.tr.service
sudo systemctl start helloworld.erdemdemirtas.com.tr
```

##### 7. Install Nginx

```
sudo apt-get update
sudo apt-get install nginx
```

##### 8. Firewall Settings

```
sudo ufw allow 'OpenSSH'
sudo ufw allow 'Nginx Full'
sudo ufw enable &&
sudo ufw status
```

##### 9. Nginx Settings

Create a file "/etc/nginx/sites-available/{yourapp}" like this

```
sudo vim /etc/nginx/sites-available/helloworld.erdemdemirtas.com.tr
```

File content

```
server {
        listen 80;
        listen [::]:80;

        server_name helloworld.erdemdemirtas.com.tr;

        location / {
             proxy_pass http://localhost:8000/;
             proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
             proxy_set_header X-Forwarded-Proto $scheme;
             proxy_set_header X-Forwarded-Port $server_port;
        }
}

```

Test Nginx File

```
sudo nginx -t
```

Slink sites-enabled

```
sudo ln -s /etc/nginx/sites-available/helloworld.erdemdemirtas.com.tr /etc/nginx/sites-enabled/helloworld.erdemdemirtas.com.tr
```
Restart Nginx

```
sudo systemctl restart nginx
```

##### 10. Install Certbot

```
sudo add-apt-repository ppa:certbot/certbot
sudo apt-get update
sudo apt-get install python-certbot-nginx
```

##### 10. Apply Certbot

Command can be use multi domain

```
sudo certbot --nginx -d {domainName} -d {www.domainName}

```

I use this command for single domain
 
```
sudo certbot --nginx -d helloworld.erdemdemirtas.com.tr 
```
Note:
	Select in options, http to https redirect option. 


#### Preferences

- [Install Nginx and Uwf Settings](https://www.digitalocean.com/community/tutorials/how-to-install-nginx-on-ubuntu-18-04)
- [Nginx Reverse Proxy](https://www.linode.com/docs/development/java/how-to-deploy-spring-boot-applications-nginx-ubuntu-16-04/#reverse-proxy)
- [Running Spring Boot app as a service](https://www.javacodemonk.com/running-spring-boot-app-as-a-service-in-unix-346ce6e9)
- [LetsEncrypt install and Apply](https://www.digitalocean.com/community/tutorials/how-to-set-up-let-s-encrypt-with-nginx-server-blocks-on-ubuntu-16-04)

