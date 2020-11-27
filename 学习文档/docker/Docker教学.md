# Docker

### 简介

Docker是一个平台，任何一台装有Docker的机器上你都可以建立、发布、运行你的应用程序。



### 为什么要使用？

- 最关键的一句话：使用Docker可以省事、省时、省钱。
- 用来取代虚拟机；
- Docker可以在几毫秒内为您提供一个沙盒环境。
- Docker有助于将一个复杂系统分解为一系列可组合的部分，这使您能够以一种更加离散的方式对服务进行推理。
- Docker在一台机器上起成百上千个独立的容器，有助于模拟网络，使网络建模成为一件轻而易举的事情。
- 由于你可以将所有系统打包进你的Docker容器，所以可以移动工作，甚至脱网离线工作。
- 减少调试开销
- Docker强迫你记录软件依赖，方便你其他地方布局或者安装软件。
- 实现持续交付



### Docker的核心概念

容器：一个镜像的运行实例就是一个容器。



### Docker的架构

熟悉Docker的结构是理解并使用好它的关键。Docker 使用客户端-服务器 (C/S) 架构模式，使用远程API来管理和创建Docker容器。Docker 容器通过 Docker 镜像来创建。容器与镜像的关系类似于面向对象编程中的对象与类。



### 试行

- 打开命令行终端，通过运行简单的Docker映像[hello-world来](https://hub.docker.com/_/hello-world/)测试安装是否正常 ：

```shell
$ docker run hello-world
```

- 启动Dockerized Web服务器。像`hello-world`上面的图像一样，如果在本地找不到该图像，Docker将其从Docker Hub中拉出。

```shell
$ docker run --detach --publish=80:80 --name=webserver nginx
```

- 在Web浏览器中，转到`http://localhost/`以查看nginx主页。由于我们指定了默认的HTTP端口，因此无需`:80`在URL末尾附加 。

![image-20200709140904550](/Users/damon/Dropbox/dreampix/docker/图片/image-20200709140904550.png)

- 在Web服务器运行时（使用`docker container ls`或`docker ps`）查看容器上的详细信息 ：

```shell
$ docker container ls
CONTAINER ID        IMAGE                    COMMAND                  CREATED             STATUS              PORTS                NAMES
c88fe43672b0        docker/getting-started   "/docker-entrypoint.…"   11 minutes ago      Up 10 minutes       0.0.0.0:80->80/tcp   interesting_cohen
```

- 使用以下命令停止并删除容器和图像。使用“全部”标志（`--all`或`-a`）查看停止的容器。

```shell
$ docker container ls
$ docker container stop webserver
$ docker container ls -a
$ docker container rm webserver
$ docker image ls
$ docker image rm nginx
```



### 首选项

从菜单栏中选择Docker菜单![鲸鱼菜单](https://docs.docker.com/docker-for-mac/images/whale-x.png)> **Preferences**，然后配置下面描述的运行时选项。

![Docker上下文菜单](/Users/damon/Dropbox/dreampix/docker/图片/prefs.png)

![优先](/Users/damon/Dropbox/dreampix/docker/图片/prefs-general.png)

在**常规**选项卡上，您可以配置何时启动和更新Docker：

- **登录时启动Docker Desktop**：打开会话时自动启动Docker Desktop。

- **自动检查更新**：默认情况下，Docker Desktop自动检查更新并在更新可用时通知您。您可以随时通过从Docker主菜单中选择**检查更新**来手动检查更新。

- **在Time Machine备份中包括VM**：选择此选项以备份Docker Desktop虚拟机。默认情况下禁用此选项。

- **将Docker登录名安全地存储在macOS钥匙串中**：默认情况下，Docker Desktop将您的Docker登录凭据存储在macOS钥匙串中。

- **发送使用情况统计信息**：Docker Desktop发送诊断，崩溃报告和使用情况数据。此信息可帮助Docker改善应用程序并进行故障排除。清除复选框以选择退出。

  单击“ **切换到Edge版本”**以了解有关Docker Desktop Edge版本的更多信息。



### 资源

在**资源**选项卡允许您配置CPU，内存，磁盘，代理，网络和其他资源。

在“高级”选项卡上，您可以限制可用于Docker的资源。

![高级首选项设置-高级](/Users/damon/Dropbox/dreampix/docker/图片/prefs-advanced.png)

高级设置为：

**CPU**：默认情况下，Docker Desktop设置为使用主机上可用处理器数量的一半。要提高处理能力，请将其设置为更大的数字；减少，减少数字。

**内存**：默认情况下，Docker Desktop设置为使用`2`GB运行时内存，该内存是从Mac上的总可用内存分配的。要增加RAM，请将其设置为更大的数字。要减少它，请降低数字。

**交换**：根据需要配置交换文件的大小。默认值为1 GB。

**磁盘映像大小**：指定**磁盘映像的大小**。

**磁盘映像位置**：指定Linux卷的容器和映像的存储位置。

您也可以将磁盘映像移动到其他位置。如果尝试将磁盘映像移动到已有的映像，则会出现提示询问您是否要使用现有映像或替换现有映像。

#### 文件共享

使用文件共享可允许Mac上的本地目录与Linux容器共享。这对于在运行和测试容器中的代码时在主机上的IDE中编辑源代码特别有用。默认情况下`/Users`，`/Volume`，`/private`，`/tmp`和`/var/folders`目录共享。如果您的项目不在此目录中，则必须将其添加到列表中。否则，你可能会得到`Mounts denied`或`cannot start service`在运行时错误。

文件共享设置为：

- **添加目录**：单击`+`并导航到要添加的目录。

- **Apply＆Restart**通过Docker的bind mount（`-v`）功能使目录可用于容器。

  可以共享的目录有一些限制：

  - 该目录不得在Docker内部存在。

有关更多信息，请参见：

- [osxfs文件系统共享](https://docs.docker.com/docker-for-mac/osxfs/)主题中的 [命名空间](https://docs.docker.com/docker-for-mac/osxfs/#namespaces)。
- [卷挂载要求共享文件`/Users`](https://docs.docker.com/docker-for-mac/troubleshoot/#volume-mounting-requires-file-sharing-for-any-project-directories-outside-of-users)。）[以外的任何项目目录](https://docs.docker.com/docker-for-mac/troubleshoot/#volume-mounting-requires-file-sharing-for-any-project-directories-outside-of-users)

#### 代理人

Docker Desktop从macOS中检测HTTP / HTTPS代理设置，并将其自动传播到Docker。例如，如果将代理设置设置为`http://proxy.example.com`，则Docker在提取容器时会使用此代理。

但是，您的代理设置不会传播到您启动的容器中。如果要为容器设置代理设置，则需要为其定义环境变量，就像在Linux上一样，例如：

```
$ docker run -e HTTP_PROXY=http://proxy.example.com:3128 alpine env

PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
HOSTNAME=b7edf988b2b5
TERM=xterm
HOME=/root
HTTP_PROXY=http://proxy.example.com:3128
```

有关设置用于运行容器的环境变量的更多信息，请参见[设置环境变量](https://docs.docker.com/engine/reference/commandline/run/#set-environment-variables--e---env---env-file)。

#### 网络

您可以配置Docker桌面网络以在虚拟专用网络（VPN）上工作。指定网络地址转换（NAT）前缀和子网掩码以启用Internet连接。

### Docker引擎

Docker Engine页面允许您配置Docker守护程序以确定容器的运行方式。

在框中输入JSON配置文件以配置守护程序设置。有关选项的完整列表，请参阅《 Docker Engine [dockerd命令行参考》](https://docs.docker.com/engine/reference/commandline/dockerd/)。

单击“ **应用并重新启动”**以保存您的设置并重新启动Docker Desktop。

### 命令行

在“命令行”页面上，您可以指定是否启用实验功能。

实验功能提供了对未来产品功能的早期访问。这些功能仅用于测试和反馈，因为它们可能在版本之间更改而不会发出警告，或者可以从将来的版本中完全删除。实验功能不得在生产环境中使用。Docker不提供对实验功能的支持。

> 要在Docker CLI中启用实验性功能，请编辑`config.json` 文件并将其设置`experimental`为enabled。
>
> 要从Docker Desktop菜单启用实验功能，请单击 **设置**（在macOS上为**Preferences**）> **命令行**，然后打开“ **启用实验功能”**开关。单击“ **应用并重新启动”**。

有关Docker CLI中当前实验功能的列表，请参阅[Docker CLI实验功能](https://github.com/docker/cli/blob/master/experimental/README.md)。

在Docker Desktop Edge和Stable版本上，您都可以打开和关闭实验功能。如果您关闭实验性功能，则Docker桌面将使用当前普遍可用的Docker Engine版本。

您可以在命令行上查看是否正在运行实验模式。如果 `Experimental`为`true`，则Docker将以实验模式运行，如下所示。（如果`false`，则实验模式关闭。）

```
$ docker version
Client: Docker Engine - Community
 Version:           19.03.8
 API version:       1.40
 Go version:        go1.12.17
 Git commit:        afacb8b
 Built:             Wed Mar 11 01:21:11 2020
 OS/Arch:           darwin/amd64
 Experimental:      false

Server: Docker Engine - Community
 Engine:
  Version:          19.03.8
  API version:      1.40 (minimum version 1.12)
  Go version:       go1.12.17
  Git commit:       afacb8b
  Built:            Wed Mar 11 01:29:16 2020
  OS/Arch:          linux/amd64
  Experimental:     false
 containerd:
  Version:          v1.2.13
  GitCommit:        7ad184331fa3e55e52b890ea95e65ba581ae3429
 runc:
  Version:          1.0.0-rc10
  GitCommit:        dc9208a3303feef5b3839f4323d9beb36df0a9dd
 docker-init:
  Version:          0.18.0
  GitCommit:        fec3683
```

### Kubernetes 

Docker Desktop包含一个可在Mac上运行的独立Kubernetes服务器，因此您可以测试在Kubernetes上部署Docker工作负载。

Kubernetes客户端命令`kubectl`包括在内，并配置为连接到本地Kubernetes服务器。如果您`kubectl`已经安装并指向其他环境，例如`minikube`或GKE集群，请确保更改上下文，以便`kubectl`指向`docker-desktop`：

```
$ kubectl config get-contexts
$ kubectl config use-context docker-desktop
```

如果您是`kubectl`用Homebrew或通过其他方法安装的，并且遇到冲突，请删除`/usr/local/bin/kubectl`。

- 要启用Kubernetes支持并安装作为Docker容器运行的Kubernetes独立实例，请选择**Enable Kubernetes**。要将Kubernetes设置为 [默认协调器](https://docs.docker.com/docker-for-mac/kubernetes/#override-the-default-orchestrator)，请[默认](https://docs.docker.com/docker-for-mac/kubernetes/#override-the-default-orchestrator)选择将**Docker堆栈部署到Kubernetes**。

  单击“ **应用并重新启动”**以保存设置。这将实例化将Kubernetes服务器作为容器运行所需的映像，并将`/usr/local/bin/kubectl`命令安装在 Mac上。

  ![启用Kubernetes](/Users/damon/Dropbox/dreampix/docker/图片/kube.png)

  启用并运行Kubernetes后，``Docker桌面设置''对话框的右下角将显示一个附加状态栏项目。

  Kubernetes的状态显示在Docker菜单中，上下文指向 `docker-desktop`。

  ![带有Kubernetes的Docker菜单](/Users/damon/Dropbox/dreampix/docker/图片/kube-context.png)

- 默认情况下，Kubernetes容器对诸如之类的命令是隐藏的`docker service ls`，因为不支持手动管理它们。要使其可见，请选择**显示系统容器（高级）**，然后单击**应用并重新启动**。大多数用户不需要此选项。

- 要随时禁用Kubernetes支持，请清除“ **启用Kubernetes”**复选框。Kubernetes容器将停止并删除，并且该 `/usr/local/bin/kubectl`命令也将删除。

  有关将Kubernetes集成与Docker Desktop结合使用的更多信息，请 [参阅在Kubernetes上部署](https://docs.docker.com/docker-for-mac/kubernetes/)。

### 重设

> 重置和重启选项
>
> 在Docker Desktop Mac上，**``** **疑难解答''**菜单中提供了**``重启Docker桌面''**，**``** **重置为出厂默认值''**以及其他重置选项。

有关重置选项的信息，请参阅[日志和故障排除](https://docs.docker.com/docker-for-mac/troubleshoot/)。

## 仪表板

Docker Desktop Dashboard使您可以与容器和应用程序进行交互，并直接从计算机管理应用程序的生命周期。仪表板UI会显示所有正在运行，已停止和已启动的容器及其状态。它提供了一个直观的界面来执行常见的操作，以检查和管理容器以及现有的Docker Compose应用程序。有关更多信息，请参阅[Docker Desktop Dashboard](https://docs.docker.com/desktop/dashboard/)。

## 添加TLS证书

您可以将可信证书颁发机构（CA）（用于验证注册表服务器证书）和客户端证书（用于对注册表进行身份验证）添加到Docker守护程序。

### 添加自定义CA证书（服务器端）

支持所有受信任的CA（根或中间CA）。Docker Desktop根据Mac密钥链创建所有用户信任的CA的证书捆绑包，并将其附加到Moby信任的证书。因此，如果主机上的用户信任企业SSL证书，则Docker Desktop会信任它。

要手动添加自定义的自签名证书，请先将证书添加到macOS钥匙串中，然后由Docker Desktop获取。这是一个例子：

```
$ sudo security add-trusted-cert -d -r trustRoot -k /Library/Keychains/System.keychain ca.crt
```

或者，如果您只想将证书添加到自己的本地钥匙串中（而不是对所有用户），请运行以下命令：

```
$ security add-trusted-cert -d -r trustRoot -k ~/Library/Keychains/login.keychain ca.crt
```

另请参阅[证书的目录结构](https://docs.docker.com/docker-for-mac/#directory-structures-for-certificates)。

> **注意**：您需要在对钥匙串或`~/.docker/certs.d`目录进行任何更改后重新启动Docker Desktop ，以使更改生效。

有关如何执行此操作的完整说明，请参阅博客文章“ [将自签名注册表证书添加到Docker和Mac的Docker桌面”](http://container-solutions.com/adding-self-signed-registry-certs-docker-mac/)。

### 添加客户端证书

您可以将客户证书放在 `~/.docker/certs.d/<MyRegistry>:<Port>/client.cert`和中 `~/.docker/certs.d/<MyRegistry>:<Port>/client.key`。

Docker Desktop应用程序启动时，它将`~/.docker/certs.d` Mac上的文件夹复制到`/etc/docker/certs.d`Moby（Docker Desktop `xhyve`虚拟机）上的目录。

> - 您需要在对钥匙串或`~/.docker/certs.d`目录进行任何更改后重新启动Docker Desktop ，以使更改生效。
> - 该注册表不能被列为*不安全的注册表*（请参阅[Docker](https://docs.docker.com/docker-for-mac/#docker-engine) Engine。Docker Desktop会忽略*不安全的注册表*下列出的证书，并且不发送客户端证书。`docker run`此类尝试从注册表中提取的命令会在命令行上生成错误消息，以及在注册表上。

### 证书的目录结构

如果具有此目录结构，则无需手动将CA证书添加到Mac OS系统登录名：

```
/Users/<user>/.docker/certs.d/
└── <MyRegistry>:<Port>
   ├── ca.crt
   ├── client.cert
   └── client.key
```

以下内容进一步说明和解释了使用自定义证书的配置：

```
/etc/docker/certs.d/        <-- Certificate directory
└── localhost:5000          <-- Hostname:port
   ├── client.cert          <-- Client certificate
   ├── client.key           <-- Client key
   └── ca.crt               <-- Certificate authority that signed
                                the registry certificate
```

只要CA证书也在钥匙串中，您也可以具有此目录结构。

```
/Users/<user>/.docker/certs.d/
└── <MyRegistry>:<Port>
    ├── client.cert
    └── client.key
```

要了解有关如何为注册表安装CA根证书以及如何设置客户端TLS证书进行验证的更多信息，请参阅 Docker Engine主题中的[使用证书验证存储库客户端](https://docs.docker.com/engine/security/certificates/)。

## 安装外壳完成

Docker Desktop随附脚本，用于启用`docker`和`docker-compose`命令的完成。补全脚本可以`Docker.app`在`Contents/Resources/etc/`目录中的内找到，并且可以安装在Bash和Zsh中。

### Bash

Bash [内置了对完成的支持](https://www.debian-administration.org/article/316/An_introduction_to_bash_completion_part_1)要激活Docker命令的完成，需要将这些文件复制或符号链接到您的`bash_completion.d/`目录。例如，如果您通过[Homebrew](http://brew.sh/)安装了bash ：

```
etc=/Applications/Docker.app/Contents/Resources/etc
ln -s $etc/docker.bash-completion $(brew --prefix)/etc/bash_completion.d/docker
ln -s $etc/docker-compose.bash-completion $(brew --prefix)/etc/bash_completion.d/docker-compose
```

将以下内容添加到您的`~/.bash_profile`：

```
[ -f /usr/local/etc/bash_completion ] && . /usr/local/etc/bash_completion
```

要么

```
if [ -f $(brew --prefix)/etc/bash_completion ]; then
. $(brew --prefix)/etc/bash_completion
fi
```

### Zsh

在Zsh中，[完成系统](http://zsh.sourceforge.net/Doc/Release/Completion-System.html)负责处理事务。要激活Docker命令的完成功能，需要将这些文件复制或符号链接到您的Zsh `site-functions/` 目录。例如，如果您通过[Homebrew](http://brew.sh/)安装了Zsh ：

```
etc=/Applications/Docker.app/Contents/Resources/etc
ln -s $etc/docker.zsh-completion /usr/local/share/zsh/site-functions/_docker
ln -s $etc/docker-compose.zsh-completion /usr/local/share/zsh/site-functions/_docker-compose
```

### Fish-Shell

### Fish-Shell还支持制表符[完成系统](https://fishshell.com/docs/current/#tab-completion)。要激活Docker命令的完成功能，需要将这些文件复制或符号链接到Fish-shell `completions/` 目录。

创建`completions`目录：

```
mkdir -p ~/.config/fish/completions
```

现在，从docker添加完成。

```
ln -shi /Applications/Docker.app/Contents/Resources/etc/docker.fish-completion ~/.config/fish/completions/docker.fish
ln -shi /Applications/Docker.app/Contents/Resources/etc/docker-compose.fish-completion ~/.config/fish/completions/docker-compose.fish
```

## 提供反馈并获得帮助

要获得社区的帮助，请查看当前用户主题，加入或开始讨论，登录到我们的[Docker Desktop for Mac论坛](https://forums.docker.com/c/docker-for-mac)。

要报告错误或问题，请[在GitHub上](https://github.com/docker/for-mac/issues)登录Docker Desktop [for Mac问题](https://github.com/docker/for-mac/issues)，您可以在其中查看社区报告的问题并提出新问题。有关更多详细信息，请参见 [日志和故障排除](https://docs.docker.com/docker-for-mac/troubleshoot/)。

有关提供有关文档反馈或自己进行更新的信息，请参阅“ [贡献文档”](https://docs.docker.com/opensource/)。

## Docker 

从Docker桌面菜单中选择**登录/创建Docker ID**以访问您的[Docker Hub](https://hub.docker.com/)帐户。登录后，您可以直接从Docker Desktop菜单访问Docker Hub存储库和组织。

有关更多信息，请参考以下[Docker Hub主题](https://docs.docker.com/docker-hub/)：

- [Docker Hub中的组织和团队](https://docs.docker.com/docker-hub/orgs/)
- [建物](https://docs.docker.com/docker-hub/builds/)

### 两因素认证

Docker Desktop使您可以使用两因素身份验证登录Docker Hub。当访问您的Docker Hub帐户时，两要素身份验证提供了额外的安全层。

您必须先在Docker Hub中启用双重身份验证，然后才能通过Docker Desktop登录到Docker Hub帐户。有关说明，请参阅[为Docker Hub启用两因素身份验证](https://docs.docker.com/docker-hub/2fa/)。

启用两因素身份验证后：

1. 转到Docker Desktop菜单，然后选择**登录/创建Docker ID**。
2. 输入您的Docker ID和密码，然后单击**登录**。
3. 成功登录后，Docker Desktop会提示您输入身份验证代码。输入手机的六位数代码，然后点击**验证**。

![Docker桌面2FA](/Users/damon/Dropbox/dreampix/docker/图片/desktop-mac-2fa.png)

成功通过身份验证后，您可以直接从Docker Desktop菜单访问您的组织和存储库。





### 将docker修改镜像源

> 在`/etc/docker/daemon.json`文件中添加下面参数
>
> ```shell
> {
>    "registry-mirrors" : ["https://almtd3fa.mirror.aliyuncs.com"]
> }
> ```
>
> 没有该文件则自行创建

### 最后重启docker服务

