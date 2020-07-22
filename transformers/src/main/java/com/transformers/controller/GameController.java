package com.transformers.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.transformers.model.GameResult;
import com.transformers.model.Transformer;
import com.transformers.service.GameService;

/**
 * Controller
 * 
 * @author parth.pandya
 */
@RestController
@RequestMapping("/transformers")
public class GameController {
	@Autowired
	private GameService gameService;
	
	/**
	 * 
	 * Create a Transformer
	 * 
	 * @param transformer
	 * @return
	 */
	@PostMapping
	@ResponseBody
	public ResponseEntity<Transformer> addNew(@RequestBody @Valid Transformer transformer) {
		transformer.setId(null);
		Transformer addedTransformer=gameService.addNew( transformer );
		
		return ResponseEntity.status(HttpStatus.CREATED).body(addedTransformer);
	}

	/**
	 * Update the transformer with id
	 *  
	 * @param id
	 * @param transformer
	 * @return
	 */
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Transformer> update(@PathVariable(required=true) final int id, 
			@RequestBody @Validated Transformer transformer) {
		transformer.setId(id);
		Transformer updatedTransformer=gameService.update( transformer );
		return ResponseEntity.status(HttpStatus.CREATED).body(transformer);
	}

	/**
	 * Delete the transformer with id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity delete(@PathVariable(required = true) final int id) {
		gameService.delete( id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * 
	 * Return all transformers
	 *  
	 * @return
	 */
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Transformer>> listAll() {
		
		List<Transformer> transformerList = gameService.listAll( );
		return ResponseEntity.status(HttpStatus.OK).body(transformerList);
	}

	/**
	 * 
	 * Given a list of Transformer IDs, determine the winning team
	 *  
	 * @param tranformerList
	 * @return
	 */
	@PostMapping("/play")
	@ResponseBody
	public ResponseEntity<GameResult> play(@RequestBody List<Integer> tranformerList) {
		
		GameResult res = gameService.play( tranformerList);
		
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
}
