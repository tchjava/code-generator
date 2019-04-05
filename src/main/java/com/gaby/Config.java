package com.gaby;

import org.apache.commons.lang3.StringUtils;

public class Config {
    /**
     * 动作名称
     */
    private String actionName;
    /**
     * 模块名称
     */
    private String moduleName;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 包名前缀
     */
    private String packagePrefix;
    /**
     * 项目前缀路径
     */
    private String packagePrefixPath;
    /**
     * 当前加载模版的位置
     */
    private String currentLoadTemplatePath;
    /**
     * 模块的父模块
     */
    private String moduleParent;
    /**
     * facade接口的前缀，取模块名称，如果有/，分割/将后面的单词首字母大写拼接
     */
    private String facadePrefix;
    /**
     * 项目真实路径
     */
    private String projectRealPath;
    /**
     * facade层项目的项目名
     */
    private String moduleNameFacade;
    /**
     * ctr层项目的项目名
     */
    private String moduleNameCtr;
    /**
     * api层项目的项目名
     */
    private String moduleNameApi;
    /**
     * 控制层包的路径
     */
    private String packageCtrPath;

    /**
     * 定义接口
     * @return
     */
    private String refs;

    public String getModuleNameApi() {
        return moduleNameApi;
    }

    public void setModuleNameApi(String moduleNameApi) {
        this.moduleNameApi = moduleNameApi;
    }

    public String getRefs() {
        if(null==refs){
            throw new RuntimeException("定义接口为空");
        }
        return refs;
    }

    public void setRefs(String refs) {
        this.refs = refs;
    }

    public String getPackagePrefixPath() {
        return packagePrefixPath;
    }

    public void setPackagePrefixPath(String packagePrefixPath) {
        this.packagePrefixPath = packagePrefixPath;
    }

    public String getPackageCtrPath() {
        StringBuffer sb = new StringBuffer();
        sb.append(packagePrefix)
                .append(".")
                .append(moduleParent)
                .append(".")
                .append("controller");
        return replacePoint2Sprit(sb.toString());
    }

    public String getPackageApiPath() {
        StringBuffer sb = new StringBuffer();
        sb.append(packagePrefix)
                .append(".")
                .append(moduleParent)
                .append(".")
                .append("model")
                .append(".")
                .append(moduleName)
                .append(".")
                .append(actionName);
        return replacePoint2Sprit(sb.toString());
    }

    public String getPackageFacadePath() {
        StringBuffer sb = new StringBuffer();
        sb.append(packagePrefix)
                .append(".")
                .append(moduleParent)
                .append(".")
                .append("facade");
        return replacePoint2Sprit(sb.toString());
    }

    public String getPackageServicePath() {
        StringBuffer sb = new StringBuffer();
        sb.append(packagePrefix)
                .append(".")
                .append(moduleParent)
                .append(".")
                .append("service");
        return replacePoint2Sprit(sb.toString());
    }

    public String getPackageMapperPath() {
        StringBuffer sb = new StringBuffer();
        sb.append(packagePrefix)
                .append(".")
                .append(moduleParent)
                .append(".")
                .append("mapper")
                .append(".")
                .append("dao");
        return replacePoint2Sprit(sb.toString());
    }

    public String getPackageMapperXmlPath() {
        StringBuffer sb = new StringBuffer();
        sb.append(packagePrefix)
                .append(".")
                .append(moduleParent)
                .append(".")
                .append("mapper")
                .append(".")
                .append("xml");
        return replacePoint2Sprit(sb.toString());
    }
    public void setPackageCtrPath(String packageCtrPath) {
        this.packageCtrPath = packageCtrPath;
    }

    public String getModuleNameCtr() {
        return moduleNameCtr;
    }

    public void setModuleNameCtr(String moduleNameCtr) {
        this.moduleNameCtr = moduleNameCtr;
    }

    public String getModuleNameFacade() {
        return moduleNameFacade;
    }

    public void setModuleNameFacade(String moduleNameFacade) {
        this.moduleNameFacade = moduleNameFacade;
    }

    public String getProjectRealPath() {
        return projectRealPath;
    }

    public void setProjectRealPath(String projectRealPath) {
        this.projectRealPath = projectRealPath;
    }

    public String getFacadePrefix() {
        if(null==refs){
            throw new RuntimeException("定义接口不允许为空");
        }
        return firstCapital(refs);
    }

    public void setFacadePrefix(String facadePrefix) {
        this.facadePrefix = facadePrefix;
    }

    public String getModuleParent() {
        return moduleParent;
    }

    public void setModuleParent(String moduleParent) {
        this.moduleParent = moduleParent;
    }

    public String getCurrentLoadTemplatePath() {
        return currentLoadTemplatePath;
    }

    public void setCurrentLoadTemplatePath(String currentLoadTemplatePath) {
        this.currentLoadTemplatePath = currentLoadTemplatePath;
    }



    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPackagePrefix() {
        return packagePrefix;
    }

    public void setPackagePrefix(String projectPrefix) {
        this.packagePrefix = projectPrefix;
    }

    private String replacePoint2Sprit(String key){
        if(StringUtils.isNotEmpty(key) && key.indexOf(".")!=-1){
            return StringUtils.replaceChars(key, ".", "/") + "/";
        }
        return key;
    }
    public String firstCapital(String key,boolean flag){
        if(key.indexOf("/")!=-1){
            StringBuffer sb = new StringBuffer();
            String[] split = key.split("/");
            int index=0;
            for(String keyStr:split){
                if(index==0){
                    if(flag){
                        sb.append(StringUtils.capitalize(keyStr));
                    }else{
                        sb.append(keyStr);
                    }
                }else{
                    sb.append(StringUtils.capitalize(keyStr));
                }
                //处理key
                index++;
            }
            return sb.toString();
        }else{
            if(null!=key){
                return flag==true?StringUtils.capitalize(key):key;
            }
        }
        return key;
    }
    public String firstCapital(String key){
        return firstCapital(key, true);
    }
}
