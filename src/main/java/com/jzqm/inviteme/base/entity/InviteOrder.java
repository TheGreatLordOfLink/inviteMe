package com.jzqm.inviteme.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="InviteOrder对象", description="")
public class InviteOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "用于前端显示的订单编码16位，用时间加随机数的方式生成，yyMMddhhmmss+3位毫秒数+1位随机数 例：2009052233019879")
    private String code;

    @ApiModelProperty(value = "用户A发起邀约")
    @TableField("user_idA")
    private String userIda;

    @ApiModelProperty(value = "用户B接受邀约")
    @TableField("user_idB")
    private String userIdb;

    @ApiModelProperty(value = "订单状态：0：待支付，1:已支付待确认，2:已确认待兑现，3：已兑现待确认付款，4：已完成")
    private Integer state;

    @ApiModelProperty(value = "订单类型：0：未定义，1：相亲，2：找网红，3：找商务")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "支付时间")
    private Date payTime;

    @ApiModelProperty(value = "确认时间")
    private Date confirmTime;

    @ApiModelProperty(value = "约会开始时间")
    private Date startTime;

    @ApiModelProperty(value = "约会结束时间")
    private Date endTime;

    @ApiModelProperty(value = "确认付款时间")
    private Date finishTime;

    @ApiModelProperty(value = "关联商铺id")
    private String shopId;

    @ApiModelProperty(value = "备注信息")
    private String common;

    @ApiModelProperty(value = "订单价格")
    private Double value;

    @ApiModelProperty(value = "评价")
    private String evaluation;

    @TableLogic
    private String isDel;


}
