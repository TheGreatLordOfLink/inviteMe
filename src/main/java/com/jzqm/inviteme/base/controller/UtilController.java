package com.jzqm.inviteme.base.controller;

import com.jzqm.inviteme.base.entity.api.ApiResult;
import com.jzqm.inviteme.common.utils.DateUtils;
import com.jzqm.inviteme.common.utils.StringUtil;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
@Api(tags = "图片上传相关接口")
@RequestMapping("/util")
@Slf4j
public class UtilController extends BaseController {

    @ApiOperation(httpMethod = "POST", value = "上传图片")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "系统错误"),
            @ApiResponse(code = 200, message = "200成功,其它为错误,返回格式：{code:0,message:'',data:object} data 属性参照下方Model",
                    response = ApiResult.class) })
    @ApiImplicitParams(value = {
    })
    @PostMapping("/uploadImg")
    @ResponseBody
    public ApiResult uploadImg( @ApiParam(name = "file", value = "file", required = true) MultipartFile file,
                                @ApiParam(name = "type", value = "type", required = false) int type){

        String folder = "./invite_me";
        String objectName = null;
        int resType = 0;
        if (null != file && !file.isEmpty())
        {
            if (file.getSize() < 1024 * 1024 * 10)
            {
                switch (type)
                {
                    case 1001:
                        break;
                    case 1002:
                        break;
                    case 1003:
                        folder = "./user";
                        break;
                    case 3001:
                        break;
                    case 4001:
                        break;
                    case 5001:
                        break;
                    case 6001:
                        break;
                    default:
                    	
                        folder = "./others";
                        break;
                }
                try {
                  //获取上传文件名,包含后缀
                    String fileName = file.getOriginalFilename();
                    System.out.println("获取上传文件名,包含后缀"+fileName);
                    String fileNewName =  StringUtil.randomString(16).toUpperCase()+ fileName.substring(fileName.lastIndexOf("."));// 采用时间戳来生成文件名
                    objectName = folder +"/"+ DateUtils.formatDate2Str(new Date(), "yyyyMM")+"/"+fileNewName;
                    
                    //生成保存文件
                    File uploadFile = new File(objectName);
                    System.out.println(uploadFile);
                    //将上传文件保存到路径
                    try {
                        file.transferTo(uploadFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                        log.error(e.getMessage());
                        return new ApiResult(ApiResult.CODE_FAIL_EXCEPTION,"error: "+ApiResult.CODE_FAIL_EXCEPTION+"   图片上传失败,请重新上传");
                    }
                    
                    return new ApiResult(ApiResult.CODE_SUCCESS,"图片上传成功",objectName);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                    log.error(e.getMessage());

                    return new ApiResult(ApiResult.CODE_FAIL_EXCEPTION,"error: "+ApiResult.CODE_FAIL_EXCEPTION+"   图片上传失败,请重新上传");
                }

            }
            else
            {
                log.info("图片太大,size="+file.getSize());
                return new ApiResult(ApiResult.CODE_FAIL_UPLOAD_SIZE,"error: "+ApiResult.CODE_FAIL_UPLOAD_SIZE+"   图片大小不可以超过10MB,请重新上传");
            }
        }
        else
        {
            log.info("空图片");
            return new ApiResult(ApiResult.CODE_FAIL_UPLOAD_EMPTY,"error: "+ApiResult.CODE_FAIL_UPLOAD_EMPTY+"  不可以上传空图片,请重新上传");
        }
    }

}
