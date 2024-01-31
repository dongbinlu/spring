### 项目启动
通过外部tomcat 启动

###基础开发配置练习
1、开启web.xml中
2、注释掉com.only.mvc.config.OnlyStarterInitializer类，不然spring会利用SPI机制自动加载。


### web3.0规范-com.only.mvc.servletInitializer包
通过web3.0规范，可以省略web.xml的配置，可以将web.xml的配置去掉，然后打开META-INF.services下的配置文件即可。
通过web3.0规范，我们可以手动添加web三大组件(servlet,filter,listener)。


### web3.0规范-com.only.mvc.config包
通过web3.0规范，去除web.xml配置和springMVC.xml配置，实现web开发。

注意此时不需要在META-INF.services下的配置文件中配置ServletContainerInitializer接口实现类，因为Spring这一步给我们
配置了，他会去找，如果我们也配置了，就会报错，报...重复了，重名了。