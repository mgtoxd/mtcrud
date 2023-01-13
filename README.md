# MtCrud

#### 介绍
数据库的更抽象操作方式，支持HTTP和GRPC协议，并且支持AT模式的分布式事务，可用于简单项目的快速开发和大型分布式项目中数据库的操作

#### 使用

1. 使用 Releases 中的二进制文件
2. 编写 setting.yaml，与二进制文件在同一目录下 

   [setting样例]:https://github.com/mgtoxd/mtcrud/blob/master/setting.yaml
3. 运行二进制文件
4. 生成 DataStructure.json 包含数据库和表的代码

##### HTTP请求

* 普通查询（get请求）：/mt/数据库代码/表代码/需要返回的列代码（用英文;分割）/分页页码/分页大小  （不分页不传）

* 复杂查询(post):/mget/数据库代码/表代码/需要返回的列代码（用英文;分割）

  > ```json
  > {
  > 'eq':{
  > '列代码':'列代码值',
  > },
  > 'gt':{
  > '列代码':'列代码大于的值',
  > },
  > 'lt':{
  > '列代码':'列代码小于的值',
  > },
  > 'like':{
  > '列代码':'列代码like的值 例如“mt%”',
  > },
  > 'order':{    A为Asc D为DESC
  >  '列代码':'A',
  >  '列代码':'D'
  > },
  > 'limit':'限制条数'
  > }
  > ```



* post：/mt/数据库代码/表代码

  > body: 格式为application/json
  >
  > ```json
  > {
  > 
  >     'id':'列代码', //需要雪花的id值
  >     'ct':'列代码',	//create_time
  >     'ut':'列代码',   //modify_time
  >     'valueMap':[
  >         {'列代码':'列代码值'},
  >         {'列代码':'列代码值'}
  >         ]
  > 
  > }
  > ```
* put: /mt/数据库代码/表代码

  > body: 格式为application/json
  >
  > ```json
  > {
  >     'condition':{	
  >         'eq':{
  > 			'列代码':'列代码值',
  > 			},
  > 		'gt':{
  > 			'列代码':'列代码大于的值',
  > 			},
  > 		'lt':{
  > 			'列代码':'列代码小于的值',
  > 			},
  > 		'like':{
  > 			'列代码':'列代码like的值 例如“mt%”',
  > 			},
  >     },
  >     'valueMap':[
  >         {'列代码':'列代码值'},
  >         {'列代码':'列代码值'}
  >         ]
  > }
  > ```
* del:/mt/数据库代码/表代码

  > ```json
  > {
  >     'eq':{
  > 	'列代码':'列代码值',
  > 	},
  > 	'gt':{
  > 	'列代码':'列代码大于的值',
  > 	},
  > 	'lt':{
  > 	'列代码':'列代码小于的值',
  > 	},
  > 	'like':{
  > 	'列代码':'列代码like的值 例如“mt%”',
  > 	},
  > }
  > ```
##### grpc请求

[grpc文件]:https://github.com/mgtoxd/mtcrud/blob/master/src/main/proto/mtcrud.proto

* GetData 查询
* PostData 增加
* PutData 更新
* DelData 删除
* ATDelData AT事务模式删除
* ATPostData AT事务模式添加
* ATPutData AT事务模式更新
* ConfirmAT AT事务提交
* RollbackAT AT事务回滚
