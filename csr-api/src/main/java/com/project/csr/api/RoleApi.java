package com.project.csr.api;

import com.project.csr.model.vo.RoleVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.RoleService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.RolePo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-30
 * @version v1.0
 */
@Slf4j
@Api(tags = {"RoleApi"},value = "角色表")
@RestController
@RequestMapping("/roleApi")
public class RoleApi {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "查询分页角色表数据")
    @PostMapping(value = "/findListByPage")
    public IPage<RolePo> findListByPage(@RequestBody RoleVo roleVo){
        return roleService.findListByPage(roleVo);
    }

    @ApiOperation(value = "根据id查询角色表数据")
    @GetMapping(value = "/findById/{id}")
    public RoleVo findById(@PathVariable("id") String id){
        RolePo po = roleService.getById(id);
        return ConvertUtils.convert(po, RoleVo.class);
    }

    @ApiOperation(value = "新增角色表数据")
    @PostMapping(value = "/add")
    public RoleVo add(@RequestBody RoleVo roleVo){
        RolePo po = ConvertUtils.convert(roleVo, RolePo.class);
        roleService.save(po);
        return ConvertUtils.convert(po, RoleVo.class);
    }

    @ApiOperation(value = "删除角色表数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return roleService.removeById(id);
    }

    @ApiOperation(value = "更新角色表数据")
    @PutMapping(value = "/update")
    public RoleVo update(@RequestBody RoleVo roleVo){
        RolePo po = ConvertUtils.convert(roleVo, RolePo.class);
        roleService.updateById(po);
        return ConvertUtils.convert(po, RoleVo.class);
    }

    @ApiOperation("根据ID禁用角色表数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return roleService.prohibitById(id);
    }
}
