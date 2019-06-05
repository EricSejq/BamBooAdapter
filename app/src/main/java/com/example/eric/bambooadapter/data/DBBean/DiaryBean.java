package com.example.eric.bambooadapter.data.DBBean;

import java.io.Serializable;

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
public class DiaryBean implements Serializable {
    @Id()
    long id;

    @NameInDb("USERNAME")
    String userName;

    @NameInDb("DIARYCONTENT")
    String diaryContent;

    @NameInDb("CREATETIME")
    long createTime;

    @NameInDb("DIARYTITLE")
    String diarytitle;

    public String getUserName() {
        return userName;
    }

    public String getDiaryContent() {
        return diaryContent;
    }

    public long getCreateTime() {
        return createTime;
    }

    public String getDiarytitle() {
        return diarytitle;
    }

    public DiaryBean(long id, String userName, String diaryContent, long createTime, String diarytitle) {
        this.id = id;
        this.userName = userName;
        this.diaryContent = diaryContent;
        this.createTime = createTime;
        this.diarytitle = diarytitle;
    }

    public DiaryBean(String userName, String diaryContent, long createTime, String diarytitle) {
        this.userName = userName;
        this.diaryContent = diaryContent;
        this.createTime = createTime;
        this.diarytitle = diarytitle;
    }
}
