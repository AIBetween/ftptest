package com.gyz.pattern.chainofresponsbility;

/**
 * Created by CodeMonkey on 2016/5/4.
 */
public class FartherHandler extends AbstractHandler {

    @Override
    protected boolean isResponsible(Request request) {

        return request.getLevel() == 2;
    }

    @Override
    protected Response echo(Request request) {

        String reponseMessage = String.format("%s allow %d:%s", "farther", request.getLevel(), request.getRequestMessage());

        return new Response(reponseMessage);
    }
}
