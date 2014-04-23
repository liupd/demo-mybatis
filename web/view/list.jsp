<%--
  Created by IntelliJ IDEA.
  User: Cai
  Date: 2014/4/23
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
    /**
     * 上一页
     */
    function prePage() {
        var pageIndex = parseInt($("#pageIndex").val());
        if (pageIndex > 1) {
            pageIndex -= 1;
            $("#pageIndex").val(pageIndex);
            $("#queryForm").submit();
        }
    }

    /**
     * 下一页
     */
    function nextPage(pageTotal) {
        var pageIndex = parseInt($("#pageIndex").val());
        if (pageIndex < pageTotal) {
            $("#pageIndex").val(pageIndex + 1);
            $("#queryForm").submit();
        }
    }

    /**
     * 选择页
     * @param pageIndex
     */
    function changePage(pageIndex) {
        if (pageIndex != $("#pageIndex").val()) {
            $("#pageIndex").val(pageIndex);
            $("#queryForm").submit();
        }
    }
</script>

<body>

<form role="form" id="queryForm" method="post" action="${pageContext.request.contextPath}/article/list">
    <%--<div class="row">--%>
        <%--<div class="col-md-3">--%>
            <%--<input name="ask" class="form-control" type="text" value="${keyWord}" placeholder="请输入关键字"/>--%>
            <input name="pageIndex" id="pageIndex" type="hidden" value="${page.currentPage}">
        <%--</div>--%>
        <%--<div class="col-md-1 row">--%>
            <%--<input type="submit" class="btn btn-info" value="查询"/>--%>
        <%--</div>--%>
        <%--<div class="col-md-1 row">--%>
            <%--<a href="javascript:;" class="btn btn-danger" data-toggle="modals" data-body="tempEdit"--%>
               <%--data-id="addModal" data-confirmFn="subAdd" data-title="新增关键字" data-ajax="0"--%>
               <%--data-data="" data-url="${pageContext.request.contextPath}/keyWords/getDataContent"--%>
               <%--data-success="addFn">--%>
                <%--<i class="fa fa-plus"></i> 新增--%>
            <%--</a>--%>
        <%--</div>--%>
    <%--</div>--%>
</form>

<c:forEach items="${articles}" var="item">
    ${item.id}--${item.title}--${item.content}<br/>
</c:forEach>

<c:if test="${page.pageTotal != 0 && not empty page }">
    <a href="javascript:;" onclick="prePage()">
        上一页
    </a>
    <c:forEach begin="${page.beginPageIndex }" end="${page.endPageIndex}" step="1" var="p" >
        <a href="javascript:;" onclick="changePage(${p})" >${p}</a>
    </c:forEach>
    <a href="javascript:;" onclick="nextPage(${page.pageTotal})">
        下一页
    </a>
</c:if>

</body>
</html>
