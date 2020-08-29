<%--
  Created by IntelliJ IDEA.
  User: yuan
  Date: 2020/2/23
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>异步提交</title>
    <script type="text/javascript" src="/static/js/jquery-3.1.0.min.js"></script>
    <script>
            //发送ajax请求，加载所有省份数据
            $.ajax({
                url:"/pro/list",
                type:'post',
                dataType:'json',
                contentType:'application/json;charset=UTF-8',
                success:function (rs) {
                    console.log(rs)
                    if (rs.code!=200){
                        layer.msg("获取失败");
                            return false;
                    }
                    var result=rs.data;
                    var option=$("#province");
                    $.each(result,function (index,value) {
                        option.append("<option value='"+value.id+"'>"+value.name+"</option>")
                    })

                    }

            })

    </script>
</head>
<body>
            <select id="province">
            <option>请选择一个城市</option>
        </select>
</body>
</html>
