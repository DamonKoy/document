

具体报错：

```shell
elasticsearch.exceptions.ConnectionError: ConnectionError(<urllib3.connection.HTTPConnection object at 0x1119e0c40>: Failed to establish a new connection: [Errno 8] nodename nor servname provided, or not known) caused by: NewConnectionError(<urllib3.connection.HTTPConnection object at 0x1119e0c40>: Failed to establish a new connection: [Errno 8] nodename nor servname provided, or not known)
```

第一次使用这个模块，以为像下面导入实例化后就直接可以使用了

```python
from elasticsearch import Elasticsearch
es = Elasticsearch([{'host' :'127.0.0.1','port' :'9200'}])
```

然后但凡我进行任何创建索引，查询，修改之类的操作，都会报错如上，当然字面意思说链接被拒绝了，我以为是本地端口链接被拒绝了。

于是想到[elasticsearch](https://so.csdn.net/so/search?q=elasticsearch&spm=1001.2101.3001.7020)本身是个服务器，我客户端通，会不会是少了服务器接受处理数据发送给客户端。当然一开始我没想到，看到网上很多人直接导入就用了，以为不用配置服务器。找了半天也没合理解释，也可能是我自己没相通
于是去官网下载并配置服务器：



https://www.elastic.co/cn/elasticsearch/

下载安装



启动

```shell
1 cd /Users/admin/Downloads/elasticsearch/bin
2 vim ./elasticsearch.in.sh
3 JAVA_OPTS="$JAVA_OPTS -Des.insecure.allow.root=true"
4 ./elasticsearch
```



验证服务器启动

```
在浏览器输入url: htts://127.0.0.1:9200 或 https://127.0.0.1:9200
```

启动日志：

![image-20231205182121156](https://gitee.com/Damon_Koy/images/raw/master/md/es启动日志.png)

如果访问显示无法正常工作，是因为开启了ssl认证
在 **ES/config/elasticsearch.yml** 文件中把 `xpack.security.http.ssl:enabled` 设置成 `false` 即可



**elasticsearch 账号密码**

找了一轮没看到有账号密码，干脆就设置免密登录就好。

找到 `elasticsearch.yml` 文件， 把 `xpack.security.enabled` 属性设置为 `false` 即可。



#### 设置内存大小

ES 的内存是自己调节的。在 `config/jvm.options` 文件中直接设置就好(追加):

```shell
-Xms512m
-Xmx2048m
```

