package br.com.gabrielbarbosa.agropopshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gabrielbarbosa.agropopshop.model.Dependente;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Long>{

}
