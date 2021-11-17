package com.blue_b.rest.model;

public class FastFood {
    String restNameStr,timeDel;
    int imageInt;

    public FastFood(String restNameStr, String timeDel, int imageInt) {
        this.restNameStr = restNameStr;
        this.timeDel = timeDel;
        this.imageInt = imageInt;
    }

    public String getRestNameStr() {
        return restNameStr;
    }

    public void setRestNameStr(String restNameStr) {
        this.restNameStr = restNameStr;
    }

    public String getTimeDel() {
        return timeDel;
    }

    public void setTimeDel(String timeDel) {
        this.timeDel = timeDel;
    }

    public int getImageInt() {
        return imageInt;
    }

    public void setImageInt(int imageInt) {
        this.imageInt = imageInt;
    }
}
