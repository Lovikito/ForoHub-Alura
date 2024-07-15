package com.ForoAPI.ForoHub.response;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/response")
public class ResponseController {
    @Autowired
    private ResponseService responseService;

    @GetMapping
    public ResponseEntity<List<DatosResponse>> getAllResponses() {
        List<DatosResponse> datosResponse = responseService.getAllResponses();
        return new ResponseEntity<>(datosResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosResponse> getResponseById(@PathVariable Long id) {
        try {
        	DatosResponse datosResponse = responseService.getResponseById(id);
            return new ResponseEntity<>(datosResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/{id}")
    public ResponseEntity<Response> createResponse(@PathVariable Long id,@RequestBody DatosResponse datosResponse) {
        Response newResponse = responseService.createResponse(id, datosResponse);
        return new ResponseEntity<>(newResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateResponse(@PathVariable Long id, @RequestBody DatosResponse datosResponse) {
        try {
            Response updatedResponse = responseService.updateResponse(id, datosResponse);
            return new ResponseEntity<>(updatedResponse, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long id) {
        boolean isDeleted = responseService.deleteResponse(id);
        if (!isDeleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
