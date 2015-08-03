package br.com.apostas.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "sessionFilter", urlPatterns = "/app/rest/restricted/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        Boolean ok = false;
        HttpServletRequest req = (HttpServletRequest) request;

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if ("sessionId".equals(ck.getName())) {
                    if (!ck.getValue().trim().isEmpty()) {
                        String value = ck.getValue().replace("%22", "");
                        Sessoes sessoes = Sessoes.getInstance();
                        Sessao sessao = sessoes.getHashSessoes().get(value);
                        if (sessao != null) {
                            //TODO - Testar IP
                            ok = true;
                        }
                    }
                }
            }
        }
        if (ok) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void destroy() {
    }

}
