package com.example.eric.bambooadapter.data.DBBean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.NameInDb;

/**
 * Description:
 * Data：2019/4/28-22:38
 *
 * @author: eric
 */
@Entity
public class DiaryBean {
    @Id()
    long id;

    @NameInDb("USERNAME")
    String userName;

    @NameInDb("DIARYCONTENT")
    String diaryContent;

    @NameInDb("CREATETIME")
    long createTime;

    public DiaryBean(long id, String userName, String diaryContent, long createTime) {
        this.id = id;
        this.userName = userName;
        this.diaryContent = diaryContent;
        this.createTime = createTime;
    }

    public DiaryBean(String userName, String diaryContent, long createTime) {
        this.userName = userName;
        this.diaryContent = diaryContent;
        this.createTime = createTime;
    }
}
