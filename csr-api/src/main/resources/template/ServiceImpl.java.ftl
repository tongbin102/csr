<#assign VO = table.entityName?substring(0,(table.entityName)?length-2) + "Vo">
<#assign Vo = (table.entityName?substring(0,(table.entityName)?length-2))?uncap_first + "Vo">
package com.project.${package.ModuleName}.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.${package.ModuleName}.dao.${table.mapperName};
import com.project.${package.ModuleName}.model.po.${entity};
import com.project.${package.ModuleName}.model.vo.${VO};
import com.project.${package.ModuleName}.service.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @version v1.0
 * @since ${date}
 */
@Service
public class ${table.serviceImplName} extends ServiceImpl<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Autowired
    private ${table.mapperName} ${table.mapperName?uncap_first};

    @Override
    public IPage<${entity}> findListByPage(${VO} ${Vo}) {
        IPage<${entity}> page = new Page<>(${Vo}.getPageNo(), ${Vo}.getPageSize());
        LambdaQueryWrapper<${entity}> wrapper = Wrappers.lambdaQuery();
        // 查询条件
        IPage<${entity}> selectPage = ${table.mapperName?uncap_first}.selectPage(page, wrapper);
        return selectPage;
    }

    @Override
    public boolean prohibitById(String id) {
        ${entity} po = new ${entity}();
        po.setValidInd(false);
        LambdaQueryWrapper<${entity}> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(${entity}::getId, id);
        return ${table.mapperName?uncap_first}.update(po, wrapper) >= 1;
    }
}

