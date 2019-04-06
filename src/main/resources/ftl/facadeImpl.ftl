package ${packagePrefix}.${moduleParent}.facade.impl;

import ${packagePrefix}.${moduleParent}.facade.${facadePrefix}Facade;
import ${packagePrefix}.${moduleParent}.model.${moduleName}.${actionName}.Request;
import ${packagePrefix}.${moduleParent}.model.${moduleName}.${actionName}.Response;
import org.springframework.stereotype.Service;

@Service
public class ${facadePrefix}FacadeImpl implements ${facadePrefix}Facade {

    @Override
    public Response ${actionName}(Request request) {
        Response response = new Response();
        return response;
    }

}
