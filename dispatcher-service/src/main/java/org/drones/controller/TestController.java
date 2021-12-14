package org.drones.controller;

import org.drones.business.DispatcherManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dispatcher")
public class TestController {
    @Autowired
    DispatcherManager dispatcherManager;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String startup() {
        dispatcherManager.fetchDrones();
        dispatcherManager.pushScheduler();
        return "OK";
    }

}
