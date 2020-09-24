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
@ApiModel(value="Shop对象", description="")
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商铺id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "商铺名称")
    private String name;

    @ApiModelProperty(value = "商铺简介")
    private String discribe;

    @ApiModelProperty(value = "商铺位置x坐标")
    private Double posx;

    @ApiModelProperty(value = "商铺位置y坐标")
    private Double posy;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "营业时间开始")
    private Date openTime;

    @ApiModelProperty(value = "营业时间截至")
    private Date closeTime;

    @ApiModelProperty(value = "关联管理者id")
    private String managerId;

    @ApiModelProperty(value = "商铺类型（待定）")
    private Integer type;

    @ApiModelProperty(value = "商铺状态（0：待审核，1：审核通过，2：停业，3：下线）")
    private Integer state;


}
