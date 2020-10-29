package com.project.csr.api;

import com.project.csr.model.vo.TestVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.csr.service.TestService;
import com.project.csr.utils.ConvertUtils;
import com.project.csr.model.po.TestPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bin.tong
 * @since 2020-10-29
 * @version v1.0
 */
@Slf4j
@Api(tags = {"TestApi"},value = "")
@RestController
@RequestMapping("/testApi")
public class TestApi {

    @Autowired
    private TestService testService;

    @ApiOperation(value = "查询分页数据")
    @PostMapping(value = "/findListByPage")
    public IPage<TestPo> findListByPage(@RequestBody TestVo testVo){
        return testService.findListByPage(testVo);
    }

    @ApiOperation(value = "根据id查询数据")
    @GetMapping(value = "/findById/{id}")
    public TestVo findById(@PathVariable("id") String id){
        TestPo po = testService.getById(id);
        return ConvertUtils.convert(po, TestVo.class);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/add")
    public TestVo add(@RequestBody TestVo testVo){
        TestPo po = ConvertUtils.convert(testVo, TestPo.class);
        testService.save(po);
        return ConvertUtils.convert(po, TestVo.class);
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping(value = "/delById/{id}")
    public boolean delById(@PathVariable("id") String id){
        return testService.removeById(id);
    }

    @ApiOperation(value = "更新数据")
    @PutMapping(value = "/update")
    public TestVo update(@RequestBody TestVo testVo){
        TestPo po = ConvertUtils.convert(testVo, TestPo.class);
        testService.updateById(po);
        return ConvertUtils.convert(po, TestVo.class);
    }

    @ApiOperation("根据ID禁用数据")
    @PutMapping("/prohibitById/{id}")
    public boolean prohibitById(@PathVariable String id) {
        return testService.prohibitById(id);
    }
}
