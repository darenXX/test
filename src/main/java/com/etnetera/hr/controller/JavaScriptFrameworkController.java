package com.etnetera.hr.controller;

import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Simple REST controller for accessing application logic.
 * just for local test (without db access and client-server)
 * without validation @Valid and @ResourceNotFoundException
 * no implementation class implementation for Framework for this basic calls without control,ect.
 *
 * @author Etnetera
 * @author thnil
 */
@RestController
public class JavaScriptFrameworkController {

    private final JavaScriptFrameworkRepository repository;

    @Autowired
    public JavaScriptFrameworkController(JavaScriptFrameworkRepository repository) {
        this.repository = repository;
    }

    /*
     * Get controller
     * @id id
     */
    @GetMapping("/frameworks")
    public Iterable<JavaScriptFramework> getFrameworks() {
        return repository.findAll();
    }

    /*
     * Get controller by ID
     * @id id
     */
    @GetMapping("/framework/{id}")
    public Optional<JavaScriptFramework> getFrameworksById(Long id) {
        return repository.findById(id);
    }

    /*
    * Create controller for one
    * @javaScriptFramework data
     */
    @PostMapping("/framework/create")
    public JavaScriptFramework addNewFramework(@RequestBody JavaScriptFramework javaScriptFramework) {
        return repository.save(javaScriptFramework);
    }

    /*
    * Update controller
    * @javaScriptFramework data
    * @id id
     */
    @PutMapping("/framework/update/{id}")
    public JavaScriptFramework updateFramework(@PathVariable(value = "id") Long id,
                                               @RequestBody JavaScriptFramework javaScriptFramework) {
        JavaScriptFramework jSF = repository.findById(id).orElse(null);
        assert jSF != null;
            if(javaScriptFramework.getName() != null) {
                jSF.setName(javaScriptFramework.getName());
            }
            if(javaScriptFramework.getDeprecationDate() != null) {
                jSF.setDeprecationDate(javaScriptFramework.getDeprecationDate());
            }
            if(javaScriptFramework.getHypeLevel() != null) {
                jSF.setHypeLevel(javaScriptFramework.getHypeLevel());
            }
            if(javaScriptFramework.getVersion() != null) {
                jSF.setVersion(javaScriptFramework.getVersion());
            }

        return repository.save(jSF);

    }

    /*
     * Delete by ID
     * @return response
     */
    @DeleteMapping("/framework/delete/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }

}
