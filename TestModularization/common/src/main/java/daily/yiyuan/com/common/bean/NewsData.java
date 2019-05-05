package daily.yiyuan.com.common.bean;

import java.io.Serializable;

public class NewsData implements Serializable {
    public String book;
    public String author;

    public NewsData(String book, String author){
        this.book = book;
        this.author = author;
    }
}
