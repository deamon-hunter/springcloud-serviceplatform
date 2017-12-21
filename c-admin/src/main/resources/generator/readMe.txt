##自动生成映射文件方法  @Author hehaiyang

1、修改generatorConfig.xml 中的tab
<table tableName="barry_job" domainObjectName="Job">

2、在项目目录下执行生成命令
mvn mybatis-generator:generate