# 维护记录与验证边界

## 2026-10-21：维护基础与缺陷修复

### 已完成的修改

- 添加标准 MIT License，说明第三方 SDK、二进制及素材的授权边界。
- 补充项目功能状态、构建要求、贡献方式和已知待办。
- 修复网络工具将 HTTP 4xx/5xx 响应体当作成功结果返回的问题。GET、JSON POST 和文件上传在非 2xx 响应时抛出带状态码的 `IOException`，成功响应保持原有内容，并关闭响应资源。
- 修复行程选择页面取消返回或返回数据为空时继续访问数据的问题。
- 添加使用真实网络工具源码的独立 JVM 测试，避免测试被 Android SDK、地图/短信配置和历史业务后端阻塞。
- 添加运行相同测试的 GitHub Actions 配置，并校验 Gradle 分发包的 SHA-256。
- 为 Mob 插件补充官方 Maven 仓库并固定实际版本；优先从 Maven Central 解析通用依赖，将失效的 JCenter 地址替换为阿里云 JCenter 镜像，保持原依赖坐标。
- 明确使用 Java 8 和 Build Tools 28.0.3，恢复 Debug APK 构建。

### 本地验证

使用 JDK 8 和 Gradle Wrapper 4.6 执行：

```sh
sh ./gradlew -p maintenance-tests --no-daemon test
```

8 项测试全部通过。同一套测试针对修改前的网络工具运行时，HTTP 错误相关的 4 项失败，正常请求相关的 4 项通过。这证明新增测试能识别本次修复的错误行为。

完整 Android 配置检查起初因找不到 `com.mob.sdk:MobSDK:2018.0319.1724` 失败；补充官方仓库后，发现其 POM 会传递解析 `MobSDK2022:LATEST`。现直接固定为本次实际验证的 `com.mob.sdk:MobSDK2022:2026.0414.1436`，避免继续通过旧坐标间接选择最新插件。Mob 短信 SDK 的传递依赖仍由插件选择，本次构建解析到 SMSSDK 3.7.10。

补齐临时 Android SDK Platform 28 和 Build Tools 28.0.3 后，发现 GoodView 1.0.0、PickerView 1.0.3、RxPermissions 0.9.5 在原仓库不可用。镜像能提供相同坐标，调整仓库后恢复解析。

```sh
sh ./gradlew :app:assembleDebug
```

最终结果：`BUILD SUCCESSFUL`，27 个任务执行，生成各 ABI 及 universal Debug APK。构建仍有历史库缺少可选类型、过时 API 和重复权限等警告。未进行设备安装、页面运行或业务后端联调；行程取消返回修复只完成源码和编译验证。GitHub CI 运行结果应以 Actions 页面为准。

### 尚未完成

- APK 的安装、设备验证和后端联调。
- 旧 SDK/依赖向受维护版本迁移，以及 Mob SDK 传递依赖的进一步固定。
- 图文发布顺序、登录用户来源、评论提交、搜索数据及行程保存。
- 历史业务后端联调与第三方素材来源核查。

以上记录只描述已实施及已验证的事项，不代表项目已有大规模用户或完整生产可用性。
