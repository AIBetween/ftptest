package com.gyz.pattern.chainofresponsbility;

/**
 * Created by CodeMonkey on 2016/5/4.
 */
public class husbandHandle extends AbstractHandler {
    @Override
    protected boolean isResponsible(Request request) {

        return request.getLevel() == 1;
    }

    @Override
    protected Response echo(Request request) {

        String reponseMessage = String.format("%s allow %d:%s", "busband", request.getLevel(), request.getRequestMessage());

        return new Response(reponseMessage);
    }

}
