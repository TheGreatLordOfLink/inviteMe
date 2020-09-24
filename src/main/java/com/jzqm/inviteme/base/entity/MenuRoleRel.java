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
@ApiModel(value="MenuRoleRel对象", description="")
public class MenuRoleRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.ASSIGN_UUID)
    private String roleId;

    private String menuId;


}
