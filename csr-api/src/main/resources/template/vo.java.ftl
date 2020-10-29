<#assign VO = table.entityName?substring(0,(table.entityName)?length-2) + "Vo">
package com.project.${package.ModuleName}.model.vo;


import com.project.${package.ModuleName}.model.po.${table.entityName};
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ${table.comment!} 信息
 * </p>
 *
 * @author ${author}
 * @version v1.0
 * @since ${date}
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class ${VO} extends ${table.entityName} implements Serializable {

}
