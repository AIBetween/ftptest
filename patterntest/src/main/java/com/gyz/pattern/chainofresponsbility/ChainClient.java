package com.gyz.pattern.chainofresponsbility;

/**
 * Created by CodeMonkey on 2016/5/4.
 */
public class ChainClient {

    private AbstractHandler headerHandler;

    public ChainClient() {

        husbandHandle husbandHandle = new husbandHandle();
        FartherHandler fartherHandler = new FartherHandler();
        husbandHandle.setNextHandler(fartherHandler);

        headerHandler = husbandHandle;
    }

    public static void main(String[] args) {

        AbstractHandler headerHandler = new ChainClient().headerHandler;

        for (int i = 0; i < 10; i++) {

            int randomNumber = (int) (Math.random() * 2 + 1);
            Request request = new Request("i want to go" + randomNumber, randomNumber);
            Response response = headerHandler.handleRequest(request);
            if (null != response) {

                System.out.println(response.getResponseMessage());
            }else {

                System.out.println("response is null!");
            }
        }
    }
}
