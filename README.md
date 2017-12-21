##贵煤数据服务平台体系云服务架构


## 开发环境

- jdk 1.8
- Maven 3.3.3以上
- lombok插件
	> 一款可以精减java代码、提升开发人员生产效率的辅助工具

###lombok安装步骤如下:

1. IDEA下安装方式

	打开setting→plugis→搜索lombok
	
	如图:
	
	![输入图片说明](https://git.oschina.net/uploads/images/2017/0728/104122_441a213f_798427.png "1.png")
	
	![输入图片说明](https://git.oschina.net/uploads/images/2017/0728/104144_5ac7e84b_798427.png "2.png")
	
	重启.


2. eclipse/MyEclipse下安装方式
	
	- 下载lombok.jar  [地址](http://projectlombok.org/)
	- 将 lombok.jar 复制到 myeclipse.ini / eclipse.ini 所在的文件夹目录下
	- 打开 eclipse.ini / myeclipse.ini
	- 在最后面插入以下两行并保存：
			
			-Xbootclasspath/a:lombok.jar
			-javaagent:lombok.jar

	- 重启.

##[参考](http://www.blogjava.net/fancydeepin/archive/2012/07/12/382933.html)



请将下面的配置加入hosts文件中。      
127.0.0.1 discovery         
47.95.218.37 redis config     


开发的时候先启动 discovery，api-gateway      
然后再启动自己要写的模块


项目创建时间：2017年12月1日

开发者：Ovrille

