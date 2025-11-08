package br.com.movieflix.controller;

import br.com.movieflix.DTO.StreamingDTO;
import br.com.movieflix.service.StreamingService;
import br.com.movieflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping
    public ResponseEntity<List<StreamingDTO>> getAllStreamings() {
        return ResponseEntity.ok(streamingService.findAll());
    }

    @PostMapping
    public ResponseEntity<StreamingDTO> saveStreaming(@RequestBody StreamingDTO streamingDTO) {
        StreamingDTO streaming = streamingService.saveStreaming(streamingDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(streaming);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingDTO> getStreamingId(@PathVariable Long id) {
        Optional<StreamingDTO> optionalStreaming = streamingService.findById(id);
        if (optionalStreaming.isPresent()) {
            return ResponseEntity.ok(optionalStreaming.get());
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByStreamingId(@PathVariable Long id) {
        if (streamingService.findById(id).isPresent()) {
            streamingService.deleteStreaming(id);
            return ResponseEntity.ok("Streaming de id " + id + " deletada com sucesso");
        } else {
            return ResponseEntity.ok("O streaming de id " + id + " não foi encontrada");
        }

    }

}
