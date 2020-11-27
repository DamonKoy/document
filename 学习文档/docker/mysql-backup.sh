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
sudo docker exec $mysql_prod_id mysqldump -u$mysql_user -p$mysql_password $db_chuman_qa_platform device_transfer_record devices > $backup_dir/$db_chuman_qa_platform-$backup_time.sql
# 备份mysql-dev库中的chmanAndroid相关库数据到宿主机目录下
sudo docker exec $mysql_dev_id mysqldump -u$mysql_user -p$mysql_password $db_chuman523 > $backup_dir/$db_chuman523-$backup_time.sql
sudo docker exec $mysql_dev_id mysqldump -u$mysql_user -p$mysql_password $db_chuman530 > $backup_dir/$db_chuman530-$backup_time.sql
sudo docker exec $mysql_dev_id mysqldump -u$mysql_user -p$mysql_password $db_chuman542 > $backup_dir/$db_chuman542-$backup_time.sql
sudo docker exec $mysql_dev_id mysqldump -u$mysql_user -p$mysql_password $db_chuman550 > $backup_dir/$db_chuman550-$backup_time.sql
echo "mysqldump success ok !"

# 更新导入数据到mysql-dev中
sudo docker exec -i $mysql_dev_id mysql -u$mysql_user -p$mysql_password $db_chuman_qa_platform < $backup_dir/$db_chuman_qa_platform-$backup_time.sql;
echo "update dev success ok !"
# 更新导入数据到mysql-prod中
sudo docker exec -i $mysql_prod_id mysql -u$mysql_user -p$mysql_password $db_chuman523 < $backup_dir/$db_chuman523-$backup_time.sql;
sudo docker exec -i $mysql_prod_id mysql -u$mysql_user -p$mysql_password $db_chuman530 < $backup_dir/$db_chuman530-$backup_time.sql;
sudo docker exec -i $mysql_prod_id mysql -u$mysql_user -p$mysql_password $db_chuman542 < $backup_dir/$db_chuman542-$backup_time.sql;
sudo docker exec -i $mysql_prod_id mysql -u$mysql_user -p$mysql_password $db_chuman550 < $backup_dir/$db_chuman550-$backup_time.sql;
echo "update prod success ok !"
