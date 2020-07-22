package com.transformers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.transformers.model.Team;
import com.transformers.model.Transformer;
import com.transformers.repository.TransformerRepository;
/**
 * The entrypoint class
 * 
 * @author parth.pandya
 *
 */
@SpringBootApplication
public class TransformersApplication implements CommandLineRunner {
	final private TransformerRepository tr;
	
	@Autowired
	public TransformersApplication(TransformerRepository tr) {
		super();
		this.tr = tr;
	}

	public static void main(String[] args) {
		SpringApplication.run(TransformersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		populateData();
	}
	/**
	 * 
	 * If there are not any transformer in the repo, populate initial default transformers for easy testing
	 * 
	 */
	private void populateData() {
		if(tr.count()!=0) return;
		
		Transformer tf = Transformer.builder()
				.name("Soundwave")
				.team(Team.DECEPTICON)
				.strength(8)
		        .intelligence(9)
		        .speed(2)
		        .endurance(6)
		        .rank(7)
		        .courage(5)
		        .firepower(6)
		        .skill(10)
				.build();
		tr.save(tf);
		tf = Transformer.builder()
				.name("Bluestreak")
				.team(Team.AUTOBOT)
				.strength(6)
		        .intelligence(6)
		        .speed(7)
		        .endurance(9)
		        .rank(5)
		        .courage(2)
		        .firepower(9)
		        .skill(7)
				.build();
		tr.save(tf);
		tf = Transformer.builder()
				.name("Hubcap")
				.team(Team.AUTOBOT)
				.strength(4)
		        .intelligence(4)
		        .speed(4)
		        .endurance(4)
		        .rank(4)
		        .courage(4)
		        .firepower(4)
		        .skill(4)
				.build();
		tr.save(tf);
	}
}
