package br.com.zup.edu.dp.api.controller;

import br.com.zup.edu.dp.api.model.Zupper;
import br.com.zup.edu.dp.api.repository.ZupperRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastrarNovoZupperController {

    private final ZupperRepository zupperRepository;

    public CadastrarNovoZupperController(ZupperRepository zupperRepository) {
        this.zupperRepository = zupperRepository;
    }

    @Transactional
    @PostMapping("/zuppers")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ZupperRequest request, UriComponentsBuilder uriComponentsBuilder) {
        Zupper novoZupper = request.paraZupper();

        zupperRepository.save(novoZupper);

        URI location = uriComponentsBuilder.path("/zuppers/{id}")
                .buildAndExpand(novoZupper.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
