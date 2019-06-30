package com.merito.freememberui;

import com.merito.multibrand.BrandContext;
import com.merito.wl.api.WhiteLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DomainCheckInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private WhiteLabelService whiteLabelService;

    @Autowired
    protected BrandContext brandContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getServletPath().equals("/")){
            boolean isAllow = false;
            String isRedirect = request.getParameter("redirect");
            if(brandContext.isWhiteLabel()) {
                isAllow = whiteLabelService.writeDomainReferer(request, response, whiteLabelService.getDomainWhiteList(request.getServerName()));
            }
            if(!isAllow && !"true".equalsIgnoreCase(isRedirect)){
                whiteLabelService.clearCookie(request, response);
            }
        }

        return super.preHandle(request, response, handler);
    }
}
