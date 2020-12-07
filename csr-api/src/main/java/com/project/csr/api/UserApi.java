package com.project.csr.api;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.project.csr.common.enums.ResCodeEnum;
import com.project.csr.common.exceptions.GlobalException;
import com.project.csr.model.vo.UserVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.UserService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.UserPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @version v1.0
 * @since 2020-10-30
 */
@Slf4j
@Api(tags = {"UserApi"}, value = "用户表")
@RestController
@RequestMapping("/userApi")
public class UserApi {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询分页用户表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<UserPo> findListByPage(@RequestBody UserVo userVo) {
        return userService.findListByPage(userVo);
    }

    @ApiOperation(value = "根据id查询用户表数据")
    @GetMapping(value = "/findById/{id}")
    public UserVo findById(@PathVariable("id") String id) {
        UserPo po = userService.getById(id);
        return ConvertUtils.convert(po, UserVo.class);
    }

    @ApiOperation(value = "新增用户表数据")
    @PostMapping(value = "/add")
    public UserVo add(@RequestBody UserVo userVo) {
        UserPo po = ConvertUtils.convert(userVo, UserPo.class);
        userService.save(po);
        return ConvertUtils.convert(po, UserVo.class);
    }

    @ApiOperation(value = "删除用户表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id) {
        return userService.removeById(id);
    }

    @ApiOperation(value = "更新用户表数据")
    @PutMapping(value = "/update")
    public UserVo update(@RequestBody UserVo userVo) {
        UserPo po = ConvertUtils.convert(userVo, UserPo.class);
        userService.updateById(po);
        return ConvertUtils.convert(po, UserVo.class);
    }

    @ApiOperation("根据ID禁用用户表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return userService.prohibitById(id);
    }

    @ApiOperation("重置用户密码")
    @PutMapping("/resetPassword")
    public boolean resetPassword(@RequestParam("username") String username) {
        UserPo userPo = userService.findByUsername(username);
        if (null == userPo) {
            throw new GlobalException(ResCodeEnum.RESCODE_NOT_FOUND);
        } else {
            return userService.resetPassword(username);
        }
    }

    @ApiOperation("修改用户密码")
    @PutMapping("/changePassword")
    public boolean changePassword(@RequestParam("username") String username, @RequestParam("password") String password) {
        UserPo userPo = userService.findByUsername(username);
        if (null == userPo) {
            throw new GlobalException(ResCodeEnum.RESCODE_NOT_FOUND);
        } else {
            return userService.changePassword(username, password);
        }
    }
}
