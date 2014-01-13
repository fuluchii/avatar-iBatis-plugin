avatar-iBatis-plugin
====================

根据输入的表结构，表名，以及目标package,自动在目标package下生成如下清单：

- #####Entity（POJO）

- #####DTO（实现序列化接口）

- #####Dao

- #####sqlmap.xml(包含对应resultType)

- #####dao.xml（spring注册dao）

使用方法：

菜单栏file下选择「avatar」
填写如下清单：

![table](https://github.com/fuluchii/avatar-iBatis-plugin/blob/master/image/table.png?raw=true "")


![table](https://github.com/fuluchii/avatar-iBatis-plugin/blob/master/image/package.png?raw=true "")


![table](https://github.com/fuluchii/avatar-iBatis-plugin/blob/master/image/tableinfo.png?raw=true "")

#####生成文件如下：

![table](https://github.com/fuluchii/avatar-iBatis-plugin/blob/master/image/result.png?raw=true "")


--------------------

###TODO：

+ 生成常用statement的intention action

+ 选择各class所在的包的界面
