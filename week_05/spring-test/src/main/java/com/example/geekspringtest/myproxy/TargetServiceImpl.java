package com.example.geekspringtest.myproxy;


/**
 *
 * @author zcw
 * @date 2021/06/05 8:35 下午
 **/
public class TargetServiceImpl implements TargetService{
    @Override
    public void tagetMethod() {
        System.out.println("jdk proxy target method");
    }
}
