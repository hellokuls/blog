/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : springblog

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 26/07/2020 19:13:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '博客标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '博客内容',
  `first_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '博客封面',
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '原创转载',
  `description` varchar(255) DEFAULT NULL COMMENT '摘要',
  `view` int(11) DEFAULT NULL COMMENT '阅读数',
  `appreciation` bit(1) DEFAULT NULL COMMENT '开启赞赏',
  `share_statement` bit(1) DEFAULT NULL COMMENT '转载声明',
  `commentabled` bit(1) DEFAULT NULL COMMENT '开启评论',
  `published` bit(1) DEFAULT NULL COMMENT '是否发布',
  `recommend` bit(1) DEFAULT NULL COMMENT '是否推荐',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `type_id` int(11) DEFAULT NULL COMMENT '类型',
  `user_id` int(11) DEFAULT NULL COMMENT '作者用户',
  `likes` bigint(20) DEFAULT NULL COMMENT '喜欢数',
  PRIMARY KEY (`id`),
  KEY `2` (`user_id`),
  KEY `1` (`type_id`),
  CONSTRAINT `1` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `2` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
BEGIN;
INSERT INTO `t_blog` VALUES (5, '用Python手写十大经典排序算法', '# 213123', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3842079103,2765975913&fm=26&gp=0.jpg', '原创', '这是博客描述', 160, b'0', b'0', b'0', b'1', b'1', '2020-05-11 09:28:16', '2020-05-18 07:44:24', 7, 1, 274);
INSERT INTO `t_blog` VALUES (7, '为了看懂滴滴拼车，我复习了初中数学', '# 12312312312', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3842079103,2765975913&fm=26&gp=0.jpg', '原创', '这里是博客描述', 147, b'1', b'1', b'1', b'1', b'1', '2020-05-11 09:27:05', '2020-05-17 08:14:30', 3, 1, 33);
INSERT INTO `t_blog` VALUES (8, '下载源', '## 前言\r\n\r\n有很多朋友刚刚学Python的时候，会来问为什么pip下载东西这么慢啊？pycharm里面下载库也是非常的慢。这其实是个常识性的问题，我们下载的慢是因为Python使用pip方法安装第三方包时，需要从 `https://pypi.org/` 资源库中下载。这个网站是国外的服务器，访问自然就很慢，但是国内有很多的镜像站，所谓镜像站就是内容一样，只不过服务器在国内，访问速度自然而然就很快了。下面给大家普及一下如何修改pip的下载源以及pycharm的下载源。\r\n\r\n\r\n## pycharm修改下载源\r\n\r\n有一些朋友是直接在pycharm的环境下安装库的，这样也有一定的好处就是不会导致电脑的环境比较混乱。那如何修改pycharm的下载源呢?\r\n\r\n1. 进入设置\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/ce62b156-486a-49d7-90ea-7471357a69ac.png)\r\n\r\n\r\n2. 到达上图界面,随意双击任意一个库\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/cc89861b-4cd8-438f-b226-7661d469962b.png)\r\n\r\n3. 到达下面这个界面后，点击Manage Repositories\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/5badb7c0-1f7d-47ff-8bd7-9a9053e97017.png)\r\n\r\n4. 添加下载源地址\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/9cea26c8-15c4-4072-b5e7-b5a4879652eb.png)\r\n\r\n这样就可以将pycharm的下载源更换为国内下载源。\r\n\r\n## 系统修改下载源\r\n\r\n在国内，比较火的就是下面这几个下载源：\r\n\r\n```\r\n阿里云 http://mirrors.aliyun.com/pypi/simple/ \r\n中国科技大学 https://pypi.mirrors.ustc.edu.cn/simple/ \r\n豆瓣 http://pypi.douban.com/simple/ \r\n清华大学 https://pypi.tuna.tsinghua.edu.cn/simple/ \r\n中国科学技术大学 http://pypi.mirrors.ustc.edu.cn/simple/\r\n```\r\n\r\n我使用的是清华大学的下载源，因为速度很快，而且每五分钟就会更新一次。所以非常的可靠。\r\n\r\n这里我会介绍mac/Linux/windows系统的修改方式(mac和Linux是一样的)\r\n\r\n### 1.mac/Linux\r\n\r\n#### 1.1 临时使用\r\n\r\n```python\r\npip install -i https://pypi.tuna.tsinghua.edu.cn/simple some-package\r\n```\r\n\r\n其中some-package就是你所需要安装的第三方库。上面的网址一定要正确，一字不漏！\r\n\r\n#### 1.2 永久修改\r\n\r\n修改 `~/.pip/pip.conf`，根据个人使用经验，有些朋友没有这个文件，甚至没有这个目录，这个时候我们先创建这个目录然后创建这个文件进行编写：\r\n\r\n```linux\r\n1.创建目录\r\nmkdir ~/.pip\r\n2.创建并编辑文件\r\nvim ~/.pip/pip.conf\r\n```\r\n\r\n将文件中的内容填写或者修改为下面的内容：\r\n\r\n```\r\n[global]\r\nindex-url = https://pypi.tuna.tsinghua.edu.cn/simple\r\n```\r\n\r\nindex-url指的是你需要更换的下载源，上面已经提供了很多的下载源了，可以直接填进去。\r\n\r\n填写后按ESC，输入wq! ,保存退出。\r\n\r\n此时我们去使用pip下载东西，使用的是清华源了。\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/a6a21517-66e7-491b-83ec-d72fee2901e0.png)\r\n\r\n\r\n### 2.windows\r\n\r\n#### 2.1 临时使用\r\n\r\nWindows的临时使用和mac是同样的方法。\r\n\r\n```python\r\npip install -i https://pypi.tuna.tsinghua.edu.cn/simple some-package\r\n```\r\n\r\n其中some-package就是你所需要安装的第三方库。上面的网址一定要正确，一字不漏！\r\n\r\n#### 2.2 永久使用\r\n\r\n1. 直接在user目录中创建一个pip目录，如：C:\\Users\\xxx\\pip\r\n\r\n\r\n2. 新建文件pip.ini\r\n\r\n\r\n3. 输入以下内容：\r\n\r\n```\r\n[global]\r\nindex-url = https://pypi.tuna.tsinghua.edu.cn/simple\r\n```\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/dab0a9ec-494f-4a82-9671-0d472051f26c.png)\r\n\r\n这样就OK了！\r\n\r\n\r\n\r\n\r\n\r\n', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3842079103,2765975913&fm=26&gp=0.jpg', '原创', '有很多朋友刚刚学Python的时候，会来问为什么pip下载东西这么慢啊？pycharm里面下载库也是非常的慢。这其实是个常识性的问题，我们下载的慢是因为Python使用pip方法安装第三方包时，需要从 `https://pypi.org/` 资源库中下载。这个网', 267, b'1', b'0', b'1', b'1', b'1', '2020-05-10 12:54:41', '2020-05-18 07:44:19', 3, 1, 51);
INSERT INTO `t_blog` VALUES (11, '130 个相见恨晚的超实用网站，一次性分享给你！', '### 123123123', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3842079103,2765975913&fm=26&gp=0.jpg', '原创', '123123123 12dasdasdasdsa', 15, b'0', b'1', b'0', b'1', b'0', '2020-05-11 14:22:40', '2020-05-11 14:22:40', 7, 1, 333);
INSERT INTO `t_blog` VALUES (12, 'Django整合markdown', '## 前言\r\n\r\n最近自己在开发一个blog，因为比较喜欢用Markdown来写文章，而且目前很多平台都支持Markdown的语法，所以想给blog装个Markdown的编辑器。于是，就搜了一下，发现了django-mdeditor这个库，给大家分享一下。\r\n\r\n## 简单介绍\r\n\r\n**Github地址:** https://github.com/pylixm/django-mdeditor \r\n\r\nDjango-mdeditor 是基于 Editor.md 的一个 django Markdown 文本编辑插件应用。\r\n\r\nDjango-mdeditor 的灵感参考自伟大的项目 django-ckeditor（https://github.com/django-ckeditor/django-ckeditor）\r\n\r\n## 后端编辑器使用\r\n\r\n### 1.安装相关库\r\n\r\n```\r\npip install django-mdeditor  # 用于后台编辑\r\npip install markdown # 用于前端显示\r\n```\r\n\r\n首先大家先安装这两个库，django-mdeditor库就是用在我们管理后台的md编辑器，markdown则是在前端显示时使用。\r\n\r\n### 2.配置\r\n\r\n安装完两个库后，我们需要进行相关的配置。\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/95c8ddff-3cee-4a31-bfcc-530069d2375b.png)\r\n\r\n新增setting配置：\r\n\r\n```\r\nINSTALLED_APPS = [\r\n    \'django.contrib.admin\',\r\n    \'django.contrib.auth\',\r\n    \'django.contrib.contenttypes\',\r\n    \'django.contrib.sessions\',\r\n    \'django.contrib.messages\',\r\n    \'django.contrib.staticfiles\',\r\n    \'blog\',\r\n    \'mdeditor\',\r\n]\r\n```\r\n\r\n除了配置上面的信息，还需要配置资源文件夹：\r\n\r\n```\r\nMEDIA_ROOT = os.path.join(BASE_DIR, \'media\')  \r\n\r\nMEDIA_URL = \'/media/\'   #你上传的文件和图片会默认存在/media/editor下\r\n```\r\n\r\n以为就完了？不，你还需要去url进行配置：\r\n\r\n\r\n![点击放大](https://imgkr.cn-bj.ufileos.com/d517f0c2-d008-4f38-8086-c1bf69883476.png)\r\n\r\n大家把我打红框的代码弄上去就ok了\r\n\r\n这时，我们就大概配置完成了。\r\n\r\n### 3.使用Markdown\r\n\r\n\r\n\r\n![点击放大](https://imgkr.cn-bj.ufileos.com/1925edaf-64db-4217-8057-6570db74faae.png)\r\n\r\n\r\n此时只需要在model中填写相应的属性，即可调用该编辑器。\r\n\r\n当然，在进入管理页面之前，你需要在admin中进行注册\r\n\r\n```python\r\nadmin.site.register(Acticle) # Acticle 是我文章的model名\r\n```\r\n\r\n打开后台之后，我们就会发现Markdown编辑器出现了：\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/c9821626-0d9f-4838-b7ae-514d16c6ed18.png)\r\n\r\n我们在这里插入的图片或者上传的文件都会在media文件夹中，这个文件夹在上面配置中提到，必须要有！！！\r\n\r\n\r\n## 前端使用\r\n\r\n我们使用了Markdown编辑器编写的文章在前端显示时，必须得将Markdown语法“翻译”成富文本的形式，所以这里我们需要使用到markdown这个库。\r\n\r\n### 视图函数\r\n\r\n```\r\npip install markdown\r\n```\r\n\r\n我们书写的博客文章内容存在Post的body属性里，回到我们的详情页视图函数，对post的body 的值做一下渲染，把Markdown文本转为HTML文本再传递给模板：\r\n\r\n```python\r\nimport markdown\r\nfrom django.shortcuts import render, get_object_or_404\r\nfrom .models import Post\r\n \r\ndef post_article(request, pk):\r\n    post = get_object_or_404(Post, pk=pk)\r\n    # 记得在顶部引入 markdown 模块\r\n    post.body = markdown.markdown(post.body,\r\n                                  extensions=[\r\n                                     \'markdown.extensions.extra\',\r\n                                     \'markdown.extensions.codehilite\',\r\n                                     \'markdown.extensions.toc\',\r\n                                  ])\r\n    return render(request, \'blog/detail.html\', context={\'post\': post})\r\n```\r\n\r\n可能有些朋友不懂get_object_or_404方法，给大家简单介绍一下get_object_or_404：我们原来调用django 的get方法(model.object.get())，如果查询的对象不存在的话，会抛出一个DoesNotExist的异常，现在我们调用django get_object_or_404方法，它会默认的调用django 的get方法，如果查询的对象不存在的话，会抛出一个Http404的异常，我感觉这样对用户比较友好，如果用户查询某个产品不存在的话，我们就显示404的页面给用户，比直接显示异常好。\r\n\r\n**markdown.extensions.extra：** 用于标题、表格、引用这些基本转换\r\n\r\n**markdown.extensions.codehilite：** 用于语法高亮\r\n\r\n**markdown.extensions.toc：** 用于生成目录\r\n\r\n### 替换网页模板\r\n\r\n在模板中找到展示博客文章的地方加上如下代码，注意这里需要使用safe过滤器。\r\n\r\n```\r\n<div>\r\n    {{ post.body|safe }}\r\n</div>\r\n```\r\n\r\n通过这样就能够显示md语法的文章了\r\n\r\n## 总结\r\n\r\n在开发过程中遇到的一个小需求就分享给大家，当然我介绍的只是mdeditor的一部分知识，mdeditor还有一些相关的配置，这里我就没给大家说了，大家可以去GitHub上自行看他们的官方文档，顺便也可以去star一下！\r\n\r\n- 第一点\r\n- 第二点\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n', 'https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2857909136,365461141&fm=26&gp=0.jpg', '转载', '最近自己在开发一个blog，因为比较喜欢用Markdown来写文章，而且目前很多平台都支持Markdown的语法，所以想给blog装个Markdown的编辑器。于是，就搜了一下，发现了django-mdeditor这个库，给大家分享一下。', 190, b'1', b'1', b'1', b'1', b'1', '2020-05-12 17:42:17', '2020-05-12 17:42:17', 3, 1, 5);
INSERT INTO `t_blog` VALUES (13, '百度文库分析', '## 前言\r\n\r\n这几天在公众号Python交流群里有人问道XX文库怎么免费下载，心想着我也没研究过这个，不知道难不难，于是自己去抓了一下包。其实难度不是很大，只是包的数据比较多不太好找，这里我只提供思路，不会提供代码，大家懂就行，当然如果你还是写不出，可以私聊我进Python交流群。\r\n\r\n\r\n## XX文库分析\r\n\r\n### PPT\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/8cbec22b-9d4c-4d75-9c00-7f246e688335.png)\r\n\r\n首先我们来分析一下PPT的下载，其实对于这种数据一般都是采用抓包的思路来进行的。\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/bc6dade1-87ab-448f-82cc-52dc528a9aee.png)\r\n\r\n我一个一个找了一下，发现上面的数据包中有我们想要的数据，我们看下拿到了什么数据：\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/1c30502c-5149-452a-8f4c-713d614c65ef.png)\r\n\r\n我们看到了有一个参数为20。恰好，我们想要下载的PPT也是20页，于是访问一下zoom中的链接。\r\n\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/23b2b005-6c43-431f-a4e9-5dbf2fe8f3e6.png)\r\n\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/aa5e8a8b-8a27-47d1-bba4-b9c7bd89b2b9.png)\r\n\r\n正是我们要的，所以后面你懂得，就交给帅气牛逼的你了！\r\n\r\n### word\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/29be239d-21a9-4aeb-82e2-9b3e74ea59f0.png)\r\n\r\n随意找了个word文档，同样的思路，我们还是来抓包，当然这个包跟我们之前的肯定不同。\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/4483150d-8c35-4e99-a8ce-0a0e8867f1b8.png)\r\n\r\n同样查看这个包，看看这个包给了我们什么数据：\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/6d79a6b6-d7ca-4e0d-b821-a0050c17e6f6.png)\r\n\r\n可以看到这就是word当中的文字信息，当然有些小伙伴说为啥你这不只是拿到文字信息么？确实这样只能拿到文字信息，但是在这串数据里面有相应的位置坐标信息。帅气牛逼的你们可以自己取折腾折腾！\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/4c1b0418-23ca-4c60-b563-b1ce89985913.png)\r\n\r\n\r\n### txt\r\n\r\n关于txt就有点小麻烦了，首先我们需要获取该txt文档的id名称\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/1aab5794-6cae-4eb6-9713-6d44a5d9e8d4.png)\r\n\r\n我们可以直接从链接当中获取。\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/3653e193-633e-4b41-bc7b-e8cba3bd503c.png)\r\n\r\n然后我们直接进行url的搜索\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/99712f38-0397-4221-8771-bd5c110b2236.png)\r\n\r\n搜索关键词，975114ef19e8b8f67c1cb9d9是我的文件id，自己修改\r\n\r\n```\r\nwkretype.bdimg.com/retype/text/975114ef19e8b8f67c1cb9d9\r\n```\r\n\r\n我们双击筛选出来的包：\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/2a6286af-1044-4162-bede-496c5e180e32.png)\r\n\r\n你可能一脸懵逼，但是我们去转换一下字符编码：\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/e47ab1d7-5142-473c-89be-77cab79a6418.png)\r\n\r\n可以看到正是我们需要的数据。后面的大家就自己解决啦！', 'https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture', '原创', '这几天在公众号Python交流群里有人问道XX文库怎么免费下载，心想着我也没研究过这个，不知道难不难，于是自己去抓了一下包。', 7, b'0', b'0', b'0', b'1', b'1', '2020-05-16 19:15:42', '2020-05-16 19:15:42', 7, 1, 1);
INSERT INTO `t_blog` VALUES (14, 'GitHub上两个适合入门的Django项目', '## 前言1\r\n\r\n最近GitHub推荐了几个很不错的项目给我，其中有两个适合Django的入门，我也仔细的看过源码并且实际的跑了起来。整体的逻辑比较简单，比较适合刚入门的朋友借鉴学习。\r\n\r\n## 项目一\r\n\r\n**项目地址：**\r\n\r\n> https://github.com/myminwang/myblog\r\n\r\n**环境：**\r\n\r\n* python 3.x\r\n* Django 2.0.x\r\n* Mysql\r\n\r\n**特点：**\r\n\r\n* 博客文章 markdown 渲染，代码高亮\r\n* 第三方社会化评论系统支持(畅言)\r\n* 三种皮肤自由切换\r\n* 全局搜索\r\n* 阅读排行榜/最新评论\r\n* 多目标源博文分享\r\n* 博文归档\r\n* 友情链接\r\n* 分享、打赏功能\r\n\r\n**页面：**\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/0161aba1-9d8e-4475-a0a9-734a675c7706.png)\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/9ac95767-3383-44d0-9228-156cd941ec2e.png)\r\n\r\n这个项目后台使用的是xadmin，关于xadmin的相关知识，大家可以去搜索相关的文档。\r\n\r\n整个项目的思路比较简单，**需要交流的朋友可以进文章末尾的群进行交流。**\r\n\r\n## 项目二\r\n\r\n>https://github.com/newpanjing/myblog\r\n\r\n这个项目大致和上面的项目相同，大家也可以看这个来进行学习。\r\n\r\n**页面：**\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/170ac9d5-7848-4600-997c-6b044b8c07ba.png)\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/7b03ac8f-53c9-4182-9495-1e5c5dcbd7f6.png)\r\n\r\n最近GitHub的速度比较慢，大家耐心下载吧~\r\n\r\n## Python web 纯技术交流群\r\n\r\n\r\n加我微信，回复“web”即可入群\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n', 'https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture', '原创', '最近GitHub推荐了几个很不错的项目给我，其中有两个适合Django的入门，我也仔细的看过源码并且实际的跑了起来。整体的逻辑比较简单，比较适合刚入门的朋友借鉴学习。', 36, b'1', b'1', b'1', b'1', b'1', '2020-05-16 19:18:53', '2020-05-17 08:14:17', 7, 1, 1);
INSERT INTO `t_blog` VALUES (15, '到底如何把Java学到精通？？？', '# 你好', 'https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture', '原创', '你猜猜，你猜猜你猜猜你猜猜你猜猜你猜猜你猜猜你猜猜你猜猜你猜猜', 760, b'0', b'0', b'0', b'1', b'0', '2020-05-19 08:47:31', '2020-06-29 07:03:48', 7, 1, 17);
INSERT INTO `t_blog` VALUES (18, '分享| 我收藏夹里贼好用的网站', '## 前言\r\n\r\n各位小伙伴大家好，今天又来分享一波自己收藏的超好用的网站，之前写过两期实用网站分享，感觉反馈都不错。今天抽空再来跟大家分享一波，当然如果你有一些好的网站，欢迎给我私信分享！如果真的不错，会有小红包~~~\r\n\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/b6443eb6-897a-4cd1-8409-bb5cfc222632.png)\r\n\r\n\r\n## 贼好用的网站\r\n\r\n### 1. processon\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/2970c006-7a7c-4cf9-abcc-6f24a904caa5.png)\r\n\r\nwww.processon.com\r\n\r\n这个网站是真心不错，尤其是对于程序员群体来说，不仅仅可以画UML，原型图，网络拓扑图等等，还能看到网上各路大神分享出来的思维导图。简直就是学习利器！非常强烈推荐！！！\r\n\r\n\r\n### 2. cloudconvert\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/48ee08bf-ecba-43cb-9281-1f9faa528480.png)\r\n\r\ncloudconvert.com\r\n\r\n有时候真的会被格式问题搞的蛋疼，这个网站还是挺良心的，支持非常多的格式转换。相信这个能够给你带来很多的便利！\r\n\r\n### 3. easyicon\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/61cd1981-a742-434d-bad1-3af63005dc99.png)\r\n\r\nwww.easyicon.net\r\n\r\n图标网，写前端美工必备网站，无数个图标随意选，各种格式大小随便挑。记得小时候写app时候经常用(手动狗头)\r\n\r\n\r\n### 4. mdnice\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/df4867d5-6fbf-4392-8a14-d55467e7f33f.png)\r\n\r\nmdnice.com\r\n\r\n这个确实挺多人问的，特别是有想写公众号文章的朋友。这是一个md美化编辑器，可以美化公众号、知乎等平台的md文章。你现在看的这篇文章就是通过它来编辑的，在此也非常感谢mdnice团队。\r\n\r\n\r\n\r\n### 5. macbl\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/5a1dfa64-a79d-4dc8-9680-4cfdeb9fd464.png)\r\n\r\nwww.macbl.com\r\n\r\n这应该是我第二次推荐这个网站了。非常非常良心的Mac软件、游戏等免费下载网。注意是免费！没有登录，关注等乱七八糟的功能，直接开下。但是有一个不友好的地方就是里面的下载都是通过网盘的形式(还好我有超级会员)。\r\n\r\n\r\n\r\n## 结尾\r\n\r\n以上就是我这次的分享，希望能够给你一些帮助，也非常感谢这些良心站长的网站。\r\n\r\n\r\n![](https://imgkr.cn-bj.ufileos.com/9792fa9c-7f17-4c0d-a3c2-8115700921f9.png)\r\n\r\n\r\n如果大家还想看到这类实用工具类网站的推荐，麻烦`点点在看`！！！如果你也有贼好用的网站推荐给我，还麻烦加我微信(下面有二维码)，如果被分享出来，会有小红包！\r\n\r\n', 'https://dss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3984473917,238095211&fm=26&gp=0.jpg', '原创', '分享一些贼好用的网站，欢迎进来看看！', 160, b'1', b'1', b'1', b'1', b'1', '2020-06-29 19:25:31', '2020-07-11 17:00:08', 3, 1, 8);
INSERT INTO `t_blog` VALUES (19, '测试', '测试测试测试测试测试测试测试测试测试测试', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3842079103,2765975913&fm=26&gp=0.jpg', '原创', '测试测试测试测试测试测试测试测试测试测试测试测试测试测试', 1, b'0', b'0', b'0', b'0', b'0', '2020-07-07 06:53:21', '2020-07-08 07:45:08', 3, 1, 1);
INSERT INTO `t_blog` VALUES (20, '测试用的', '123123123121233', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3842079103,2765975913&fm=26&gp=0.jpg', '原创', '123123123123123', 1, b'1', b'1', b'1', b'0', b'1', '2020-07-07 07:04:52', '2020-07-08 07:44:57', 15, 1, 1);
INSERT INTO `t_blog` VALUES (21, '测试图片功能', '![](/img/upload/blog/nathan-de-fortunato-Kfjp8qa8_pM-unsplash.jpg)', 'https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3842079103,2765975913&fm=26&gp=0.jpg', '原创', '策是一打四到手机哦雷迪克你', 17, b'1', b'1', b'1', b'1', b'1', '2020-07-11 20:52:46', '2020-07-11 22:04:10', 17, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for t_blog_tags
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tags`;
CREATE TABLE `t_blog_tags` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `blog_id` bigint(20) NOT NULL COMMENT '博客id',
  `tags_id` int(11) NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `13` (`tags_id`),
  CONSTRAINT `12` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `13` FOREIGN KEY (`tags_id`) REFERENCES `t_tag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog_tags
-- ----------------------------
BEGIN;
INSERT INTO `t_blog_tags` VALUES (93, 11, 2);
INSERT INTO `t_blog_tags` VALUES (94, 11, 3);
INSERT INTO `t_blog_tags` VALUES (95, 11, 4);
INSERT INTO `t_blog_tags` VALUES (111, 12, 1);
INSERT INTO `t_blog_tags` VALUES (112, 12, 4);
INSERT INTO `t_blog_tags` VALUES (113, 12, 8);
INSERT INTO `t_blog_tags` VALUES (114, 13, 1);
INSERT INTO `t_blog_tags` VALUES (115, 13, 4);
INSERT INTO `t_blog_tags` VALUES (116, 13, 8);
INSERT INTO `t_blog_tags` VALUES (138, 14, 1);
INSERT INTO `t_blog_tags` VALUES (139, 14, 4);
INSERT INTO `t_blog_tags` VALUES (140, 14, 8);
INSERT INTO `t_blog_tags` VALUES (141, 7, 2);
INSERT INTO `t_blog_tags` VALUES (142, 7, 3);
INSERT INTO `t_blog_tags` VALUES (143, 7, 8);
INSERT INTO `t_blog_tags` VALUES (144, 8, 1);
INSERT INTO `t_blog_tags` VALUES (145, 8, 2);
INSERT INTO `t_blog_tags` VALUES (146, 8, 5);
INSERT INTO `t_blog_tags` VALUES (147, 5, 2);
INSERT INTO `t_blog_tags` VALUES (148, 5, 3);
INSERT INTO `t_blog_tags` VALUES (167, 15, 1);
INSERT INTO `t_blog_tags` VALUES (168, 15, 3);
INSERT INTO `t_blog_tags` VALUES (169, 15, 5);
INSERT INTO `t_blog_tags` VALUES (176, 20, 9);
INSERT INTO `t_blog_tags` VALUES (177, 20, 10);
INSERT INTO `t_blog_tags` VALUES (178, 20, 11);
INSERT INTO `t_blog_tags` VALUES (179, 19, 13);
INSERT INTO `t_blog_tags` VALUES (186, 18, 4);
INSERT INTO `t_blog_tags` VALUES (197, 21, 10);
COMMIT;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '发表日期',
  `blog_id` bigint(20) DEFAULT NULL COMMENT '关联博客id',
  `parent_comment_id` bigint(20) DEFAULT NULL COMMENT '父评论',
  `admin_comment` bit(1) DEFAULT NULL COMMENT '是否为管理员',
  PRIMARY KEY (`id`),
  KEY `3` (`blog_id`),
  CONSTRAINT `3` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
BEGIN;
INSERT INTO `t_comment` VALUES (39, '小红', '1839938674@qq.com', '这是小红的评论', '/images/1.jpeg', '2020-05-15 15:26:10', 12, NULL, NULL);
INSERT INTO `t_comment` VALUES (40, '小明', '1839938674@qq.com', '小明回复小红的评论', '/images/1.jpeg', '2020-05-15 15:26:24', 12, 39, NULL);
INSERT INTO `t_comment` VALUES (41, '小蓝', '1839938674@qq.com', '这是小蓝回复小明的评论', '/images/1.jpeg', '2020-05-15 15:27:16', 12, 40, NULL);
INSERT INTO `t_comment` VALUES (42, '小黑', '1839938674@qq.com', '新的一个人评论', '/images/1.jpeg', '2020-05-15 15:32:30', 12, 41, NULL);
INSERT INTO `t_comment` VALUES (43, '一号', '1839938674@qq.com', '一号评论', '/images/1.jpeg', '2020-05-15 15:36:08', 12, NULL, NULL);
INSERT INTO `t_comment` VALUES (44, '二号', '1839938674@qq.com', '二号评论', '/images/1.jpeg', '2020-05-15 15:36:19', 12, 43, NULL);
INSERT INTO `t_comment` VALUES (46, '三号', '123@qq.com', '我是三号评论', '/images/1.jpeg', '2020-05-15 17:25:37', 12, 44, NULL);
INSERT INTO `t_comment` VALUES (47, '四号', '123@qq.com', '我是四号评论', '/images/1.jpeg', '2020-05-15 17:25:58', 12, 43, NULL);
INSERT INTO `t_comment` VALUES (48, 'kuls', '1839938674@qq.com', '这是博主的评论', 'http://q1.qlogo.cn/g?b=qq&nk=1839938674&s=100', '2020-05-15 17:59:24', 12, NULL, b'1');
INSERT INTO `t_comment` VALUES (50, 'kuls', '1839938674@qq.com', '这也是我的评论', 'http://q1.qlogo.cn/g?b=qq&nk=1839938674&s=100', '2020-05-15 18:13:02', 12, 48, b'1');
INSERT INTO `t_comment` VALUES (51, '小白', '1839938674@qq.com', '博主你好！', '/images/1.jpeg', '2020-05-15 18:34:01', 12, 50, b'0');
INSERT INTO `t_comment` VALUES (52, '小白', '1839938674@qq.com', '博主你好！', '/images/1.jpeg', '2020-05-15 18:34:32', 12, 50, b'0');
INSERT INTO `t_comment` VALUES (53, '小白', '123@qq.com', '大家好！', '/images/1.jpeg', '2020-05-15 18:34:53', 12, 42, b'0');
INSERT INTO `t_comment` VALUES (54, '小黑黑', '123@qq.com', '来一个新评论', '/images/1.jpeg', '2020-05-15 18:38:57', 12, NULL, b'0');
INSERT INTO `t_comment` VALUES (55, '123', '123@qq.com', '报道！', '/images/1.jpeg', '2020-05-15 18:40:17', 12, NULL, b'0');
INSERT INTO `t_comment` VALUES (56, 'dasd', '1839938674@qq.com', 'dasdasdas', '/images/1.jpeg', '2020-05-15 18:41:37', 12, NULL, b'0');
INSERT INTO `t_comment` VALUES (57, '123', 'qq915873525@163.com', '新来的！', '/images/1.jpeg', '2020-05-15 18:45:19', 12, NULL, b'0');
INSERT INTO `t_comment` VALUES (58, '123123', '1839938674@qq.com', '评论！！！', '/images/1.jpeg', '2020-05-15 18:50:04', 12, NULL, b'0');
INSERT INTO `t_comment` VALUES (59, '123123', '1839938674@qq.com', '我来了！', '/images/1.jpeg', '2020-05-15 18:50:18', 12, 57, b'0');
INSERT INTO `t_comment` VALUES (60, 'asda', '1839938674@qq.com', 'dasdasdas', '/images/1.jpeg', '2020-05-15 18:51:50', 12, NULL, b'0');
INSERT INTO `t_comment` VALUES (61, '123', '1839938674@qq.com', '123123123', '/images/1.jpeg', '2020-05-15 18:53:44', 12, NULL, b'0');
INSERT INTO `t_comment` VALUES (62, '测试', '123@qq.com', '测试滑动', '/images/1.jpeg', '2020-05-15 18:55:34', 12, NULL, b'0');
INSERT INTO `t_comment` VALUES (63, '123', '123@qq.com', '我也来评论', '/images/1.jpeg', '2020-05-15 18:56:32', 12, NULL, b'0');
INSERT INTO `t_comment` VALUES (64, '阿萨德', '1839938674@qq.com', '奥术大师大所大', '/images/1.jpeg', '2020-05-15 18:57:12', 12, NULL, b'0');
INSERT INTO `t_comment` VALUES (65, '123', '1839938674@qq.com', '发个评论', '/images/1.jpeg', '2020-05-15 19:01:33', 7, NULL, b'0');
INSERT INTO `t_comment` VALUES (66, 'kuls', '1839938674@qq.com', '我来评论', 'http://q1.qlogo.cn/g?b=qq&nk=1839938674&s=100', '2020-05-15 19:37:37', 8, NULL, b'1');
INSERT INTO `t_comment` VALUES (67, 'kuls', '1839938674@qq.com', '没得问题', 'http://q1.qlogo.cn/g?b=qq&nk=1839938674&s=100', '2020-05-15 19:37:47', 8, NULL, b'1');
INSERT INTO `t_comment` VALUES (68, 'kuls', '1839938674@qq.com', '没问题', 'http://q1.qlogo.cn/g?b=qq&nk=1839938674&s=100', '2020-05-15 19:37:53', 8, 66, b'1');
INSERT INTO `t_comment` VALUES (69, '123', '1839938674@qq.com', 'hello！', '/images/1.jpeg', '2020-05-16 19:40:31', 14, NULL, b'0');
INSERT INTO `t_comment` VALUES (70, 'demo', '760145395@qq.com', '第一！', '/images/1.jpeg', '2020-05-16 20:12:40', 13, NULL, b'0');
INSERT INTO `t_comment` VALUES (71, 'kuls', '1839938674@qq.com', '你好！', 'http://q1.qlogo.cn/g?b=qq&nk=1839938674&s=100', '2020-05-17 08:14:52', 7, 65, b'1');
INSERT INTO `t_comment` VALUES (72, 'kuls', '1839938674@qq.com', '你好！', 'http://q1.qlogo.cn/g?b=qq&nk=1839938674&s=100', '2020-05-17 08:44:14', 14, 69, b'1');
INSERT INTO `t_comment` VALUES (73, '小红', '123@qq.com', '我来评论下', '/images/1.jpeg', '2020-05-17 20:29:58', 7, NULL, b'0');
INSERT INTO `t_comment` VALUES (74, '黑不溜秋', '123@qq.com', '回复一下', '/images/1.jpeg', '2020-05-17 20:32:12', 7, 73, b'0');
INSERT INTO `t_comment` VALUES (75, 'kuls', '1839938674@qq.com', 'hello', 'http://q1.qlogo.cn/g?b=qq&nk=1839938674&s=100', '2020-05-17 20:40:25', 7, NULL, b'1');
INSERT INTO `t_comment` VALUES (76, '读者', 'qq915873525@163.com', '牛逼！', '/images/comment-user.png', '2020-05-18 07:09:22', 14, NULL, b'0');
INSERT INTO `t_comment` VALUES (77, '读者', '123@qq.com', '评论测试', '/images/comment-user.png', '2020-05-18 07:39:25', 7, NULL, b'0');
INSERT INTO `t_comment` VALUES (78, '1233', '123@qq.com', '测试一下', '/images/comment-user.png', '2020-05-18 07:40:22', 7, NULL, b'0');
INSERT INTO `t_comment` VALUES (79, '小白', '27454349@qq.com', '我来了！！！', 'http://q1.qlogo.cn/g?b=qq&nk=27454349&s=100', '2020-05-19 09:10:10', 5, NULL, b'0');
INSERT INTO `t_comment` VALUES (80, '小白', '27454349@qq.com', '测试测试~！！！', 'http://q1.qlogo.cn/g?b=qq&nk=27454349&s=100', '2020-05-19 09:10:27', 5, NULL, b'0');
INSERT INTO `t_comment` VALUES (81, '小白', '27454349@qq.com', '哈哈哈哈', 'http://q1.qlogo.cn/g?b=qq&nk=27454349&s=100', '2020-05-19 09:10:35', 5, 80, b'0');
INSERT INTO `t_comment` VALUES (82, 'kuls', '111111@qq.com', '看看', 'http://q1.qlogo.cn/g?b=qq&nk=1839938674&s=100', '2020-05-19 09:13:21', 5, NULL, b'1');
INSERT INTO `t_comment` VALUES (86, '111', '1839938674@qq.com', '评论测试！', 'http://q1.qlogo.cn/g?b=qq&nk=1839938674&s=100', '2020-05-23 17:21:31', 7, NULL, b'0');
INSERT INTO `t_comment` VALUES (89, '就说', '1823454949@qq.com', '12133131311', 'http://q1.qlogo.cn/g?b=qq&nk=1823454949&s=100', '2020-05-28 19:03:49', 14, 76, b'0');
INSERT INTO `t_comment` VALUES (90, 'kuls', '1839938674@qq.com', '我的妈呀', 'http://q1.qlogo.cn/g?b=qq&nk=1839938674&s=100', '2020-05-28 20:53:45', 14, NULL, b'1');
INSERT INTO `t_comment` VALUES (91, '胖子', '123@qq.com', '我来评论一下', 'http://q1.qlogo.cn/g?b=qq&nk=123&s=100', '2020-05-29 07:18:00', 15, NULL, b'0');
INSERT INTO `t_comment` VALUES (92, 'Zom', '123@qq.com', 'Hello', 'http://q1.qlogo.cn/g?b=qq&nk=123&s=100', '2020-05-29 07:24:34', 12, 52, b'0');
INSERT INTO `t_comment` VALUES (93, 'kuls', '1839938674@qq.com', 'HELLO', 'http://q1.qlogo.cn/g?b=qq&nk=1839938674&s=100', '2020-05-30 20:47:31', 14, NULL, b'1');
INSERT INTO `t_comment` VALUES (94, 'kuls', '1839938674@qq.com', '你好', 'http://q1.qlogo.cn/g?b=qq&nk=1839938674&s=100', '2020-06-07 18:30:22', 15, 91, b'1');
COMMIT;

-- ----------------------------
-- Table structure for t_link
-- ----------------------------
DROP TABLE IF EXISTS `t_link`;
CREATE TABLE `t_link` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '友链名称',
  `url` varchar(200) DEFAULT NULL COMMENT '友链地址',
  `canshow` bit(1) DEFAULT NULL COMMENT '是否显示前台',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_link
-- ----------------------------
BEGIN;
INSERT INTO `t_link` VALUES (4, '我的小破站', 'http://kuls6.top', b'0');
COMMIT;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
BEGIN;
INSERT INTO `t_tag` VALUES (1, 'python');
INSERT INTO `t_tag` VALUES (2, '技术');
INSERT INTO `t_tag` VALUES (3, '认知');
INSERT INTO `t_tag` VALUES (4, '经验');
INSERT INTO `t_tag` VALUES (5, 'Java');
INSERT INTO `t_tag` VALUES (8, '编程');
INSERT INTO `t_tag` VALUES (9, '感悟');
INSERT INTO `t_tag` VALUES (10, 'docker');
INSERT INTO `t_tag` VALUES (11, 'springboot');
INSERT INTO `t_tag` VALUES (12, 'SSM');
INSERT INTO `t_tag` VALUES (13, 'Mybatis');
COMMIT;

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_type
-- ----------------------------
BEGIN;
INSERT INTO `t_type` VALUES (3, '编程开发');
INSERT INTO `t_type` VALUES (7, 'python开发');
INSERT INTO `t_type` VALUES (9, 'Java开发');
INSERT INTO `t_type` VALUES (13, '爬虫');
INSERT INTO `t_type` VALUES (14, '编程语言');
INSERT INTO `t_type` VALUES (15, 'Web开发');
INSERT INTO `t_type` VALUES (16, 'springboot');
INSERT INTO `t_type` VALUES (17, 'springcloud');
INSERT INTO `t_type` VALUES (18, 'SSM');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL COMMENT 'id',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `type` int(11) DEFAULT NULL COMMENT '用户类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, 'kuls', 'kuls', '202cb962ac59075b964b07152d234b70', '1839938674@qq.com', 'http://q1.qlogo.cn/g?b=qq&nk=1839938674&s=100', 1, '2020-05-04 18:02:39', '2020-05-04 18:02:42');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
