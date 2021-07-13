package com.zoee.proxy;

/**
 * @Author huangSir
 * @Date 2021/7/9 23:12
 */

/**
 * 这个是一个生产商应该有的标准
 */
public interface IProducer {
    public void saleProducer(float money);
    public void afterProducer(float money);
}
