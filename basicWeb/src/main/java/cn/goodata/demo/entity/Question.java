package cn.goodata.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.beans.Transient;
import java.util.Date;

@TableName("t_test")
public class Question {
    @TableId(type= IdType.AUTO)
    private Integer id;

    private String title;

    @TableField("item_No")
    private Integer itemNo;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    public Integer getItemNo() {
           return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
