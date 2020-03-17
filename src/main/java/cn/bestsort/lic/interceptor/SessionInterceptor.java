/*
package cn.bestsort.cloud_disk.interceptor;

import cn.bestsort.cloud_disk.model.entity.User;
import cn.bestsort.cloud_disk.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 3/12/20 9:32 AM
 *//*


@Slf4j
@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length != 0) {
                for (Cookie cookie : cookies) {
                    if ("token".equals(cookie.getName())) {
                        String token = cookie.getValue();
                        //user = userService.getByToken(token);
                        if (user != null) {
                            //写入Session便于持久化登录
                            request.getSession().setAttribute("user", user);
                        }
                        break;
                    }
                }
            }
        }
        log.info("\naccount:{} \nname: {} \nlogin from: {} \nhas been view :{} \nby: {}  \nReferer from {}",
                user==null?"Customer":user.getAccount(),
                user==null?"Customer":user.getName(), IpUtil.getIpAddr(request),
                request.getRequestURI()+(request.getQueryString()==null?"":("?"+request.getQueryString())),
                request.getHeader("User-Agent"),
                request.getHeader("Referer"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) { }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) { }
}
*/
