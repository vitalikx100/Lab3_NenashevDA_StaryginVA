package com.example.Lab3_NenashevDA_StaryginVA.config;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RequiredArgsConstructor
@ControllerAdvice
public class XmlStylesheetAdvice implements ResponseBodyAdvice<Object> {

    private final XmlMapper xmlMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (body == null || selectedContentType == null) return body;

        if (selectedContentType.includes(MediaType.APPLICATION_XML)) {
            try {
                String xml = xmlMapper.writeValueAsString(body);
                String xslFile = chooseXslByPath(request.getURI().getPath());

                String pi = "<?xml-stylesheet type=\"text/xsl\" href=\"/xsl/" + xslFile + "\"?>\n";

                if (xml.startsWith("<?xml")) {
                    int endDeclaration = xml.indexOf("?>") + 2;
                    return xml.substring(0, endDeclaration) + "\n" + pi + xml.substring(endDeclaration);
                } else {
                    return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + pi + xml;
                }

            } catch (Exception e) {
                throw new HttpMessageNotWritableException("Ошибка XML сериализации", e);
            }
        }

        return body;
    }

    private String chooseXslByPath(String path) {
        if (path.matches(".*/api/users/\\d+$")) return "user-details.xsl";
        if (path.matches(".*/api/users/?$")) return "users-list.xsl";
        if (path.matches(".*/api/tasks/\\d+$")) return "task-details.xsl";
        if (path.matches(".*/api/tasks/?$")) return "tasks-list.xsl";
        if (path.matches(".*/api/users/\\d+/tasks$")) return "tasks-list.xsl";
        return "default.xsl";
    }
}