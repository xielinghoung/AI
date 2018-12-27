package ai.yarnpx.com.ai.entily;

import cn.bmob.v3.BmobUser;

public class UserInfo extends BmobUser {
    private String resgisterType;
    private boolean sex;
    private String jianjie;

    public String getResgisterType() {
        return resgisterType;
    }

    public void setResgisterType(String resgisterType) {
        this.resgisterType = resgisterType;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "resgisterType='" + resgisterType + '\'' +
                ", sex=" + sex +
                ", jianjie='" + jianjie + '\'' +
                '}';
    }
}