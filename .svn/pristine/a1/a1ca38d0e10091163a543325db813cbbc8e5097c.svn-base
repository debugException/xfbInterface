package cn.emagsoftware.utils;

import cn.emagsoftware.xfb.pojo.Customer;

public class UserHolder {
    private static final ThreadLocal<Customer> THREAD_LOCAL = new ThreadLocal<Customer>();

    public static void setUser(Customer user) {
        THREAD_LOCAL.set(user);
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }

    public static long getUserCustomerID() {
        if (null == THREAD_LOCAL.get()) {
            return 0;
        }
        return THREAD_LOCAL.get().getCustomerId();
    }

    public static String getUserCustomerName() {
        if (null == THREAD_LOCAL.get()) {
            return "";
        }
        return THREAD_LOCAL.get().getOpenNick();
    }
}
