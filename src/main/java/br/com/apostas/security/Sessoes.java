package br.com.apostas.security;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Sessoes {

    private static Sessoes sessoes;
    private HashMap<String, Sessao> hashSessoes = new HashMap<String, Sessao>();

    private Sessoes() {

    }

    public static synchronized Sessoes getInstance() {
        if (sessoes == null) {
            sessoes = new Sessoes();
        }
        return sessoes;
    }

    public static Sessao getSessao(String sessionId) {
        return getInstance().getHashSessoes().get(sessionId);
    }

    public static Sessao getSessao(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie ck : cookies) {
            if (ck.getName().toLowerCase().equals("sessionid")) {
                String value = ck.getValue().replace("%22", "");
                return getSessao(value);
            }
        }
        return null;
    }

    public HashMap<String, Sessao> getHashSessoes() {
        return hashSessoes;
    }
}
