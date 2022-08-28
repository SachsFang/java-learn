FROM java:8
# 维护人
MAINTAINER sachs
# VOLUME 指定了临时文件目录为/tmp。其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为springboot-learn.jar并移动到/根目录
ADD springboot-learn-0.0.1-SNAPSHOT.jar /springboot-learn.jar
# 运行jar包
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/springboot-learn.jar"]
# 暴露端口
EXPOSE 8090
