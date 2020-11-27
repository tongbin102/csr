package com.project.csr.api;

import com.project.csr.model.vo.UserStoreVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.UserStoreService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.UserStorePo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户-区域/店关系表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-11-24
 * @version v1.0
 */
@Slf4j
@Api(tags = {"UserStoreApi"},value = "用户-区域/店关系表")
@RestController
@RequestMapping("/userStoreApi")
public class UserStoreApi {

    @Autowired
    private UserStoreService userStoreService;

    @ApiOperation(value = "查询分页用户-区域/店关系表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<UserStorePo> findListByPage(@RequestBody UserStoreVo userStoreVo){
        return userStoreService.findListByPage(userStoreVo);
    }

    @ApiOperation(value = "根据id查询用户-区域/店关系表数据")
    @GetMapping(value = "/findById/{id}")
    public UserStoreVo findById(@PathVariable("id") String id){
        UserStorePo po = userStoreService.getById(id);
        return ConvertUtils.convert(po, UserStoreVo.class);
    }

    @ApiOperation(value = "新增用户-区域/店关系表数据")
    @PostMapping(value = "/add")
    public UserStoreVo add(@RequestBody UserStoreVo userStoreVo){
        UserStorePo po = ConvertUtils.convert(userStoreVo, UserStorePo.class);
        userStoreService.save(po);
        return ConvertUtils.convert(po, UserStoreVo.class);
    }

    @ApiOperation(value = "删除用户-区域/店关系表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return userStoreService.removeById(id);
    }

    @ApiOperation(value = "更新用户-区域/店关系表数据")
    @PutMapping(value = "/update")
    public UserStoreVo update(@RequestBody UserStoreVo userStoreVo){
        UserStorePo po = ConvertUtils.convert(userStoreVo, UserStorePo.class);
        userStoreService.updateById(po);
        return ConvertUtils.convert(po, UserStoreVo.class);
    }

    @ApiOperation("根据ID禁用用户-区域/店关系表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return userStoreService.prohibitById(id);
    }
}
