### 状态

1. 每次HttpRequest请求都会收到一个HttpResponse
2. 每个HttpResponse都会携带一个对应的状态码
   - 2xx
     - 成功系列
   - 3xx
     - 转发，重定向
   - 4xx
     - 客户端错误
   - 5xx
     - 服务器错误
     - 逻辑错误



### Response

1. Content, code
   - code 根据实际需要指定
   - 要符合状态码规则
   - 也可能用到反爬上
2. render_template
   - 使用完全同上
   - Template()配合render(key=value, key1 =value1)
3. make_response
   - 制作一个响应
   - 返回的结果是Response的一个对象
4. 直接实例化Response对象
   - 最底层的方式
5. 还可以使用重定向
   - redirect(地址)
   - Url_for 反向解析获得
6. 还可以返回json
   - jsonlfly
     - 支持直接的字典传输
     - 构造使用key=value， key=value
7. 还可以终止响应
   - abort
   - 只需传递终止的状态码



### 爬虫

1. 数据抓取
2. 数据提取
   - 提取正常请求的
   - 状态码异常的通常不会提取
3. 数据存储





### 会话技术

1. 出现场景
   - 我们的不同请求，可能后面的请求需要前面请求返回的数据
2. 解决问题
   - web开发中http短连接
   - 从request到response请求的生命周期就结束了
3. 解决方案
   - cookie
     - 客户端会话技术
     - 所有信息存储在客户度，服务端不做任何信息存储
     - cookie 默认不支持中文
     - flask 默认支持中文
       - 当我们传入中文cookie值得时候，会被自动编码，后去的时候会自动解码
     - cookie 默认会携带本网站的所有cookie
       - 网站指定的ip和域名
     - cookie 不能跨域名
       - 想跨域名，frame 
   - session
     - 



### 登录功能

1. 个人中心

   - cookie信息获取

2. 登录

   - form 表单提交 
   - cookie 数据存储

3. 登出

   - cookie 数据清除

    