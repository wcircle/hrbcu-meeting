package com.zxy.hrbcu.meeting.service;

import com.zxy.hrbcu.meeting.domain.TUUser;
import com.zxy.hrbcu.meeting.util.Md5Util;
import com.zxy.hrbcu.meeting.util.SpringSessionWebUtil;
import com.zxy.hrbcu.meeting.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wenxu on 2017/6/9.
 */
@Service
@Scope("prototype")
public class LoginService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    public ResultVo loginOut(HttpServletRequest request) throws Exception {
        ResultVo resultVo = new ResultVo();
        resultVo.setStatus(1);
        resultVo.setMsg("退出成功");
        //删除缓存
        SpringSessionWebUtil.removeSessionAttribute("token_id");
        SpringSessionWebUtil.removeSessionAttribute("userVo");
        return resultVo;
    }

    public ResultVo login(String phone, String password,String strCode,HttpServletRequest request) throws Exception {
        ResultVo resultVo = new ResultVo();
        List<TUUser> userList = userService.checkLoginUserName(phone);

        if (null == userList || userList.size() == 0) {
            resultVo.setStatus(0);
            resultVo.setMsg("手机号错误，请重新输入！");
            return resultVo;
        } else if (userList.size() != 1) {
            resultVo.setStatus(0);
            resultVo.setMsg("同一个手机号存在多个，请联系管理员！");
            return resultVo;
        }
        TUUser user = userList.get(0);
        if (!user.getPassword().equals(Md5Util.encrypt(password))) {
            resultVo.setStatus(0);
            resultVo.setMsg("密码错误，请重新输入！");
            return resultVo;
        }

        String strTempCode = request.getSession().getAttribute("strCode")+"";

        if(StringUtils.isEmpty(strCode)){
            resultVo.setStatus(0);
            resultVo.setMsg("请输入验证码!");
            return resultVo;
        }

        if(!strCode.equals(strTempCode)){
            resultVo.setStatus(0);
            resultVo.setMsg("验证码错误，请重新输入！");
            return resultVo;
        }

        //登录成功 删除strcode
        request.getSession().removeAttribute("strCode");

        user.setPassword("");
        user.setCreateStaffId(null);
        user.setCreateTime(null);
        user.setUpdateStaffId(null);
        user.setUpdateTime(null);
        SpringSessionWebUtil.setSessionAttribute("token_id", user.getId());
        SpringSessionWebUtil.setSessionAttribute("userVo", user);
        resultVo.setStatus(1);
        resultVo.setMsg("登录成功");
        resultVo.setData(user.getUserType());
        return resultVo;
    }

}
