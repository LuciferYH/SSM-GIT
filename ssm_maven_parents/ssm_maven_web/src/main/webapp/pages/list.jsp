<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>查询所有成功了</h3>

    <c:forEach items="${productList}" var="product">

        <tr>
            <td><input name="ids" type="checkbox"></td>
            <td>${product.id }</td>
            <td>${product.productNum }</td>
            <td>${product.productName }</td>
            <td>${product.cityName }</td>
            <td>${product.departureTimeStr }</td>
            <td class="text-center">${product.productPrice }</td>
            <td>${product.productDesc }</td>
            <td class="text-center">${product.productStatusStr }</td>
            <td class="text-center">
                <button type="button" class="btn bg-olive btn-xs">订单</button>
                <button type="button" class="btn bg-olive btn-xs">详情</button>
                <button type="button" class="btn bg-olive btn-xs">编辑</button>
            </td>
        </tr>
    </c:forEach>
</body>
</html>
