package cn.sp.utils;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Ship
 * @date 2020-08-05 15:08
 */
public class RpcRequest implements Serializable {

    private Map<String,String> headers;

    private String serviceName;

    private Object[] parameters;

    private Class<?>[] parameterTypes;

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
}
