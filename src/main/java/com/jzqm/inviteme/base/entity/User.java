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
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "真实姓名")
    private String name;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "出生日期")
    private Date bornDate;

    @ApiModelProperty(value = "性别：1：男，2：女，0：未填写")
    private Integer sex;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "所在省份")
    private String province;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "所在地址")
    private String address;

    @ApiModelProperty(value = "GPS定位x坐标")
    private Double posx;

    @ApiModelProperty(value = "GPS定位y坐标")
    private Double posy;

    @ApiModelProperty(value = "工作")
    private String work;

    @ApiModelProperty(value = "学历")
    private String education;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "工作年限")
    private String workTime;

    @ApiModelProperty(value = "最后一次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "当前可用金额")
    private Double score;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "阅读前可用金额")
    private Double preScore;

    @ApiModelProperty(value = "历史累积金额（包含提现的）")
    private Double totalScore;

    @ApiModelProperty(value = "公告访问标记：0:已读，1:未读")
    private Integer isNotic;

    @ApiModelProperty(value = "用户状态：1:可用，0:冻结，2:永久封号")
    private Integer state;

    @ApiModelProperty(value = "绑定推广专员id")
    private String inviterId;

    @ApiModelProperty(value = "用户标签")
    private String label;

    @ApiModelProperty(value = "价格")
    private Double price;

    @ApiModelProperty(value = "用户基本情况")
    private String discribe;

    @ApiModelProperty(value = "身份证正面")
    private String fromImgPath;

    @ApiModelProperty(value = "身份证反面")
    private String backImgPath;

    @ApiModelProperty(value = "头像")
    private String headerImgPath;


}
