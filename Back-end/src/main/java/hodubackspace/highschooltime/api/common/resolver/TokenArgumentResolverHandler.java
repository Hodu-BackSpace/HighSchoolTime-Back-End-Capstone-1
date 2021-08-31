package hodubackspace.highschooltime.api.common.resolver;

import hodubackspace.highschooltime.api.common.annotation.TokenInfo;
import hodubackspace.highschooltime.domain.Member;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class TokenArgumentResolverHandler implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenInfo.class) && parameter.getParameterType().isAssignableFrom(Long.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        if (SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication().getName() == null) {
            throw new RuntimeException("Security Context에 유저 정보 X");
        }
        return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
