# java-china

Java中国是一款开源免费的论坛程序，致力于打造一个简洁优质的Java程序员论坛。

演示地址 [http://java-china.org](http://java-china.org)

有任何问题可以发 [issues](https://github.com/junicorn/java-china/issues/new)

## 特性

- 界面简洁清爽，对移动端友好
- 支持markdown语法
- 支持Emoji表情输入
- 支持Github账户登录
- 支持@用户
- 支持在线播放音乐(小彩蛋)
- 每日励志名言
- 更多功能还在开发...

## 使用

1. 建立数据库javachina，编码为utf－8,导入 `javachina.sql`
2. 导入maven工程或者使用 `war:war` 命令打包一个war包
3. 启动tomcat，访问 http://127.0.0.1:8080/java-china

## 配置 [blade.properties]

- `blade.dev`：是否是开发者模式(生产环境建议关闭)
- `app.site_url`：你的网站地址
- `app.version`：当前版本，用户清除静态资源缓存
- `app.db_cahce`：是否开启数据库缓存(生产环境建议开启)
- `qiniu`：这部分修改为你的七牛空间配置
- `email`：这部分修改为你的邮箱配置，否则无法注册
- `db`：这部分为数据库配置，修改为你连接的数据库，用户，密码

## 预览图 

![alt](http://7xsk2r.com2.z0.glb.clouddn.com/QQ20160417-0.png)

## 提醒

由于Emoji图片太多，我把它存在 [这里](https://github.com/biezhi/emojis) ，如果你需要可以在这儿下载，本项目中删除了。

## 开源协议

[Apache2](https://github.com/junicorn/java-china/blob/master/LICENSE)

## 捐赠我们

![alt](http://7xsk2r.com2.z0.glb.clouddn.com/alipay.png)


