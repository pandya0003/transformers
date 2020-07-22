package com.transformers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.transformers.model.Transformer;

/**
 * 
 * Repository for the entity Transformer
 * 
 * @author parth.pandya
 *
 */
public interface TransformerRepository extends JpaRepository<Transformer, Integer>{
	@Override
    @Query("select t from Transformer t")
    List<Transformer> findAll();
}