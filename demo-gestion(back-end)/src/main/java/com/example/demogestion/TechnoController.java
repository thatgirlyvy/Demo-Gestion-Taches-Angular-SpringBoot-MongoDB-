package com.example.demogestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TechnoController {

    @Autowired
    private TechnoRepository technoRepository;

    @PostMapping("/technos")
    public Techno addTechnos(@RequestBody Techno techno) {
        return technoRepository.save(techno);
    }

    @GetMapping("/technos")
    public ResponseEntity<List<Techno>> getAllTechnos() {
        return  ResponseEntity.ok(technoRepository.findAll());
    }

    @GetMapping("/technos/{id}")
    public ResponseEntity<Techno> getTechnoById(@PathVariable(value="id") String technoId) throws ResourceNotFound{
        Techno techno = technoRepository.findById(technoId).orElseThrow(() -> new ResourceNotFound("technologie not found"));
        return  ResponseEntity.ok().body(techno);
    }

    @PutMapping("/technos/{id}")
    public ResponseEntity<Techno> updateTechno(@PathVariable(value = "id") String technoId, @RequestBody Techno technoDetails) throws ResourceNotFound{
        Techno techno = technoRepository.findById(technoId).orElseThrow(() -> new ResourceNotFound(" technologie not found"));
        techno.setTitle(technoDetails.getTitle());
        final Techno updatedTechno = technoRepository.save(techno);
        return ResponseEntity.ok(updatedTechno);
    }

    @DeleteMapping("/technos/{id}")
    public Map<String, Boolean> deleteTechno(@PathVariable(value = "id") String technoId) throws ResourceNotFound{
        Techno techno = technoRepository.findById(technoId).orElseThrow(() ->new ResourceNotFound("technology not found"));

        technoRepository.delete(techno);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

}
