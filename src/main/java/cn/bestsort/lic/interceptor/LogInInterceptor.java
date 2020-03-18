package cn.bestsort.lic.interceptor;

import cn.bestsort.lic.handler.CacheStoreHandler;
import cn.bestsort.lic.model.entity.User;
import cn.bestsort.lic.model.enums.propertys.PrimaryProperty;
import cn.bestsort.lic.utils.IpUtil;
import cn.bestsort.lic.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 3/12/20 9:32 AM
 */


@Slf4j
@Component
public class LogInInterceptor implements HandlerInterceptor {
    @Resource
    private CacheStoreHandler cacheStoreHandler;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
        if (isLogin == null || !isLogin) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length != 0) {
                for (Cookie cookie : cookies) {
                    if ("code".equals(cookie.getName())) {
                        String account = (String) request.getSession().getAttribute(PrimaryProperty.USER_ACCOUNT.getValue());
                        String password = (String) request.getSession().getAttribute(PrimaryProperty.USER_PASSWORD.getValue());
                        String token = cookie.getValue();
                        if (token.equals(UserUtil.isUserOwn(account, password, cacheStoreHandler))) {
                            //写入Session便于持久化登录
                            request.getSession().setAttribute("isLogin", true);
                        }
                        break;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) { }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) { }
}

