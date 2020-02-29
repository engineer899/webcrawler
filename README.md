## 系统名称
许可证信息自动爬取,导入,更新系统

###系统使用方法
1.在数据库中创建许可证信息表 表名统一为zr_licence
2.在数据库中创建企业信息表 表名统一为ZR_VE_BASEINFO_REGSTATE
3.根据具体的业务需求 修改配置文件:
environment      指定所要爬取哪个地区的企业许可证信息 设置之前应将编译器编码设置成utf-8 
                 请查看项目中test.util.SiftedData类再进行设置 或作出相应修改进行自定义设置
                 
jdbc.driver      指定数据库的驱动 默认为oracle.jdbc.driver.OracleDriver
jdbc.url         指定连接数据库的url
jdbc.username    用户名
jdbc.password    密码
ZR_VE_BASEINFO   企业主体信息表的表名  统一为: 用户名.ZR_VE_BASEINFO_REGSTATE 的形式
StartTime        每天开始更新的时间

例：
environment=江西省抚州市崇仁县
jdbc.driver=oracle.jdbc.driver.OracleDriver
jdbc.url=jdbc:oracle:thin:@192.168.10.40/CREDIT
jdbc.username=NECIPS_CRRCJG
jdbc.password=NECIPS_CRRCJG
ZR_VE_BASEINFO=SJTBK_CR.ZR_VE_BASEINFO_REGSTATE
StartTime=21:00:00


3.设置好配置文件后 将整个项目打成jar包 放到服务器任意文件夹中 
用命令java -Dfile.encoding=utf-8 -jar webcrawler03.jar 启动项目


