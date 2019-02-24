package api;

public class ApiUrl {
    /**
     * 登录接口
     */
    public static final String USER_LOGIN = "http://172.17.8.100/small/user/v1/login";
    /**
     * 注册接口
     */
    public static final String USER_REG = "http://172.17.8.100/small/user/v1/register";
    /**
     * 轮播接口
     */
    public static final String GOOD_BANNER = "small/commodity/v1/bannerShow";
    /**
     * 首页商品列表
     */
    public static final String MAIN_GOOD = "http://172.17.8.100/small/commodity/v1/commodityList";
    /**
     * 购物车商品列表---【一级列表】
     */
    public static final String CART_GOOD = "http://172.17.8.100/small/order/verify/v1/findShoppingCart";
    /**
     * 圈子展示
     */
    public static final String QuanShow = "http://172.17.8.100/small/circle/v1/findCircleList";
    /**
     * 关键字搜索
     */
    public static final String FIND_SHOW = "small/commodity/v1/findCommodityByKeyword";
}
