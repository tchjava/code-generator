package com.gaby;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Generator {
    public static void main(String[] args) throws IOException, TemplateException {
        Config config=loadConfig();
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setDefaultEncoding("utf-8");
        configuration.setDirectoryForTemplateLoading(new File(config.getCurrentLoadTemplatePath()));
        //创建ctroller层
        createController(config,configuration);
        //创建api层
        createApi(config,configuration);
        //创建facade层
        createFacade(config, configuration);
        //创建service层
        createService(config, configuration);
        //创建mapper层
        createMapper(config, configuration);
        System.out.println("----执行完毕----");
    }

    /**
     * 创建mapper层
     * @param config
     * @param configuration
     */
    private static void createMapper(Config config, Configuration configuration) {
        Template templateMapper = null;
        Template templateMapperXml = null;
        try {
            templateMapper = configuration.getTemplate("mapper.ftl");
            templateMapperXml = configuration.getTemplate("mapperxml.ftl");
        } catch (IOException e) {
            throw new RuntimeException("创建Service层模版失败");
        }
        Map map = new HashMap();
        map.put("packagePrefix", config.getPackagePrefix());
        map.put("moduleParent", config.getModuleParent());
        map.put("facadePrefix", config.getFacadePrefix());

        //最终输出位置
        String mapperPath = config.getProjectRealPath() + "/" + config.getProjectName() + "/" + config.getModuleNameFacade()
                + "/src/main/java/" + config.getPackageMapperPath();

        String xmlPath = config.getProjectRealPath() + "/" + config.getProjectName() + "/" + config.getModuleNameFacade()
                + "/src/main/java/" + config.getPackageMapperXmlPath();
        //接口的位置
        File fileMapper = new File(mapperPath);
        if(!fileMapper.exists()){
            fileMapper.mkdirs();
        }
        //实现类的位置
        File fileMapperXml = new File(xmlPath);
        if(!fileMapperXml.exists()){
            fileMapperXml.mkdirs();
        }

        try {
            templateMapper.process(map,new FileWriter(new File(fileMapper,String.format("%sDao.java",config.getFacadePrefix()))));
            templateMapperXml.process(map,new FileWriter(new File(fileMapperXml,String.format("%sMapper.xml",config.getFacadePrefix()))));
            System.out.println("----执行mapper层完毕----");
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建service层
     * @param config
     * @param configuration
     */
    private static void createService(Config config, Configuration configuration) {
        Template templateService = null;
        Template templateServiceImpl = null;
        try {
            templateService = configuration.getTemplate("service.ftl");
            templateServiceImpl = configuration.getTemplate("serviceImpl.ftl");
        } catch (IOException e) {
            throw new RuntimeException("创建Service层模版失败");
        }
        Map map = new HashMap();
        map.put("packagePrefix", config.getPackagePrefix());
        map.put("moduleParent", config.getModuleParent());
        map.put("moduleName", config.getModuleName());
        map.put("actionName", config.getActionName());
        map.put("facadePrefix", config.getFacadePrefix());

        //最终输出位置
        String outPath = config.getProjectRealPath() + "/" + config.getProjectName() + "/" + config.getModuleNameFacade()
                + "/src/main/java/" + config.getPackageServicePath();
        //接口的位置
        File file = new File(outPath);
        if(!file.exists()){
            file.mkdirs();
        }
        //实现类的位置
        File fileImpl=new File(file,"impl");
        if(!fileImpl.exists()){
            fileImpl.mkdirs();
        }
        try {
            templateService.process(map,new FileWriter(new File(file,String.format("%sService.java",config.getFacadePrefix()))));
            templateServiceImpl.process(map,new FileWriter(new File(fileImpl,String.format("%sServiceImpl.java",config.getFacadePrefix()))));
            System.out.println("----执行service层完毕----");
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建facade层
     * @param config
     * @param configuration
     */
    private static void createFacade(Config config, Configuration configuration) {
        Template templateFacade = null;
        Template templateFacadeImpl = null;
        try {
            templateFacade = configuration.getTemplate("facade.ftl");
            templateFacadeImpl = configuration.getTemplate("facadeImpl.ftl");
        } catch (IOException e) {
            throw new RuntimeException("创建Facade层模版失败");
        }
        Map map = new HashMap();
        map.put("packagePrefix", config.getPackagePrefix());
        map.put("moduleParent", config.getModuleParent());
        map.put("moduleName", config.getModuleName());
        map.put("actionName", config.getActionName());
        map.put("facadePrefix", config.getFacadePrefix());

        //最终输出位置
        String outPath = config.getProjectRealPath() + "/" + config.getProjectName() + "/" + config.getModuleNameFacade()
                + "/src/main/java/" + config.getPackageFacadePath();
        //接口的位置
        File file = new File(outPath);
        if(!file.exists()){
            file.mkdirs();
        }
        //实现类的位置
        File fileImpl=new File(file,"impl");
        if(!fileImpl.exists()){
            fileImpl.mkdirs();
        }

        try {
            templateFacade.process(map,new FileWriter(new File(file,String.format("%sFacade.java",config.getFacadePrefix()))));
            templateFacadeImpl.process(map,new FileWriter(new File(fileImpl,String.format("%sFacadeImpl.java",config.getFacadePrefix()))));
            System.out.println("----执行Facade层完毕----");
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建api层
     * @param config
     * @param configuration
     */
    private static void createApi(Config config, Configuration configuration) {
        Template templateRequest = null;
        Template templateResponse = null;
        try {
            templateRequest = configuration.getTemplate("request.ftl");
            templateResponse = configuration.getTemplate("response.ftl");
        } catch (IOException e) {
            throw new RuntimeException("创建Api层模版失败");
        }
        Map map = new HashMap();
        map.put("packagePrefix", config.getPackagePrefix());
        map.put("moduleParent", config.getModuleParent());
        map.put("moduleName", config.getModuleName());
        map.put("actionName", config.getActionName());

        //最终输出位置
        String outPath = config.getProjectRealPath() + "/" + config.getProjectName() + "/" + config.getModuleNameApi()
                + "/src/main/java/" + config.getPackageApiPath();
        File file = new File(outPath);
        if(!file.exists()){
            file.mkdirs();

        }
        try {
            templateRequest.process(map,new FileWriter(new File(file,"Request.java")));
            templateResponse.process(map,new FileWriter(new File(file,"Response.java")));
            System.out.println("----执行api层完毕----");
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 创建控制层
     * @param config
     * @param configuration
     */
    private static void createController(Config config, Configuration configuration) {
        Template template = null;
        try {
            template = configuration.getTemplate("controller.ftl");
        } catch (IOException e) {
            throw new RuntimeException("创建控制层模版失败");
        }
        Map map = new HashMap();
        map.put("packagePrefix", config.getPackagePrefix());
        map.put("moduleParent", config.getModuleParent());
        map.put("moduleName", config.getModuleName());
        map.put("actionName", config.getActionName());
        map.put("ctrPrefix", config.getFacadePrefix());
        map.put("ctrPrefix_firstLower", StringUtils.uncapitalize(config.getFacadePrefix()));
        map.put("facadePrefix", config.getFacadePrefix());
        map.put("facadePrefix_firstLower",StringUtils.uncapitalize(config.getFacadePrefix()));
        map.put("refs", config.getRefs());

        //最终输出位置
        String outPath =config.getProjectRealPath()+"/" + config.getProjectName() + "/" + config.getModuleNameCtr()
                + "/src/main/java/" + config.getPackageCtrPath();
        File file = new File(outPath);
        if(!file.exists()){
            file.mkdirs();

        }
        //rr
        String destFilePath = outPath + config.firstCapital(config.getRefs()) + "Controller.java";
        System.out.println(config.firstCapital(config.getRefs()));
        System.out.println(destFilePath);
        try {
            template.process(map,new FileWriter(destFilePath));
            System.out.println("----执行ctrl层完毕----");
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载配置文件到config对象中
     */
    private static Config loadConfig() {
        Properties properties = new Properties();
        try {
            properties.load(Generator.class.getResourceAsStream("/generator.properties"));
            Config config = new Config();
            if(StringUtils.isEmpty( properties.getProperty("current.load.template.path"))){
                throw new RuntimeException("current.load.template.path为空!");
            }
            config.setCurrentLoadTemplatePath(properties.getProperty("current.load.template.path"));
            config.setActionName(properties.getProperty("action.name"));
            config.setProjectRealPath(properties.getProperty("project.real.path"));
            config.setProjectName(properties.getProperty("project.name"));
            config.setModuleNameCtr(properties.getProperty("module.name.ctr"));
            config.setModuleNameApi(properties.getProperty("module.name.api"));
            config.setModuleNameFacade(properties.getProperty("module.name.facade"));
            config.setPackagePrefix(properties.getProperty("package.prefix"));
            //todo 设置config值
            config.setModuleParent(properties.getProperty("module.parent"));
            config.setModuleName(properties.getProperty("module.name"));
            config.setRefs(properties.getProperty("refs"));
            return config;
        } catch (IOException e) {
            return null;
        }
    }
}
