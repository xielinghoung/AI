package ai.yarnpx.com.ai.entily;

import cn.bmob.v3.BmobObject;

public class DidiOoder extends BmobObject {

    private String oderId;
    private String oderName;
    private String oderClassification;
    private String oderSource;

    public String getOderId() {
        return oderId;
    }

    public void setOderId(String oderId) {
        this.oderId = oderId;
    }

    public String getOderNmae() {
        return oderName;
    }

    public void setOderNmae(String oderNmae) {
        this.oderName = oderNmae;
    }

    public String getOderClassification() {
        return oderClassification;
    }

    public void setOderClassification(String oderClassification) {
        this.oderClassification = oderClassification;
    }

    public String getOderSource() {
        return oderSource;
    }

    public void setOderSource(String oderSource) {
        this.oderSource = oderSource;
    }

    @Override
    public String toString() {
        return "DidiOoder{" +
                "oderId='" + oderId + '\'' +
                ", oderNmae='" + oderName + '\'' +
                ", oderClassification='" + oderClassification + '\'' +
                ", oderSource='" + oderSource + '\'' +
                '}';
    }
}
