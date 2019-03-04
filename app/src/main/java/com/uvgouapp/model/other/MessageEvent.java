package com.uvgouapp.model.other;

public class MessageEvent {

    public boolean flag;//true  成功进入主页面

    //1.全部刷新 6.刷新商品   7.刷新生活    3.刷新秀场
    //2.全部加载 4.加载商品   5.加载生活
    public int refreshLoad;

    public int refresh;//10  刷新淘友圈数据

    public int money;//金额

    public String outTradeNo;//订单号

}
