# Myf Foundation
个人自用的通用类库，包括缓存、日志、对象存储等

工程进度
- [ ] 缓存相关
- [ ] 日志相关
- [ ] BigDecimal相关
- [x] 断言工具类

# How to use
See [official reference](https://help.github.com/en/github/managing-packages-with-github-packages/configuring-apache-maven-for-use-with-github-packages#authenticating-to-github-packages) to authenticate to GitHub Packages

Add Github Repository and Dependency
```xml
<repositories>
    <repository>
        <id>github</id>
        <name>GitHub majiaxin110 Apache Maven Packages</name>
        <url>https://maven.pkg.github.com/majiaxin110/myf-foundation</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>dev.mtage</groupId>
        <artifactId>myf-foundation</artifactId>
        <version>0.0.3</version>
    </dependency>
</dependencies>
```

poi~