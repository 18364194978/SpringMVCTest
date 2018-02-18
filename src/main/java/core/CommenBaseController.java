package core;

import core.extjs.ExtJSBaseParameter;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.WebDataBinder;
import core.web.CustomDateEditor;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2018/2/18 0018.
 */
public abstract class CommenBaseController<E extends ExtJSBaseParameter> {
    public static final String CMD_EDIT = "edit";
    public static final String CMD_NEW = "new";
    public static final String MODEL = "model";
    protected String idField;
    protected String statusField;
    protected static final String separator = "/";
//    protected Service<E> service;
    protected static ObjectMapper mapper = new ObjectMapper();
    protected static JsonFactory factory;

    static {
        factory = mapper.getJsonFactory();
    }

    public CommenBaseController() {
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor());
    }

    protected void writeJSON(HttpServletResponse response, String json) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void writeJSON(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        JsonGenerator responseJsonGenerator = factory.createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
        responseJsonGenerator.writeObject(obj);
    }
}
