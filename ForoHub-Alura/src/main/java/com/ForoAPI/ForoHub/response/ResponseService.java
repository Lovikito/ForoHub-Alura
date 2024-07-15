package com.ForoAPI.ForoHub.response;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ForoAPI.ForoHub.domain.TopicRepository;
import com.ForoAPI.ForoHub.infra.security.AuthenticationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResponseService {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private TopicRepository topicRepository;
    public List<DatosResponse> getAllResponses() {
        List<Response> responses = responseRepository.findAll();
        return responses.stream()
                .map(response -> {
                    String author = (response.getResponseAuthor() != null) ? response.getResponseAuthor() : "Desconocido";
                    return new DatosResponse(
                            response.getResponseTitle(),
                            response.getBody(),
                            author,
                            response.getCreationDate());
                })
                .collect(Collectors.toList());
    }

    public DatosResponse getResponseById(Long id) {
        Response response = responseRepository.findById(id).orElse(null);
        if (response != null){
            return  new DatosResponse(
                    response.getResponseTitle(),
                    response.getBody(),
                    response.getResponseAuthor(),
                    response.getCreationDate());
        }
        return null;
    }

    public Response createResponse(Long id, DatosResponse dto) {
        var author = authenticationService.getNameAuthenticatedUser();
        if (topicRepository.findById(id).isPresent()){
        Response response = new Response();
        response.setTopic(topicRepository.getReferenceById(id));
        response.setResponseAuthor(author);
        response.setResponseTitle(dto.responseTitle());
        response.setBody(dto.body());
        response.setCreationDate(LocalDateTime.now());
        return responseRepository.save(response);
        }
        return new Response();
    }

        public Response updateResponse (Long id, DatosResponse dto){
            Optional<Response> existingResponse = responseRepository.findById(id);
            if (existingResponse.isPresent()) {
                Response newResponse = existingResponse.get();
                newResponse.setResponseTitle(dto.responseTitle());
                newResponse.setBody(dto.body());
                var author = authenticationService.getNameAuthenticatedUser();
                newResponse.setResponseAuthor(author);
                newResponse.setCreationDate(LocalDateTime.now());
                return responseRepository.save(newResponse);
            }
            throw new EntityNotFoundException("No se encontró el Topic con id: " + id);
        }

        public boolean deleteResponse (Long id){
            if (responseRepository.existsById(id)) {
                responseRepository.deleteById(id);
                return true;
            }
            return false;
        }
    }
