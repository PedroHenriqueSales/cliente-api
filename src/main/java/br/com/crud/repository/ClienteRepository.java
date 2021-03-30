package br.com.crud.repository;

import br.com.crud.domain.Cliente;
import br.com.crud.dto.filters.ClienteFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(" SELECT c FROM Cliente c " +
            " WHERE 0=0 " +
            " AND ( :#{#filtro.id} IS NULL OR :#{#filtro.id} = c.id ) " +
            " AND ( :#{#filtro.nome} IS NULL OR :#{#filtro.nome} = c.nome ) " +
            " AND ( coalesce(:#{#filtro.dataNascimento}, null) IS NULL OR  c.dataNascimento =  :#{#filtro.dataNascimento} ) " +
            " AND ( :#{#filtro.cpf} IS NULL OR  c.cpf = :#{#filtro.cpf} ) " )
    Page<Cliente> findByFilter(ClienteFilter filtro, Pageable pageable);

}
