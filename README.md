# 畅游 · cy

畅游是一个使用 Java 开发的原生 Android 旅游应用，包含旅游内容社区和行程配置界面。项目保留了早期 Android 应用的实现，当前维护工作从可复现的测试、缺陷修复和文档整理开始。

## 功能与当前状态

| 模块 | 已有实现 | 当前限制 |
| --- | --- | --- |
| 账号与资料 | 短信验证码注册、密码登录、头像上传、个人资料编辑 | 依赖业务后端、Mob 短信服务及有效配置 |
| 内容社区 | 推荐/关注列表、图文展示、点赞和取消点赞、评论查询、系统分享 | 部分页面固定使用用户 ID；发表评论尚未接通 |
| 内容发布 | 游记、攻略、动态编辑，最多选择 9 张图片，发布及上传请求 | 图片上传和内容创建顺序仍需修复及后端联调 |
| 行程配置 | 日期、人数、偏好、预算、城市、停留天数、景点/住宿/餐饮选择 | 大量示例数据；未实现完整的行程保存与路线优化 |
| 个人内容 | 关注、粉丝、收藏内容列表 | 收藏夹和问答等部分页面使用静态数据 |
| 首页与搜索 | 三个首页栏目、搜索分类页面、百度定位/搜索 SDK 接入 | 首页图片为示例；搜索分类没有真实查询结果 |

仓库仅包含客户端，不包含业务后端。已有接口代码不代表对应线上服务当前可用，也不代表 App 已通过端到端验收。

## 目录

```text
app/src/main/java/com/example/lxc/cy/
  main/       Activity 页面
  fragment/   首页、社区和搜索分页
  adapter/    列表与图片适配器
  bean/       数据对象
  other/      网络、图片和通用工具
app/src/main/res/       页面布局和资源
app/libs/              历史第三方 JAR 与原生库
maintenance-tests/     不依赖 Android SDK 的网络回归测试
.github/workflows/     网络回归测试 CI
```

## 运行网络回归测试

需要 JDK 8，Gradle 由仓库自带的 Wrapper 下载。测试直接编译 App 中的 `OkhttpHelper.java`，使用本机 MockWebServer，不连接业务后端，也不需要短信/地图密钥或 Android SDK。

```sh
java -version
sh ./gradlew -p maintenance-tests --no-daemon test
```

覆盖 GET、JSON POST、multipart 文件上传的正常响应、HTTP 错误、空响应，以及错误后的后续请求。测试报告位于 `maintenance-tests/build/reports/tests/test/index.html`。GitHub Actions 运行同一命令；它的通过仅证明网络层测试通过。

## 构建 Android App

历史构建版本：JDK 8、Gradle 4.6、Android Gradle Plugin 3.2.0、Android SDK Platform 28，最低 Android API 21。不要直接用 JDK 17 运行这个版本的 Gradle。

1. 安装 Android SDK Platform 28 和对应 Build Tools。
2. 在未提交的 `local.properties` 中设置 `sdk.dir=/your/android/sdk`，或设置 `ANDROID_HOME`。
3. 为自己的应用配置百度 SDK 和 Mob 短信服务；业务接口地址在 `app/src/main/res/values/strings.xml`。历史代码中仍有其他固定地址和用户 ID，需要逐步整理。
4. 执行：

```sh
sh ./gradlew :app:assembleDebug
```

项目仍使用旧版 Support Library、JCenter/JitPack 依赖和历史 SDK。完整构建及运行情况见 [维护记录](docs/maintenance.md)，请勿把网络测试通过等同于 APK 可用。

## 参与维护

请先阅读 [CONTRIBUTING.md](CONTRIBUTING.md)。欢迎提交可复现的缺陷、构建信息和小范围修复。近期任务及验证边界见 [维护记录](docs/maintenance.md)。

## 许可证

项目原创代码及文档采用 [MIT License](LICENSE)。第三方依赖、SDK、随附二进制和外部素材保留各自权利及适用条款，根目录许可证不授予这些内容额外权利，详见 [THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md)。
