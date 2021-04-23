package cn.goodata.demo.util;

public class Constants {

    public static final Boolean isLoginFailureLock =true;
    public static final Integer loginFailureLockCount =5 ;// 同一账号允许连续登录失败的最大次数,超出次数后将锁定其账号
    public static final Integer loginFailureLockTime =5;// 账号锁定时间(单位: 分钟,0表示永久锁定)


}
