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
@ApiModel(value="UserImg对象", description="")
public class UserImg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "图片保存路径")
    private String path;

    @ApiModelProperty(value = "关联用户id")
    private String userId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "图片类型：1：头像，2：生活照，3（待定）")
    private Integer type;

    @ApiModelProperty(value = "状态  0:不可用，1：可用")
    private Integer state;


}
