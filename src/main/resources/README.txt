#.配置SpringBoot热部署
#.集成swagger2自动生成接口文档
#.集成lombok去除实体类的get和set方法
#.集成Security加密和鉴权
#.集成JPA实现持久化
#.集成Log4j实现日志打印
#.集成PageHelper分页查询https://blog.csdn.net/maoyuanming0806/article/details/77720754

#附近的人
#$lat=20; $lon=20;
#SELECT * FROM users_location ORDER BY ACOS(SIN(('.$lat.' * 3.1415) / 180 ) *SIN((latitude * 3.1415) / 180 ) +COS(('.$lat.' * 3.1415) / 180 ) * COS((latitude * 3.1415) / 180 ) *COS(('.$lon.' * 3.1415) / 180 - (longitude * 3.1415) / 180 ) ) * 6380 ASC LIMIT 10
#只对于经度和纬度大于或小于该用户1度(111公里)范围内的用户进行距离计算,同时对数据表中的经度和纬度两个列增加了索引来优化where语句执行时的速度.
#SELECT * FROM users_location WHERE latitude > '.$lat.'-1 AND latitude < '.$lat.'+1 AND longitude > '.$lon.'-1 AND longitude < '.$lon.'+1 ORDER BY ACOS(SIN(('.$lat.' * 3.1415) / 180 ) *SIN((latitude * 3.1415) / 180 ) +COS(('.$lat.' * 3.1415) / 180 ) * COS((latitude * 3.1415) / 180 ) *COS(('.$lon.'* 3.1415) / 180 - (longitude * 3.1415) / 180 ) ) * 6380 ASC LIMIT 10

Eureka Client并没有自己实现探测本机IP的逻辑，而是交给Spring的org.springframework.cloud.commons.uti.InetUtils工具类的findFirstNonLoopbackAddress()方法完成
下面这位哥们分析了eureka 客户端是如何获取本机ip地址：
http://blog.csdn.net/neosmith/article/details/53126924



Ctrl+Shift+O自动引包快捷键