package entity;

import java.util.List;

public class CartBean {

    public String message;
    public String status;
    public List<ResultCartBean> resultCartBean;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public List<ResultCartBean> getResultCartBean() {
        return resultCartBean;
    }

    public class ResultCartBean{
        public int commodityId;
        public String commodityName;
        public int count;
        public String pic;
        public int price;

        public boolean isChecked;//列表表示是否选中
        public int productNum = 1;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    /*public String msg;
    public String code;
    public List<DataBean> data;

    public class DataBean{
        public boolean isChecked;//一级列表表示是否选中
        public String sellerName;
        public String sellerid;
        public List<ListBean> list;

        public class ListBean{
            public boolean isProjectChecked;//二级列表表示是否选中
            public String images;
            public String title;
            public double price;
            public String pid;
            public int pos;
            public int productNum = 1;
        }
    }*/
}
