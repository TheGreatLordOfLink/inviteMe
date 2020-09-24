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
@ApiModel(value="VisitedRecord对象", description="")
public class VisitedRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "user_id", type = IdType.ASSIGN_UUID)
    private String userId;

    @ApiModelProperty(value = "访问日期")
    private Date loginDate;

    @ApiModelProperty(value = "首次访问时间")
    private Date firstTime;

    @ApiModelProperty(value = "最后访问时间")
    private Date lastTime;

    @ApiModelProperty(value = "访问次数")
    private Integer visitedCount;

    @ApiModelProperty(value = "访问页面标题")
    private String page;


}
