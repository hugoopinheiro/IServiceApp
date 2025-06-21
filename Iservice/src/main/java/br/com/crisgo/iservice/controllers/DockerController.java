package br.com.crisgo.iservice.controllers;

import br.com.crisgo.iservice.environment.InstanceFormationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.crisgo.iservice.models.HelloDocker;
@RequestMapping("api/v1/docker")
public class DockerController {
    Logger logger = LoggerFactory.getLogger(DockerController.class);

    @Autowired
    private InstanceFormationService service;
    @GetMapping(path = "/")
    public String imUpAndRunning(){return "{healthy: true}";}
    @RequestMapping("/hello-docker")
    public HelloDocker greeting(){
        logger.info("endpoint /hello-docker is called");

        return new HelloDocker(
                "Hello Docker - v1",
                service.retrieveInstanceInfo()
        );
    }
}
