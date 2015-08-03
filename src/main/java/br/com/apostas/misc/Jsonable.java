package br.com.apostas.misc;

import java.io.Serializable;

public class Jsonable implements Serializable {
    public String toJson() {
        return JsonConverter.toJson(this);
    }
}
