### 遇到问题：

1. 弹窗显示html页面

   ```html
   
               // 监听行工具事件
               // 使用lay-filter识别
               table.on('tool(table_toolbox)', function(obj){
                   var data = obj.data;
                   //console.log(obj)
                   if (obj.event === 'edit') {
                       if (data.name === '获取token'){
                               layer.open({
                                   type: 2,
                                   skin: 'layui-layer-rim', //加上边框
                                   area: ['880px', '420px'], //宽高
                                   content: '/toolbox/get_install_token'
                           });
                       }
   
                   }
               })
   ```

   

2. 弹窗显示样式

```html
            // 监听行工具事件
            // 使用lay-filter识别
            table.on('tool(table_toolbox)', function(obj){
                var data = obj.data;
                var html = '<form class="layui-form" action="get_install_token" method="post" target="">\n' +
                    '\n' +
                    '  <div class="layui-form-item" pane="">\n' +
                    '    <label class="layui-form-label">执行环境</label><br>\n' +
                    '       <div class="layui-input-block">\n' +
                    '         <input type="radio" name="env" value="demo" title="demo环境" checked="">\n' +
                    '         <input type="radio" name="env" value="api2" title="api2环境">\n' +
                    '         <input type="radio" name="env" value="api" title="正式环境">\n' +
                    '       </div>\n' +
                    '  </div>\n' +
                    '\n' +
                    '\n' +
                    '  <div class="layui-form-item">\n' +
                    '    <label class="layui-form-label">用户id</label>\n' +
                    '    <div class="layui-input-inline">\n' +
                    '      <input type="text" name="user_id" lay-verify="required" placeholder="请输入用户id" autocomplete="off" class="layui-input" >\n' +
                    '    </div>\n' +
                    '  </div>\n' +
                    '\n' +
                    '\n' +
                    ' <div class="layui-form-item">\n' +
                    '    <div class="layui-input-block">\n' +
                    '      <button type="submit" class="layui-btn" lay-submit="demo1" lay-filter="demo1">立即提交</button>\n' +
                    '    </div>\n' +
                    '  </div>\n' +
                    '\n' +
                    '</form>\n' 

                if (obj.event === 'edit') {
                    if (data.name === '获取token'){
                            layer.open({
                                type: 1,    // 弹出一个页面
                                title: data.name,   // 获取name的值设置为title
                                offset: 'auto',     // 出现时的坐标  默认是垂直水平居中
                                skin: 'layui-layer-rim', //加上边框
                                area: ['880px', '420px'], //宽高
                                content: html,
                        })
                        // 渲染弹窗页面的form样式
                        form.render();
                    }
                }
            })
```



3. 弹窗显示的单选框样式展示不了

```html
                if (obj.event === 'edit') {
                    if (data.name === '获取token'){
                            layer.open({
                                type: 1,    // 弹出一个页面
                                title: data.name,   // 获取name的值设置为title
                                offset: 'auto',     // 出现时的坐标  默认是垂直水平居中
                                skin: 'layui-layer-rim', //加上边框
                                area: ['880px', '420px'], //宽高
                                content: html,
                        })
                        // 渲染弹窗页面的form样式
                        form.render();
                    }
                }
            })
```



4. 弹窗form表单提交报错：

   ```text
   flask.debughelpers.FormDataRoutingRedirect: b'A request was sent to this URL (http://127.0.0.1:5000/notify)
   but a redirect was issued automatically by the routing system to &quot;http://127.0.0.1:5000/toolbox/get_install_token. The
   URL was defined with a trailing slash so Flask will automatically redirect to the URL with the trailing slash if
   it was accessed without one. Make sure to directly send your POST-request to this URL since we can\'t make
   browsers or HTTP clients redirect with form data reliably or without user interaction.\n\nNote: this exception
   is only raised in debug mode' // Werkzeug Debugger
   ```

```
@blue.route('/toolbox/get_install_token/', methods=['GET', 'POST'], strict_slashes=False)
def get_install_token():
    """获取用户token"""
    if request.method == 'GET':

        return render_template('toolbox/get_install_token.html')

    elif request.method == 'POST':

        env = request.form.get('env')
        user_id = request.form.get('user_id')
        resp = TestToolbox().test_token(env, user_id)
        return resp
```



5. 弹窗fomr表单提交后会跳转页面：

