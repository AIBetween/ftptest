package com.gyz.pattern.chainofresponsbility;

/**
 * 抽象的处理器对象。有四个方法。<br>
 *     1. 处理器模板。控制流程。
 *     2. 判断责任方法，控制是否是本处理器负责处理的。
 *     3. 本处理器具体的处理逻辑。
 *     4. 设置下一个处理器。
 * Created by CodeMonkey on 2016/5/3.
 */
public abstract class AbstractHandler {

    /**
     * 下一个处理器对象。
     */
    private AbstractHandler nextHandler;

    /**
     * templete 控制流程。
     * @return 一个封装好的回应。
     */
    public Response handleRequest(Request request) {
        if (isResponsible(request)) {

            return echo(request);
        }else {
            if (null != nextHandler) {

                return nextHandler.handleRequest(request);
            }
        }

        return null;
    }

    /**
     * 控制是否是本处理器负责处理的
     * @param request 一个请求对象
     * @return 如果是本处理器负责处理的，返回true，否则false。
     */
    protected abstract boolean isResponsible(Request request);

    /**
     * 本处理器具体的处理逻辑。
     * @param request 请求对象
     * @return 一个处理结果。
     */
    protected abstract Response echo(Request request);

    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }


}
