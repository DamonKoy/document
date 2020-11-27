## 服务器安装docker

#### 连接服务器：

```
ssh test@192.168.216.210
```

#### 安装docker：

```
sudo yum install docker
```

#### 安装后设置开机启动：

```
sudo service docker start
```

#### 修改docker镜像源：

```
sudo vi /etc/docker/daemon.json
```

内容为：

```
{
   "registry-mirrors" : ["https://almtd3fa.mirror.aliyuncs.com"]
}
```

#### 重启docker：

```
sudo service docker restart
```

#### 在主机上创建目录，并添加读写权限以便jenkins应用运行时读写文件，如：

```
sudo mkdir -p /var/jenkins_node
sudo chmod 777 /var/jenkins_node
```

#### 拉取jenkins镜像：

```
sudo docker pull jenkins/jenkins:lts
```

#### 创建数据卷，用于持久化Jenkins容器的数据到宿主机

```
docker volume create jenkins_data
```

#### 运行jenkins -d为后台进行  --name为设置名称

```
sudo docker run -d --name jenkins --restart always -p 5000:5000  -p 8080:8080 -p 50000:50000 -v /home/test/jenkins:/home/test/var/jenkins_home jenkins/jenkins:lts
```

![image-20200709190541585](/Users/damon/Library/Application Support/typora-user-images/image-20200709190541585.png)

#### 查看容器进程：

```
sudo docker ps -a
```

![image-20200709190638633](/Users/damon/Library/Application Support/typora-user-images/image-20200709190638633.png)

#### 通过查看容器log进行查找jenkins初始密码：（xxxx 容器 为ID）

```
sudo docker logs XXXX
```

![image-20200709190807940](/Users/damon/Library/Application Support/typora-user-images/image-20200709190807940.png)

#### 通过内网访问服务器jenkins

http://192.168.216.210:8080/

#### 删除对应绑定网桥

```
sudo docker network disconnect --force bridge jenkins
```

#### 删除 jenkins容器，xxxx 容器 为ID

```
sudo docker rm -f XXXX
```

#### 停止容器:

```
sudo docker stop jenkins
```

#### 重启容器：

```
docker restart <container_id>
```

#### 从宿主机客户进入容器

```
sudo docker exec -it -u root jenkins /bin/bash
```

#### 进入容器后建立.ssh目录，创建密钥文件私钥id_rsa，公钥id_rsa.pub

```
mkdir ~/.ssh && cd ~/.ssh
ssh-keygen -t rsa		# 一直回车即可
```

#### 更新容器配置(修改为自动运行或停止自动运行)

```
docker update --restart always <容器ID号或者容器别名>
docker update --restart no <容器ID号或者容器别名>
```

#### 修改容器别名

```
docker rename <容器原来名> <要改为的名字>
```

#### 获取容器元挂载

```
sudo docker inspect <容器名> | grep Mounts -A 20
```

#### 修改已存在容器映射

```
sudo service docker stop
sudo docker inspect <容器名>
# 查看id的hash值
sudo vi /var/lib/docker/containers/<hash_of_the_container>/hostconfig.json
sudo vi /var/lib/docker/containers/<hash_of_the_container>/config.v2.json
# 在 hostconfig.json 里有 "PortBindings":{} 这个配置项，可以改成 "PortBindings":{"80/tcp":[{"HostIp":"","HostPort":"8080"}]} 这里 80 是容器端口， 8080 是本地端口， 然后在 config.v2.json 里面添加一个配置项 "ExposedPorts":{"80/tcp":{}} , 将这个配置项添加到 "Tty": true, 前面，我不知道添加到别的地方会不会有影响，因为经过对比正常的端口映射配置项是在这个位置，这个就是将容器内部端口暴露出来，如果不加这一句端口映射不会成功的，最后重启 docker的守护进程 service docker restart
sudo service docker restart
```

#### 查看映射端口

```
docker port CONTAINER_ID
```

#### 查看容器的IP地址

```
sudo docker inspect --format='{{.NetworkSettings.IPAddress}}' ID/NAMES
```

#### 提交镜像（提交时会导致docker进程阻塞）

```
sudo docker commit -a 'zhiquan.feng' -m '提交融合QA平台和接口自动化的jenkins项目' 87e71042d3b8 chuman/jenkins:v1
```





#### ### 安装vim

```
apt-get install vim
```





### 安装pipenv，因为网络的问题，要使用国内的镜像源来加速(全局安装，要不pipenv)

```
sudo -H pip3 install -U pipenv -i http://pypi.douban.com/simple/ --trusted-host pypi.douban.com
```

```
find / -name pipenv
```

创造软连接

```
ln -s /var/jenkins_home/python3/bin/pipenv /usr/bin/pipenv
```



#### 解决pip加载慢

```
sudo pip3 install -i https://pypi.tuna.tsinghua.edu.cn/simple -r requirements.txt
```



#### 解决allure安装问题

