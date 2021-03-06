package com.litchi.cloud.iot.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单资源
 * </p>
 *
 * @author wjhf
 * @since 2019-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Resource extends Model<Resource> {

private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 说明
     */
    private String remark;

    /**
     * 顺序
     */
    private Integer seq;

    /**
     * URL
     */
    private String url;

    /**
     * 调用方式
     */
    private String method;

    /**
     * çˆ¶ID
     */
    private Integer pid;

    /**
     * 状态
     */
    private Integer type;

    /**
     * 页面地址
     */
    private String pageUrl;

    private String icon;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
