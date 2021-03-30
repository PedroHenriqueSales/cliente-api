package br.com.crud.dto.mapper;

import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface AbstractMapper<E, D> {

    E toEntity(D dto);

    D toDTO(E entity);

    default List<D> toListDTO(Collection<E> entity) {
        return entity == null ? null :
                entity.stream()
                        .filter(Objects::nonNull)
                        .map(this::toDTO)
                        .collect(Collectors.toList());
    }

    default List<E> toListEntity(Collection<D> dtos) {
        return dtos == null ? null :
                dtos.stream()
                        .filter(Objects::nonNull)
                        .map(this::toEntity)
                        .collect(Collectors.toList());
    }


    default Page<D> toPageDTO(Page<E> entity) {
        return entity.map(this::toDTO);
    }


}
