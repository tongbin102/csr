package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};

<#if enableCache>
    import org.apache.ibatis.annotations.CacheNamespace;
    import com.project.csr.cache.MybatisRedisCache;
</#if>

/**
* <p>
    * ${table.comment!} Mapper 接口
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if enableCache>
    @CacheNamespace(implementation = MybatisRedisCache.class)
</#if>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
