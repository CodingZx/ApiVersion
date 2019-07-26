# ApiVersion

扩展SpringMVC, 基于api版本号自动选择对应接口


###使用方式
- 配置基本参数
> \# 设置请求Header名称 <br>
>  cicco.api.version.header = api-version                    <br>
> \# 设置版本号分隔符 <br>
>  cicco.api.version.separator = \\.                         <br>
> \# 设置默认版本号  <br>
>  cicco.api.version.defaultVersion = 1.0.0                  <br>

- Application引用Properties
> @Import(ApiVersionProperties.class)

- 使用ApiVersion注解
```java
    @GetMapping("/version")
    @ApiVersion(version = "1.0.1")
    public String testController() {
        return "version-1.0.1";
    }    
```
### 说明
优先选取小于等于请求Header值中的最大版本接口<br>
若接口未加ApiVersion注解, 默认为配置项cicco.api.version.defaultVersion的版本<br>

####示例详见[单元测试](https://github.com/CodingZx/apiVersion/tree/master/src/test)