```
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>toolbox_list</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">

</head>

<body>


<div class="demoTable">
    搜索功能名称：
    <div class="layui-inline">
        <input class="layui-input" name="id" id="demoReload" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
</div>

    <table class="layui-hide" id="table_toolbox" lay-filter="table_toolbox"></table>


    <script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-sm" lay-event="edit">使用</a>
    </script>

    <script src="/static/layui/layui.js" charset="utf-8" merge="true"></script>
    <script src="/static/js/jquery-3.3.1.min.js" ></script>

    <script>
        layui.use(['table', 'layer', 'form'], function () {
            var table = layui.table
            ,layer = layui.layer
            ,form = layui.form
            ,$ = layui.jquery

            // layer.config({
            //     path: '/static/layui/lay/modules/layer.js' //layer.js所在的目录，可以是绝对目录，也可以是相对目录
            // });

            table.render({
                elem: '#table_toolbox'
                , url: 'get_toolbox_list'
                , title: '工具箱功能表'
                , cols: [[
                    { field: 'id', title: 'ID', width: '10%' , sort: true, fixed: true } 
                    , { field: 'name', title: '功能名称', width: '50%' }
                    , { fixed: 'right', title: '操作', toolbar: '#barDemo', width: '40%' }
                ]]
                , page: true
                , curr: 1 // 起始页设为第1页
                , id: 'testReload'
            });


            // 监听行工具事件
            // 使用lay-filter识别
            table.on('tool(table_toolbox)', function(obj){
                var data = obj.data;
                var get_install_token_html = '<form class="layui-form" action="" method="post" target="">' +
                    '  <div class="layui-form-item" pane="">' +
                    '    <label class="layui-form-label">执行环境</label><br>' +
                    '       <div class="layui-input-block">' +
                    '         <input type="radio" name="env" value="demo" title="demo环境" checked="">' +
                    '         <input type="radio" name="env" value="api2" title="api2环境">' +
                    '         <input type="radio" name="env" value="api" title="正式环境">' +
                    '       </div>' +
                    '  </div>' +
                    '  <div class="layui-form-item">' +
                    '    <label class="layui-form-label">用户id</label>' +
                    '    <div class="layui-input-inline">' +
                    '      <input id="user_id" type="text" name="user_id" lay-verify="required" placeholder="请输入用户id" autocomplete="off" class="layui-input" required>' +
                    '    </div>' +
                    '  </div>' +
                    ' <div class="layui-form-item">' +
                    '    <div class="layui-input-block">' +
                    '    <button type = "button" class="layui-btn" id="submit-btn"> 立即提交</button >' +  
                    '    </div>' +
                    '  </div>' +
                    '</form>' +
                    '<legend><h3>---------------------------处理结果---------------------------</h3></legend></fieldset>' +
                    '<blockquote id="resp" class="layui-elem-quote"></blockquote>'

                if (obj.event === 'edit') {
                    if (data.name === '获取token'){
                        layer.open({
                            type: 1,    // 弹出一个页面
                            title: data.name,   // 获取name的值设置为title
                            shadeClose: true,   // 点击关闭蒙层
                            offset: 'auto',     // 出现时的坐标  默认是垂直水平居中
                            skin: 'layui-layer-rim', //加上边框
                            area: ['880px', '420px'], //宽高
                            async:false,        // 同步请求
                            content: get_install_token_html,
                        })
                        // 渲染弹窗页面的form样式
                        form.render();
                        $(document).on('click','#submit-btn',function(){
                            var env = $('input:radio:checked').val()        // 获取单选框被选中的值
                            var user_id = document.getElementById('user_id').value      // 获取输入的user_id值
                            $.ajax({
                                type: 'post',
                                url: 'get_install_token',
                                dataType: 'text',
                                data: {'env':env, 'user_id':user_id},
                                success: function (result) {
                                    document.getElementById('resp').innerHTML = result
                                }
                            })
                            
                        })
                        
                    }
                }
            })


            // // 搜索重载页面
            //   var $ = layui.$, active = {
            //     reload: function () {
            //         var demoReload = $('#demoReload');
            //         //执行重载
            //         table.reload('testReload', {
            //             page: {
            //                 curr: 1 //重新从第 1 页开始
            //             }
            //             , where: {
            //                 key: {
            //                     id: demoReload.val()
            //                 }
            //             }
            //         }, 'data');
            //     }
            // };

            // $('.demoTable .layui-btn').on('click', function () {
            //     var type = $(this).data('type');
            //     active[type] ? active[type].call(this) : '';
            // });

        })
</script>

</body>

</html>
```





