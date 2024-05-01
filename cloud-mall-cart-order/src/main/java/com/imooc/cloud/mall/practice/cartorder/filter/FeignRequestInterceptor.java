package com.imooc.cloud.mall.practice.cartorder.filter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 描述：     Feign请求拦截器
 */
@EnableFeignClients
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //通过RequestContextHolder获取到请求 拿到requestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }//类型转换 拿到Request
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames(); //拿到所有Header名字
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) { //如果还有新的元素 先获取
                String name = headerNames.nextElement(); //获取相关的值
                Enumeration<String> values = request.getHeaders(name);
                while (values.hasMoreElements()) {//获取到当前元素
                    String value = values.nextElement();
                    requestTemplate.header(name, value);
                }
            }
        }
    }
}
