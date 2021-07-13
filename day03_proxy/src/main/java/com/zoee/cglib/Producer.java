package com.zoee.cglib;

/**
 * @Author huangSir
 * @Date 2021/7/9 22:05
 */

import com.zoee.proxy.IProducer;

/**
 * 实现接口，表明该生产商符合生产商应该有的标准
 */
public class Producer{


    public void saleProducer(float money) {
        System.out.println("拿到金额"+money+",卖出一台电脑...");
    }

    public void afterProducer(float money) {
        System.out.println("对产品进行了售后，然后得到佣金"+money);
    }
}
