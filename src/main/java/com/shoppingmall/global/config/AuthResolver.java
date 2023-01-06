package com.shoppingmall.global.config;

import com.shoppingmall.auth.domain.LoginToken;
import com.shoppingmall.auth.dto.request.UserSession;
import com.shoppingmall.auth.exception.UnauthorizedException;
import com.shoppingmall.auth.repository.LoginTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
public class AuthResolver implements HandlerMethodArgumentResolver {

    private final LoginTokenRepository loginTokenRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserSession.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);

        if (httpServletRequest == null) {
            throw new UnauthorizedException();
        }

        Cookie[] cookies = httpServletRequest.getCookies();
        String accessToken = cookies[0].getValue();

        LoginToken loginToken = loginTokenRepository.findByAccessToken(accessToken)
                .orElseThrow(UnauthorizedException::new);

        return UserSession.builder()
                .id(loginToken.getMember().getId())
                .build();
    }
}
