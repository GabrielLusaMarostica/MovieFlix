package br.com.movieflix.service;

import br.com.movieflix.DTO.StreamingDTO;
import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.mapper.StreamingMapper;
import br.com.movieflix.repository.CategoryRepository;
import br.com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository streamingRepository;
    private final StreamingMapper streamingMapper;

    public List<StreamingDTO> findAll(){
        List<Streaming> streamings = streamingRepository.findAll();
        List<StreamingDTO> resultado = new ArrayList<>();
        for(Streaming streaming : streamings){
            StreamingDTO dto = streamingMapper.map(streaming);
            resultado.add(dto);
        }
        return resultado;
    }

    public StreamingDTO saveStreaming(StreamingDTO streamingDTO){
        Streaming streaming = streamingMapper.map(streamingDTO);
        streamingRepository.save(streaming);
        return streamingMapper.map(streaming);
    }

    public Optional<StreamingDTO> findById(Long id){
        Optional<Streaming> streaming = streamingRepository.findById(id);
        return streaming.map(streamingMapper::map); //usa o metodo map no streaming, que é um metodo dos optional que
        // transforma o valor dentro dele caso esse valor exista. O ::map é o mesmo que streamingMapper.map(streaming). Ou seja ele vai
        // transformar o streaming em um dto caso ele seja valido
    }

    public void deleteStreaming(Long id){
        streamingRepository.deleteById(id);
    }

}
