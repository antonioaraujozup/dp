package br.com.zup.edu.dp.api.repository;

import br.com.zup.edu.dp.api.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
