package com.example.eric.bambooadapter.data.DBBean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.NameInDb;
import io.objectbox.annotation.Unique;

/**
 * Description:
 * Data：2019/4/27-22:28
 *
 * @author: eric
 */
@Entity
public class TestBean {
    @Id
    long id;

    @Unique
    @Index
    @NameInDb("USERNAME")
    public String username;

    public TestBean(String username) {
        this.username = username;
    }

    //为了ObjectBox在构造实体时提高性能，还需要提供一个全属性构造函数
    public TestBean(long id, String username) {
        this.id = id;
        this.username = username;
    }
}
