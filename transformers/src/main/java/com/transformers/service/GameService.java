package com.transformers.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transformers.model.GameResult;
import com.transformers.model.Team;
import com.transformers.model.Transformer;
import com.transformers.repository.TransformerRepository;

@Service
public class GameService {

	final private TransformerRepository transformerRepository;

	@Autowired
	public GameService(TransformerRepository transformerRepository) {
		super();
		this.transformerRepository = transformerRepository;
	}

	/**
	 * Add new transformer The id in the input transformer is ignored. The returned
	 * transformer includes new genertaded id.
	 * 
	 * @param transformer
	 * @return
	 */
	public Transformer addNew(Transformer transformer) {
		transformer.setId(null);
		return transformerRepository.save(transformer);
	}

	/**
	 * Update the transformer. If the input transformer don't exist, throws the
	 * TransformerNotFoundException.
	 * 
	 * @param transformer
	 * @return
	 */
	public Transformer update(Transformer transformer) {

		transformerRepository.findById(transformer.getId())
				.orElseThrow(() -> new TransformerNotFoundException(transformer.getId()));
		return transformerRepository.save(transformer);
	}

	/**
	 * Delete the transformer with the input id. If the input transformer don't
	 * exist, throws the TransformerNotFoundException.
	 *
	 * @param id
	 */
	public void delete(int id) {
		Transformer transformer = transformerRepository.findById(id)
				.orElseThrow(() -> new TransformerNotFoundException(id));
		transformerRepository.delete(transformer);
	}

	/**
	 * Return all transformers
	 * 
	 * @return
	 */
	public List<Transformer> listAll() {
		return new ArrayList<Transformer>(transformerRepository.findAll());
	}

	/**
	 * Determines the result of game based on the given list of Transformer IDs,
	 * 
	 * @param tranformerIds
	 * @return
	 */
	public GameResult play(List<Integer> tranformerIds) {

//		Determining winning team:
//			Your API should take as input a list of Transformer IDs and based on input returns:
//			1. The number of battles
//			2. The winning team
//			3. The surviving members of the losing team

		// The variables for result
		GameResult res = new GameResult();

		int battles = 0;
		List<Transformer> losersA = new ArrayList<>();
		List<Transformer> losersD = new ArrayList<>();
		boolean allDestroyed = false;

//		The basic rules of the battle are:
//		● The transformers are split into two teams based on if they are Autobots or Decepticons
//		● The teams should be sorted by rank and faced off one on one against each other in
//		order to determine a victor, the loser is eliminated.

		SortedSet<Transformer> tfListA = new TreeSet<>();
		SortedSet<Transformer> tfListD = new TreeSet<>();
		tranformerIds.stream()
				.map(id -> transformerRepository.findById(id).orElseThrow(() -> new TransformerNotFoundException(id)))
				.forEach(tf -> {
					if (tf.getTeam() == Team.AUTOBOT)
						tfListA.add(tf);
					else {
						tfListD.add(tf);
					}
				});

		// Matching fighters
		Iterator<Transformer> ita = tfListA.iterator();
		Iterator<Transformer> itd = tfListD.iterator();
		while (true) {
			if (tfListA.size() <= battles || tfListD.size() <= battles)
				break;

			Transformer tfa = ita.next();
			Transformer tfd = itd.next();

			battles++;

//				Special rules:
//				● Any Transformer named Optimus Prime or Predaking wins his fight automatically
//				regardless of any other criteria
//				● In the event either of the above face each other (or a duplicate of each other), the game
//				immediately ends with all competitors destroyed

			boolean specialA = tfa.getName().equals("Optimus") || tfa.getName().equals("Predaking");
			boolean specialD = tfd.getName().equals("Optimus") || tfd.getName().equals("Predaking");

			if (specialA && specialD) {
				// All destroyed
				allDestroyed = true;
				break;
			} else if (specialA) {
				losersD.add(tfd);
				continue;
			} else if (specialD) {
				losersA.add(tfa);
				continue;
			}

			// ○ If any fighter is down 4 or more points of courage and 3 or more points of
			// strength compared to their opponent, the opponent automatically wins the
			// faceoff regardless of overall rating (opponent has ran away)
			if (tfa.getCourage() <= tfd.getCourage() - 4 && tfa.getStrength() <= tfd.getStrength() - 3) {
				losersA.add(tfa);
				continue;
			} else if (tfd.getCourage() <= tfa.getCourage() - 4 && tfd.getStrength() <= tfa.getStrength() - 3) {
				losersD.add(tfd);
				continue;
			}
//				○ Otherwise, if one of the fighters is 3 or more points of skill above their opponent,
//				they win the fight regardless of overall rating
			if (tfa.getSkill() <= tfd.getSkill() - 3) {
				losersA.add(tfa);
				continue;
			} else if (tfd.getSkill() <= tfa.getSkill() - 3) {
				losersD.add(tfd);
				continue;
			}
//				● The winner is the Transformer with the highest overall rating
			if (overallRate(tfa) < overallRate(tfd)) {
				losersA.add(tfa);
				continue;
			} else if (overallRate(tfa) > overallRate(tfd)) {
				losersD.add(tfd);
				continue;
			}
//				● In the event of a tie, both Transformers are considered destroyed				
			losersA.add(tfa);
			losersD.add(tfd);

		}

//			● The team who eliminated the largest number of the opposing team is the winner

		res.setBattles(battles);
		if (battles == 0 || allDestroyed) {
			res.setWinningTeam(null);
			res.setSurvivedLosers(null);
		} else {
			if (losersA.size() > losersD.size()) {
				res.setWinningTeam(Team.DECEPTICON);
				res.setSurvivedLosers(tfListA.stream().filter(tf -> !losersA.contains(tf)).map(tf -> tf.getName())
						.collect(Collectors.toList()));
			} else if (losersA.size() < losersD.size()) {
				res.setWinningTeam(Team.AUTOBOT);
				res.setSurvivedLosers(tfListD.stream().filter(tf -> !losersD.contains(tf)).map(tf -> tf.getName())
						.collect(Collectors.toList()));
			} else {
				res.setWinningTeam(null);
				res.setSurvivedLosers(null);
			}
		}
		return res;
	}

	/**
	 * The overall rate of a transformer
	 * 
	 * @param t
	 * @return
	 */
	private int overallRate(Transformer t) {
		return t.getStrength() + t.getIntelligence() + t.getSpeed() + t.getEndurance() + t.getFirepower();
	}
}
