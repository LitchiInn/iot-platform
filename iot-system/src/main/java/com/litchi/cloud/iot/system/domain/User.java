package com.litchi.cloud.iot.system.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wjhf
 * @since 2019-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    private Integer id;

    private Date createTime;

    private Date modifyTime;

    private String name;

    private String password;

    private String username;

    private String purviews;

    private Integer organId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
