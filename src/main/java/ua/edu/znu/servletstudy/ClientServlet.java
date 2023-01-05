package ua.edu.znu.servletstudy;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Servlet that shows information about client.
 */
@WebServlet(name = "ClientServlet",
        urlPatterns = {"/ClientServlet"})
public class ClientServlet extends HttpServlet {
    private TemplateEngine templateEngine;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.templateEngine = (TemplateEngine) getServletContext()
                .getAttribute(ThymeleafConfiguration.TEMPLATE_ENGINE_ATR);
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {
        WebContext context = getWebContext(request, response);
        templateEngine.process("client", context, response.getWriter());
        response.setContentType("text/html;charset=UTF-8");
    }
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {
        WebContext context = getWebContext(request, response);
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String age = request.getParameter("age");
        String[] tours = request.getParameterValues("tours");
        templateEngine.process("clientresult", context, response.getWriter());
        response.setContentType("text/html;charset=UTF-8");

        /* Print all parameters names and their values */
        PrintWriter out = response.getWriter();
        Map m = request.getParameterMap();
        Set s = m.entrySet();
        Iterator it = s.iterator();

        while(it.hasNext()){
            Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>)it.next();
            String key = entry.getKey();
            String[] value = entry.getValue();
            if(value.length>1){
                out.println(key + ":<br>");
                for (int i = 0; i < value.length; i++) {
                    out.println("<li>" + value[i] + "</li><br>");
                }
            }else
                out.println(key + ": " + value[0] + "<br>");
        }
        out.close();
    }

    private WebContext getWebContext(HttpServletRequest request,
                                     HttpServletResponse response) {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);
        WebContext context = new WebContext(webExchange);
        return context;
    }

    @Override
    public String getServletInfo() {
        return "Servlet that shows information about client.";
    }
}