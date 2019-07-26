package lol.cicco.api.version.config;

import lol.cicco.api.version.annotation.ApiVersion;
import lol.cicco.api.version.condition.ApiVersionCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

@Configuration
public class WebMvcConfiguration implements WebMvcRegistrations {

    @Autowired
    private ApiVersionProperties properties;

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping() {

            @Override
            protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
                return createCondition(AnnotationUtils.findAnnotation(handlerType, ApiVersion.class));
            }

            @Override
            protected RequestCondition<?> getCustomMethodCondition(Method method) {
                return createCondition(AnnotationUtils.findAnnotation(method, ApiVersion.class));
            }

            private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion) {
                return new ApiVersionCondition(apiVersion, properties);
            }
        };
    }
}
