## web工程中URL地址的推荐写法
#####在JavaWeb开发中，只要是写URL地址，那么建议最好以"/"开头，也就是使用绝对路径的方式，那么这个"/"到底代表什么呢？可以用如下的方式来记忆"/"：
<font color=red>如果"/"是给服务器用的，则代表当前的web工程，如果"/"是给浏览器用的，则代表webapps目录。</font>

####1、"/"代表当前web工程的常见应用场景
①.ServletContext.getRealPath(String path)获取资源的绝对路径
```java
/**
* 1.ServletContext.getRealPath("/download/1.JPG")是用来获取服务器上的某个资源，
* 那么这个"/"就是给服务器用的，"/"此时代表的就是web工程
 * ServletContext.getRealPath("/download/1.JPG")表示的就是读取web工程下的download文件夹中的1.JPG这个资源
* 只要明白了"/"代表的具体含义，就可以很快写出要访问的web资源的绝对路径
*/
this.getServletContext().getRealPath("/download/1.JPG");
```
②.在服务器端forward到其他页面
```java
/**
  * 2.forward
   * 客户端请求某个web资源，服务器跳转到另外一个web资源，这个forward也是给服务器用的，
  * 那么这个"/"就是给服务器用的，所以此时"/"代表的就是web工程
  */
  this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
```


③.使用include指令或者<jsp:include>标签引入页面
```jsp
<%@include file="/jspfragments/head.jspf" %>
<jsp:include page="/jspfragments/demo.jsp" />
```
此时"/"代表的都是web工程。

------------------

####2、"/"代表webapps目录的常见应用场景
①.使用sendRedirect实现请求重定向
```java
response.sendRedirect("/JavaWeb_HttpServletResponse_Study_20140615/index.jsp");
```
服务器发送一个URL地址给浏览器，浏览器拿到URL地址之后，再去请求服务器，所以这个"/"是给浏览器使用的，此时"/"代表的就是webapps目录，"/JavaWeb_HttpServletResponse_Study_20140615/index.jsp"这个地址指的就是"webapps\JavaWeb_HttpServletResponse_Study_20140615\index.jsp"

　　response.sendRedirect("/项目名称/文件夹目录/页面");这种写法是将项目名称写死在程序中的做法，不灵活，万一哪天项目名称变了，此时就得改程序，所以推荐使用下面的灵活写法：
```java
response.sendRedirect(request.getContextPath()+"/index.jsp");
```
request.getContextPath()获取到的内容就是"/JavaWeb_HttpServletResponse_Study_20140615"，这样就比较灵活了，使用request.getContextPath()代替"/项目名称"，推荐使用这种方式，灵活方便！

②.使用超链接跳转
```jsp
<a href="/JavaWeb_HttpServletResponse_Study_20140615/index.jsp">跳转到首页</a>
```
是客户端浏览器使用的超链接跳转，这个"/"是给浏览器使用的，此时"/"代表的就是webapps目录。
```jsp
<a href="${pageContext.request.contextPath}/index.jsp">跳转到首页</a>
```
这样就可以避免在路径中出现项目的名称，使用${pageContext.request.contextPath}取代"/JavaWeb_HttpServletResponse_Study_20140615"

③.Form表单提交
```jsp
<form action="/JavaWeb_HttpServletResponse_Study_20140615/servlet/CheckServlet" method="post">    
         <input type="submit" value="提交"> 
         </form>
```
这是客户端浏览器将form表单提交到服务器，所以这个"/"是给浏览器使用的，此时"/"代表的就是webapps目录。
改进：使用${pageContext.request.contextPath}取代"/JavaWeb_HttpServletResponse_Study_20140615"
```jsp
<form action="${pageContext.request.contextPath}/servlet/CheckServlet" method="post">
          <input type="submit" value="提交">
</form>
```
④.js脚本和css样式文件的引用
```jsp
<%--使用绝对路径的方式引用js脚本--%>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
 <%--${pageContext.request.contextPath}与request.getContextPath()写法是得到的效果是一样的--%>
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>
 <%--使用绝对路径的方式引用css样式--%>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" type="text/css"/>
```
优化总结：
当"/"代表webapp目录时：
  java类中采用request.getContextPath()来替换"/项目名称"；
  jsp，js,css文件中，采用pageContext.request.contextPath来替换"/项目名称"；


