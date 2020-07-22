package com.transformers.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 
 * Represents the game result
 * 
 * @author parth.pandya
 *
 */
@Getter @Setter
@NoArgsConstructor
public class GameResult {
	/**
	 * Count of battles
	 */
	private int battles;
	/**
	 * Winning team
	 * If there's not a winner, get Null
	 */
	private Team winningTeam;
	/**
	 * Survivors from the losing team
	 * If there's not a loser, get Null, not empty list
	 *  
	 */
	private List<String> survivedLosers;
}
