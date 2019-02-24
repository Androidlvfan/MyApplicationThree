package entity;

import java.util.List;

public class FindBean {
    public String message;
    public String status;
    public List<FindResultBean> result;

    public    String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public List<FindResultBean> getResult() {
        return result;
    }

    public class FindResultBean{
        public int commodityId;
        public String commodityName;
        public String masterPic;
        public int price;
        public int saleNum;

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

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
