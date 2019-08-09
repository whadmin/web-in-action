package com.wuhao.web.servletNorm.http.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Map;

/**
 * 获取客户端提交的参数
 * 通过request、response对象获取中文的值时，需要设置为统一的编码；
 * 不设置request的编码，中文信息会显示为乱码；
 * 只设置request的编码，response不设置编码，则中文字符显示为？
 * 通过POST请求提交的中文信息，只需要设置request的编码，即可正确显示中文
 *
 * @author wuhao1
 */
@WebServlet(
        name = "RequestGetHttpParam",
        urlPatterns = {"/RequestGetHttpParam"}
)
public class RequestGetHttpParam extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //客户端是以UTF-8编码提交表单数据的，所以需要设置服务器端以UTF-8的编码进行接收，否则对于中文数据就会产生乱码
        request.setCharacterEncoding("UTF-8");
//        displaybyParameter(request,response);

//        //通过getParameterNames()方法来获取Form表单中的数据
//        displayFromByParameterNames(request,response);
//        //通过request.getParameterMap()方法来获取客户端提交的参数信息
        displayFromByParameterMap(request, response);

    }

    /**
     * 通过getParameter()方法来获取form表单中的数据
     */
    private void displaybyParameter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 编&nbsp;&nbsp;号(文本框)：
         <input type="text" name="userid" value="NO." size="2" maxlength="2">
         */
        //获取填写的编号，userid是文本框的名字
        String userid = request.getParameter("userid");
        /**
         * 用户名(文本框)：<input type="text" name="username" value="请输入用户名">
         */
        //获取填写的用户名
        String username = request.getParameter("username");
        /**
         * 密&nbsp;&nbsp;码(密码框)：<input type="password" name="userpass" value="请输入密码">
         */
        //获取填写的密码
        String userpass = request.getParameter("userpass");
        //获取选中的性别
        String sex = request.getParameter("sex");
        //获取选中的部门
        String dept = request.getParameter("dept");
        //获取选中的兴趣，因为可以选中多个值，所以获取到的值是一个字符串数组，因此需要使用getParameterValues方法来获取
        String[] insts = request.getParameterValues("inst");
        //获取填写的说明信息
        String note = request.getParameter("note");
        //获取隐藏域的内容
        String hiddenField = request.getParameter("hiddenField");

        String instStr = "";
        /**
         * 获取数组数据的技巧，可以避免insts数组为null时引发的空指针异常错误！
         */
        for (int i = 0; insts != null && i < insts.length; i++) {
            if (i == insts.length - 1) {
                instStr += insts[i];
            } else {
                instStr += insts[i] + ",";
            }
        }

        String htmlStr = "<table>" +
                "<tr><td>填写的编号：</td><td>{0}</td></tr>" +
                "<tr><td>填写的用户名：</td><td>{1}</td></tr>" +
                "<tr><td>填写的密码：</td><td>{2}</td></tr>" +
                "<tr><td>选中的性别：</td><td>{3}</td></tr>" +
                "<tr><td>选中的部门：</td><td>{4}</td></tr>" +
                "<tr><td>选中的兴趣：</td><td>{5}</td></tr>" +
                "<tr><td>填写的说明：</td><td>{6}</td></tr>" +
                "<tr><td>隐藏域的内容：</td><td>{7}</td></tr>" +
                "</table>";
        htmlStr = MessageFormat.format(htmlStr, userid, username, userpass, sex, dept, instStr, note, hiddenField);

        //设置服务器端以UTF-8编码输出数据到客户端
        response.setCharacterEncoding("UTF-8");
        //设置客户端浏览器以UTF-8编码解析数据
        response.setContentType("text/html;charset=UTF-8");
        //输出htmlStr里面的内容到客户端浏览器显示
        response.getWriter().write(htmlStr);
    }

    /**
     * 通过getParameterNames()方法来获取Form表单中的数据；只能获取每个字段的一个值。
     * 当获取的字段的值为多个值的时候，无法正确显示所有的值
     */

    private void displayFromByParameterNames(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置request的编码方式为UTF-8
        request.setCharacterEncoding("UTF-8");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取所有的参数名，
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            //得到参数名
            String name = paramNames.nextElement();
            //通过参数名获取对应的值
            String value = request.getParameter(name);
            response.getWriter().write(MessageFormat.format("{0}={1}", name, value));
            response.getWriter().write("<br>");
        }
    }

    /**
     * 通过request.getParameterMap()方法来获取客户端提交的参数信息
     * 该方法返回的是一个Map；
     */
    private void displayFromByParameterMap(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //request不设置编码，则中文字符显示为乱码
//        request.setCharacterEncoding("UTF-8");
        //只设置request的编码，response不设置编码，则中文字符显示为？
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");

        //request对象封装的参数是以Map的形式存储的
        Map<String, String[]> paramMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            String paramName = entry.getKey();
            String paramValue = "";
            String[] paramValueArr = entry.getValue();
            for (int i = 0; paramValueArr != null && i < paramValueArr.length; i++) {
                if (i == paramValueArr.length - 1) {
                    paramValue += paramValueArr[i];
                } else {
                    paramValue += paramValueArr[i] + ",";
                }
            }
            response.getWriter().write(MessageFormat.format("{0}={1}", paramName, paramValue));
            response.getWriter().write("<br>");
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
        displayFromByParameterMap(request,resp);
    }
}
