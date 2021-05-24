# CRUD基础工程

## 初始化支持增加***功能
* SSM：Springboot+Mybatits-plus
* 基类封装：BaseController提供Spring异步事件监听, 获取IP等公共方法; BaseService集成ServiceImpl提供单表分页查询,CRUD等操作
* 事件监听：Spring异步监听更新操作记录表
* 异常：异常封装工具类(ExceptionWrapper)+自定义异常(SystemException)+AOP拦截(Handler)+异常枚举(ExceptionEnums)
* 定时任务: es-job
* 配置管理: Consul+热刷新
* 缓存: Redis(连接池)
* 常用工具类: lombok，DateUtil，JsonUtil，GeneralUtil，httpClient(Retrofit),SpringContext，Swagger
* 单元测试：1. 提供Spring基类代码 Generate代码自动生成
* 日志：本地log + logstash(TCP) 后续可以直接接入ELK系统
* Dockerfile 支持镜像打包
* 连接池：数据库连接池 + 事务管理

## 2021-12-31 增加***功能
* 
* 

## TODO
- [ ] 一级标题

- [ ] 父标题
> - [ ] 子标题

