package com.jzqm.inviteme.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zhao Jianbo
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Config对象", description="")
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "编码名")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "变量值")
    private String value;

    @ApiModelProperty(value = "备注")
    private String common;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    private String parentId;

    @ApiModelProperty(value = "排序")
    private Integer sort;


}