```
wget https://nodejs.org/dist/v12.13.0/node-v12.13.0-linux-x64.tar.xz

export NODE_HOME=/<nodejs路径>

export PATH=$PATH:$NODE_HOME/bin 

export NODE_PATH=$NODE_HOME/lib/node_modules

npm install -g allure-commandline --save-dev

```



#### 解决系统时间不同步的问题

```
// 进入docker对应容器，删除/etc/localtime
sudo docker exec -it -u root jenkins /bin/bash
rm -rf /etc/localtime
// 回到宿主机，复制本地系统时间文件夹到对应docker容器/etc/localtime中
sudo docker cp /usr/share/zoneinfo/Asia/Shanghai <CONTAINER_ID>:/etc/localtime
```







### 同步docker两个容器的mysql数据

#### 在宿主机安装crontab

```
sudo yum install vixie-cron crontabs
```

#### 查看定时任务列表

```
crontab -l
```

#### 新建定时任务命令

```
crontab -e
```

#### 宿主机执行定时任务

```
#!/bin/bash

30 7 * * * bash /home/test/mysql-backup.sh
```

#### 定时设置解释

```
*    *    *    *    *
-    -    -    -    -
|    |    |    |    |
|    |    |    |    +----- 星期中星期几 (0 - 7) (星期天 为0)
|    |    |    +---------- 月份 (1 - 12) 
|    |    +--------------- 一个月中的第几天 (1 - 31)
|    +-------------------- 小时 (0 - 23)
+------------------------- 分钟 (0 - 59)
```

#### 启动定时crond服务

```
service crond start
```

#### 查看crond服务状态

```
service crond status
```

#### 删除所有定时任务命令

```
crontab -r
```

#### 数据库备份与更新脚本 mysql-backup.sh

```
#!/bin/bash

# 设置mysql的登录用户名和密码(根据实际情况填写)
mysql_user="root"
mysql_password="1z2x3c4v5b6n7m"
mysql_prod_id="51ea579affb7"
mysql_dev_id="34aa0aed191e"

# 设置需要备份的数据库
db_chuman_qa_platform="chuman_qa_platform"
db_chuman523="chumanAndroid523"
db_chuman530="chumanAndroid530"
db_chuman542="chumanAndroid542"
db_chuman550="chumanAndroid550"

# 备份文件存放宿主机路径(根据实际情况填写)
backup_location=/home/test/mysql-backup

#如果文件夹不存在则创建
if [ ! -d $backup_location ]; 
then 
 mkdir -p $backup_location; 
fi

# 是否删除过期数据
expire_backup_delete="ON"
expire_days=30
backup_time=`date +%Y%m%d%H%M`
backup_dir=$backup_location
welcome_msg="Welcome to use MySQL backup tools!"

# 备份mysql-prod库中的chuman_qa_platform表数据到宿主机目录下
sudo docker exec $mysql_prod_id mysqldump -u$mysql_user -p$mysql_password $db_chuman_qa_platform device_transfer_record devices > $backup_dir/$database-$backup_time.sql
# 备份mysql-dev库中的chmanAndroid相关库数据到宿主机目录下
sudo docker exec $mysql_dev_id mysqldump -u$mysql_user -p$mysql_password $db_chuman523 > $backup_dir/$db_chuman523-$backup_time.sql
sudo docker exec $mysql_dev_id mysqldump -u$mysql_user -p$mysql_password $db_chuman530 > $backup_dir/$db_chuman530-$backup_time.sql
sudo docker exec $mysql_dev_id mysqldump -u$mysql_user -p$mysql_password $db_chuman542 > $backup_dir/$db_chuman542-$backup_time.sql
sudo docker exec $mysql_dev_id mysqldump -u$mysql_user -p$mysql_password $db_chuman550 > $backup_dir/$db_chuman550-$backup_time.sql
echo "mysqldump success ok !"

# 更新导入数据到mysql-dev中
sudo docker exec -i $mysql_dev_id mysql -u$mysql_user -p$mysql_password $db_chuman_qa_platform < $backup_dir/$database-$backup_time.sql;
echo "update dev success ok !"
# 更新导入数据到mysql-prod中
sudo docker exec -i $mysql_prod_id mysql -u$mysql_user -p$mysql_password $db_chuman523 < $backup_dir/$db_chuman523-$backup_time.sql;
sudo docker exec -i $mysql_prod_id mysql -u$mysql_user -p$mysql_password $db_chuman530 < $backup_dir/$db_chuman530-$backup_time.sql;
sudo docker exec -i $mysql_prod_id mysql -u$mysql_user -p$mysql_password $db_chuman542 < $backup_dir/$db_chuman542-$backup_time.sql;
sudo docker exec -i $mysql_prod_id mysql -u$mysql_user -p$mysql_password $db_chuman550 < $backup_dir/$db_chuman550-$backup_time.sql;
echo "update prod success ok !"

```

