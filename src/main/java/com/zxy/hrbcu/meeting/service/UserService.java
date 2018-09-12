package com.zxy.hrbcu.meeting.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zxy.hrbcu.meeting.constant.Constant;
import com.zxy.hrbcu.meeting.dao.TUUserDao;
import com.zxy.hrbcu.meeting.domain.TUUser;
import com.zxy.hrbcu.meeting.dto.RegisterDto;
import com.zxy.hrbcu.meeting.util.Assembler;
import com.zxy.hrbcu.meeting.util.Md5Util;
import com.zxy.hrbcu.meeting.util.UUIDTool;
import com.zxy.hrbcu.meeting.vo.ResultVo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wenxu on 2017/6/9.
 */
@Service
@Scope("prototype")
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private TUUserDao userDao;

    public List checkLoginUserName(String phone){
        Map param = Maps.newHashMap();
        param.put("phone",phone);
        List<TUUser> userList = userDao.checkLoginUserName(param);
        return userList;
    }

    /**
     * 注册
     * @param registerDto
     * @return
     */
    public ResultVo register(RegisterDto registerDto) throws Exception{
        ResultVo resultVo = new ResultVo();
        ResultVo validateResult = validateAdd(registerDto);
        if(validateResult.getStatus() == 0){
            return validateResult;
        }

        TUUser user = new TUUser();
        Assembler.assemble(registerDto,user);
        String id = UUIDTool.getUUID();
        user.setId(id);
        user.setPassword(Md5Util.encrypt(user.getPassword()));
        user.setUserType(Constant.USER_TYPE_JOIN);
        user.setCreateStaffId(id );
        user.setCreateTime(new Date());
        user.setCreateStaffId(id);
        user.setUpdateTime(new Date());
        userDao.insertSelective(user);

        resultVo.setStatus(1);
        resultVo.setMsg("注册成功");
        return resultVo;
    }

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    public TUUser getUserById(String id){
        return userDao.selectByPrimaryKey(id);
    }

    /**
     * 校验添加用户
     * @param registerDto
     * @return
     */
    private ResultVo validateAdd(RegisterDto registerDto){
        ResultVo resultVo = new ResultVo();
        if(StringUtils.isEmpty(registerDto.getUserName())){
            resultVo.setStatus(0);
            resultVo.setMsg("用户姓名必填");
            return resultVo;
        }

        if(StringUtils.isEmpty(registerDto.getPhone())){
            resultVo.setStatus(0);
            resultVo.setMsg("手机号必填");
            return resultVo;
        }

        if(registerDto.getPhone().length() != 11){
            resultVo.setStatus(0);
            resultVo.setMsg("手机号长度不对");
            return resultVo;
        }

        if(!org.apache.commons.lang.StringUtils.isNumeric(registerDto.getPhone())){
            resultVo.setStatus(0);
            resultVo.setMsg("手机号格式不对");
            return resultVo;
        }

        if(StringUtils.isEmpty(registerDto.getPassword())){
            resultVo.setStatus(0);
            resultVo.setMsg("登录密码必填");
            return resultVo;
        }

        if(!registerDto.getPassword().matches("[\\w]{6,12}")){
            resultVo.setStatus(0);
            resultVo.setMsg("登录密码格式不正确");
            return resultVo;
        }

        if(StringUtils.isEmpty(registerDto.getConfirmPassword())){
            resultVo.setStatus(0);
            resultVo.setMsg("确认登录密码必填");
            return resultVo;
        }

        if(!registerDto.getConfirmPassword().matches("[\\w]{6,12}")){
            resultVo.setStatus(0);
            resultVo.setMsg("确认登录密码格式不正确");
            return resultVo;
        }

        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            resultVo.setStatus(0);
            resultVo.setMsg("确认登录密码必须与登录密码一致");
            return resultVo;
        }

        if(StringUtils.isEmpty(registerDto.getGender())){
            resultVo.setStatus(0);
            resultVo.setMsg("性别必填");
            return resultVo;
        }

        if(StringUtils.isEmpty(registerDto.getIdNo())){
            resultVo.setStatus(0);
            resultVo.setMsg("身份证号码必填");
            return resultVo;
        }

        if(registerDto.getIdNo().length() > 18){
            resultVo.setStatus(0);
            resultVo.setMsg("身份证号码长度不对");
            return resultVo;
        }

        //校验手机号是否已经注册过
        TUUser param = new TUUser();
        param.setPhone(registerDto.getPhone());
        List<TUUser> userList = userDao.selectBySelective(param);
        if(!CollectionUtils.isEmpty(userList)){
            resultVo.setStatus(0);
            resultVo.setMsg("手机号已注册过，请直接登录。");
            return resultVo;
        }

        //校验身份证是否已经注册过
        param = new TUUser();
        param.setIdNo(registerDto.getIdNo());
        userList = userDao.selectBySelective(param);
        if(!CollectionUtils.isEmpty(userList)){
            resultVo.setStatus(0);
            resultVo.setMsg("身份证号已注册过，请联系管理员");
            return resultVo;
        }

        resultVo.setStatus(1);
        return resultVo;
    }

    public List<Map<String,String>> getUserList(){
        TUUser param = new TUUser();
        param.setUserType(Constant.USER_TYPE_JOIN);
        List<TUUser> userList = userDao.selectBySelective(param);
        List<Map<String,String>> resultList = Lists.newArrayList();
        for(TUUser user:userList){
            Map<String,String> result = Maps.newHashMap();
            result.put("userId",user.getId());
            result.put("userName",user.getUserName());
            result.put("phone",user.getPhone());
            result.put("gender",user.getGender());
            result.put("idNo",user.getIdNo());
            resultList.add(result);
        }
        return resultList;
    }

    public List<Map<String,String>> getUserListForExcel(){
        List<Map<String,String>> userList = userDao.getUserListForExcel();
        return userList;
    }



}
