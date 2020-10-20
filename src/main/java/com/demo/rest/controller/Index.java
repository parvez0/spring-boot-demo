package com.demo.rest.controller;

import com.demo.rest.pojos.DataRequest;
import com.demo.rest.pojos.GenericResponse;
import com.demo.rest.pojos.HealthCheckResponse;
import com.demo.rest.models.IndexModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class Index {
    @Autowired private IndexModel indexModel;

    @GetMapping("/health-check")
    public ResponseEntity HealthCheck(){
        indexModel.AggregateData();
        HealthCheckResponse resp = new HealthCheckResponse(true, "spring server is running and waiting for connections");
        return new ResponseEntity(resp, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity PushData(@RequestBody DataRequest body){
        try{
            indexModel.WriteData(body);
            return new ResponseEntity(new GenericResponse(true, "data recorded"), HttpStatus.OK);
        }catch (Exception e){
            Map<String, Object> data = new HashMap<>();
            data.put("message", e.getMessage());
            return new ResponseEntity(new GenericResponse(false, "failed to record metrics", data), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity FetchData(@RequestParam(name = "time", defaultValue = "5m") String time){
        try{

            return new ResponseEntity(new GenericResponse(true, "data recorded"), HttpStatus.OK);
        }catch (Exception e){
            Map<String, Object> data = new HashMap<>();
            data.put("message", e.getMessage());
            return new ResponseEntity(new GenericResponse(false, "failed to record metrics", data), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
