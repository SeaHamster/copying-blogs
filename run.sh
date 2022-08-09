# 自动化部署脚本 参考 https://blog.csdn.net/weixin_44078653/article/details/121554142

# 项目模块统一变量设置
#版本
VERSION=1.0

# blogs
BLOGS_CONTAINER="blogs"
BLOGS_IMAGE="blogs"
BLOGS_PORT=1555
# jenkins 自动打包完之后的项目地址
JENKINS_RELIABLE_PATH=/var/jenkins_home/workspace/copying-blogs

# 删除应用服务器旧的代码（在第二个服务器创建好/home/project/fym-platform/目录，否则报错）
#ssh -Tq ${host} <<EOF
#rm -rf /home/project/fym-platform/*
#EOF

# 拷贝新代码到应用服务器
#echo "拷贝代码开始..."
#scp -r ./* /home/project/reliable-tools
#echo "拷贝代码结束..."

# 登录到项目服务器执行命令
#ssh -Tq ${host} <<EOF
#  cd /home/project/reliable-tools
# echo "项目开始构建打包"
#  mvn clean package -Dmaven.test.skip=true -P dev
 echo "当前目录"
 cd $JENKINS_RELIABLE_PATH
 pwd
 echo "jar文件拷贝"
  mkdir -p ./docker
  cp -r ./target/*.jar Dockerfile ./docker

 echo "=============== 开始部署容器 ==============="
  echo "停止并删除旧的容器"
  docker ps -a|grep $BLOGS_CONTAINER &&  docker stop $BLOGS_CONTAINER && docker rm $BLOGS_CONTAINER || echo "not exist"

 echo "=====删除旧的镜像====="
  docker images |grep $BLOGS_IMAGE && docker rmi -f $BLOGS_IMAGE || echo  "not exist"

 echo "=====构建新的镜像====="
  docker build --build-arg JAR_FILE=$BLOGS_IMAGE-1.0.jar  -t $BLOGS_IMAGE $OBJECT_PATH/

 echo "=====运行镜像容器====="
  docker run -d --name $BLOGS_CONTAINER -v /mnt/copying-blogs/$BLOGS_IMAGE:/usr/fzz -p $BLOGS_PORT:$BLOGS_PORT $BLOGS_IMAGE
#EOF

echo "部署成功"


