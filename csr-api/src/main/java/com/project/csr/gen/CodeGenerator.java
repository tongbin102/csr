package com.project.csr.gen;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * 执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 *
 * @author: bin.tong
 * @date: 2020/7/9 13:17
 **/
public class CodeGenerator {

    private static final String AUTHOR = "bin.tong";

    /**
     * csr配置
     */
    private static final String MODULE_NAME = "";
    public static final String BASE_PACKAGE = "com.project";
    public static final String MODULE_PACAKAGE = "csr";
    public static final String TABLE_PREFIX = "";
    public String[] table_names = {
            // "channel", "factor", "element", "factor_channel_question", "scope",
            // "element_score", "element_score_channel",
            // "question_assistance", "question_monitor", "question_survey",
            // "score",
            // "store",
            // "score_channel", "score_factor", "score_question",
            // "role", "user",
            // "specific",
            "specific_score","specific_score_channel"
    };

    private static final String JDBC_URL = "jdbc:mysql://10.55.3.4:3306/csr?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "Ghtg@2019";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    /**
     * 全局配置
     */
    private static final String JAVA_PATH = "/src/main/java";
    private static final String RESOURCES_PATH = "/src/main/resources";

    private static final String PROJECT_PATH = System.getProperty("user.dir") + "/" + MODULE_NAME;

    private static final String SERVICE_NAME = "%sService";
    private static final String ENTITY_NAME = "%sPo";
    private static final String CONTROLLER_NAME = "%sApi";
    private static final String MAPPER_NAME = "%sMapper";
    private static final String XML_NAME = "%sMapper";

    /**
     * 生成包
     */
    public static final String MODEL_PACKAGE = BASE_PACKAGE + "." + MODULE_PACAKAGE + ".model";
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + "." + MODULE_PACAKAGE + ".dao";
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + "." + MODULE_PACAKAGE + ".service";
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + "." + MODULE_PACAKAGE + ".api";

    private static final String PACKAGE_PATH_MODEL = packageConvertPath(MODEL_PACKAGE);
    private static final String PACKAGE_PATH_MAPPER = packageConvertPath(MAPPER_PACKAGE);
    private static final String PACKAGE_PATH_SERVICE = packageConvertPath(SERVICE_PACKAGE);
    private static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(SERVICE_IMPL_PACKAGE);
    private static final String PACKAGE_PATH_CONTROLLER = packageConvertPath(CONTROLLER_PACKAGE);

    /**
     * 策略配置
     */
    private static final String SUPER_ENTITY_CLASS = "com.project.csr.common.model.BasePo";

    /**
     * 模板
     */
    private static final String XML_TEMPLATE_PATH = "/template/mapper.xml.ftl";
    private static final String MAPPER_TEMPLATE_PATH = "/template/mapper.java.ftl";
    private static final String MODEL_TEMPLATE_PATH = "/template/vo.java.ftl";
    private static final String SERVICE_TEMPLATE_PATH = "/template/IService.java.ftl";
    private static final String SERVICE_IMPL_TEMPLATE_PATH = "/template/ServiceImpl.java.ftl";
    private static final String CONTROLLER_TEMPLATE_PATH = "/template/controller.java.ftl";


    public static void main(String[] args) {
        CodeGenerator cg = new CodeGenerator();
        cg.gen();
    }

    public void gen() {
        // 全局配置
        GlobalConfig gc = this.getGlobalConfig();
        // 数据源配置
        DataSourceConfig dsc = this.getDataSourceConfig();
        // 策略配置
        StrategyConfig strategy = this.getStrategyConfig();

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(gc);
        mpg.setDataSource(dsc);
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = this.getPackageConfig(mpg);
        mpg.setPackageInfo(pc);
        // 自定义配置
        InjectionConfig cfg = this.getInjectionConfig(pc);
        mpg.setCfg(cfg);
        // 模板配置
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setController(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setMapper(null);
        mpg.setTemplate(templateConfig);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();
    }

    /**
     * 全局配置
     *
     * @return
     */
    private GlobalConfig getGlobalConfig() {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(PROJECT_PATH + JAVA_PATH);
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setFileOverride(true);
        gc.setEnableCache(true);
        gc.setIdType(IdType.ID_WORKER);
        gc.setServiceName(SERVICE_NAME);
        gc.setEntityName(ENTITY_NAME);
        gc.setControllerName(CONTROLLER_NAME);
        gc.setMapperName(MAPPER_NAME);
        gc.setXmlName(XML_NAME);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        return gc;
    }

    /**
     * 数据源配置
     *
     * @return
     */
    private DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(JDBC_URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(JDBC_DIVER_CLASS_NAME);
        dsc.setUsername(JDBC_USERNAME);
        dsc.setPassword(JDBC_PASSWORD);
        return dsc;
    }

    /**
     * 策略配置
     *
     * @return
     */
    private StrategyConfig getStrategyConfig() {

        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 公共父类
        strategy.setSuperEntityClass(SUPER_ENTITY_CLASS);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id", "create_time", "creator", "update_time", "updater", "valid_ind");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setInclude(table_names);

//        strategy.setInclude("p_goods_brand", "p_goods_category", "p_goods_sku"
//                , "p_goods_sku_spec_value", "p_goods_spec_key", "p_goods_spec_value", "p_goods_spu", "p_goods_spu_spec", "p_shop_info");
        strategy.setTablePrefix(TABLE_PREFIX);
        return strategy;
    }

    /**
     * 自定义配置
     *
     * @return
     */
    private InjectionConfig getInjectionConfig(PackageConfig pc) {

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        // 自定义配置:mapper.xml
        focList.add(new FileOutConfig(XML_TEMPLATE_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return PROJECT_PATH + RESOURCES_PATH + "/mapper/" + tableInfo.getXmlName() + StringPool.DOT_XML;
            }
        });

        // 自定义配置:dao.java
        focList.add(new FileOutConfig(MAPPER_TEMPLATE_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_MAPPER + tableInfo.getMapperName() + StringPool.DOT_JAVA;
            }
        });


        // 自定义配置:xx.java
        focList.add(new FileOutConfig(MODEL_TEMPLATE_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_MODEL + "/vo/" + tableInfo.getEntityName().substring(0, tableInfo.getEntityName().length() - 2) + "Vo" + StringPool.DOT_JAVA;
            }
        });

        // 自定义配置:xxService.java
        focList.add(new FileOutConfig(SERVICE_TEMPLATE_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + tableInfo.getServiceName() + StringPool.DOT_JAVA;
            }
        });

        // 自定义配置:xxServiceImpl.java
        focList.add(new FileOutConfig(SERVICE_IMPL_TEMPLATE_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
            }
        });

        // 自定义配置:controller.java
        focList.add(new FileOutConfig(CONTROLLER_TEMPLATE_PATH) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_CONTROLLER + tableInfo.getControllerName() + StringPool.DOT_JAVA;
            }
        });

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 包配置
     *
     * @param mpg
     * @return
     */
    private PackageConfig getPackageConfig(AutoGenerator mpg) {
        PackageConfig pc = new PackageConfig();
        StrategyConfig strategy = mpg.getStrategy();

        String parent = "";
        if (strategy.getTablePrefix()[0].equals(TABLE_PREFIX)) {
            parent = MODULE_PACAKAGE;
        }
        pc.setParent(BASE_PACKAGE);
        pc.setModuleName(parent);
        pc.setEntity("model.po");
        pc.setController("api");
        pc.setMapper("dao");
        return pc;
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

}
