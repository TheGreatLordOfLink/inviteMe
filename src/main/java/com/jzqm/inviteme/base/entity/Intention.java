package com.jzqm.inviteme.base.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel(value="Intention对象", description="")
public class Intention implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "意向分类id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "邀约者视角标题")
    private String title;

    @ApiModelProperty(value = "被邀约者视角标题")
    private String myIntention;

    @ApiModelProperty(value = "描述")
    private String discribe;

    @ApiModelProperty(value = "父节点id")
    private String parentId;


}
