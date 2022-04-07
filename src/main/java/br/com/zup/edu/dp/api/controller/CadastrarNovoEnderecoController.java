package br.com.zup.edu.dp.api.controller;

import br.com.zup.edu.dp.api.model.Endereco;
import br.com.zup.edu.dp.api.model.Zupper;
import br.com.zup.edu.dp.api.repository.EnderecoRepository;
import br.com.zup.edu.dp.api.repository.ZupperRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastrarNovoEnderecoController {

    private final ZupperRepository zupperRepository;
    private final EnderecoRepository enderecoRepository;

    public CadastrarNovoEnderecoController(ZupperRepository zupperRepository, EnderecoRepository enderecoRepository) {
        this.zupperRepository = zupperRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    @PostMapping("/zuppers/{idZupper}/enderecos")
    public ResponseEntity<?> cadastrar(@PathVariable Long idZupper, @RequestBody @Valid EnderecoRequest request, UriComponentsBuilder uriComponentsBuilder) {

        Zupper zupper = zupperRepository.findById(idZupper)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não existe zupper cadastrado para o Id informado!"));

        Endereco novoEndereco = request.paraEndereco();

        novoEndereco.setZupper(zupper);

        enderecoRepository.save(novoEndereco);

        URI location = uriComponentsBuilder.path("/zuppers/{idZupper}/enderecos/{idEndereco}")
                .buildAndExpand(zupper.getId(), novoEndereco.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
}
