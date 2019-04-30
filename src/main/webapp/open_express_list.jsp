<%@ page import="java.util.List" %>
<%@ page import="manage.ExpressManage" %>
<%@ page import="entity.Express" %>
<%@ page import="exception.ExpressException" %>
<%@ page import="manage.impl.ExpressManageImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ExpressManage expressManage = new ExpressManageImpl();
    List<Express> lists = null;
    try {
        lists = expressManage.listNotTakeExpress();
    } catch (ExpressException e) {
        request.setAttribute("title","错误：");
        request.setAttribute("detail",e.getMessage());
        request.getRequestDispatcher("/comm/error.jsp").forward(request,response);
    }
    if (lists == null) {
        request.setAttribute("title","数据为空");
        request.setAttribute("detail","快递数据为空");
        request.getRequestDispatcher("/comm/error.jsp").forward(request,response);
    }
%>

<jsp:include page="comm/header.jsp" flush="true"  />
<jsp:include page="comm/nav.jsp" flush="true" />

<h1 class="text-center" >小区未领取快递信息查询</h1>
<div class="table-responsive">
    <table class="table table-hover table-striped">
        <tr>
            <th>编号</th>
            <th>快递名称</th>
            <th>姓名</th>
            <th>手机号码</th>
            <th>快递单号</th>
            <th>创建时间</th>
        </tr>
        <%
            for(int i=0;i<lists.size();i++) {
                Express e = lists.get(i);
        %>
        <tr>
            <td><%=e.getEid()%></td>
            <td><%=e.getExpress_name()%></td>
            <td><%=e.getUsername()%></td>
            <td><%=e.getPhone()%></td>
            <td><%=e.getExpress_id()%></td>
            <td><%=e.getGmt_create()%></td>
        </tr>
        <%
            }
        %>
    </table>
</div>

<jsp:include page="comm/footer.jsp" flush="true" />