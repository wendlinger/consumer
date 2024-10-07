package com.example.consumer.mappers;

import java.util.List;

public interface EntityMapper<D, E> {

    D toDTO(E entity);

    List<D> toListDTO(List<E> dtoList);

    E toEntity(D dto);

    List<E> toListEntity(List<D> entityList);
}