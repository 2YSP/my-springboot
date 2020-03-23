package cn.sp.component;

import cn.sp.annotation.IP;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by 2YSP on 2019/1/6.
 */
public class IPAddressArgumentResolver extends AbstractNamedValueMethodArgumentResolver {
    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        IP annotation = parameter.getParameterAnnotation(IP.class);
        return new IPAddressArgumentResolver.RequestIPNamedValueInfo(annotation);
    }

    @Nullable
    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
        String ip = servletRequest.getRemoteAddr();
        return ip == null ? "127.0.0.1":ip;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(IP.class) && !Map.class.isAssignableFrom(parameter.nestedIfOptional().getNestedParameterType());
    }

    private static class RequestIPNamedValueInfo extends NamedValueInfo{

        private RequestIPNamedValueInfo(IP annotation) {
            super(annotation.name(), annotation.required(), annotation.defaultValue());
        }
    }

}
