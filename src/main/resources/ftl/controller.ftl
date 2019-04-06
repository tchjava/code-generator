package ${packagePrefix}.${moduleParent}.controller;

import com.gaby.web.plugin.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ${packagePrefix}.${moduleParent}.model.${moduleName}.${actionName}.Request;
import ${packagePrefix}.${moduleParent}.model.${moduleName}.${actionName}.Response;
import ${packagePrefix}.${moduleParent}.facade.${facadePrefix}Facade;

@RestController
@RequestMapping("/${refs}")
public class ${ctrPrefix}Controller extends BaseController {

    @Autowired
    private ${facadePrefix}Facade ${facadePrefix_firstLower}Facade;

    @RequestMapping("${actionName}")
    public Response ${actionName}(@RequestBody Request request){
        return ${ctrPrefix_firstLower}Facade.${actionName}(request);
    }


}