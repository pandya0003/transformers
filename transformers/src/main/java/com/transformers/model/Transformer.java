package com.transformers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents transformer
 * 
 * @author parth.pandya
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transformer implements Comparable<Transformer> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	private String name;
	@NotNull
	private Team team;
	@Min(1)
	@Max(10)
	private int strength;
	@Min(1)
	@Max(10)
	private int intelligence;
	@Min(1)
	@Max(10)
	private int speed;
	@Min(1)
	@Max(10)
	private int endurance;
	@Min(1)
	@Max(10)
	private int rank;
	@Min(1)
	@Max(10)
	private int courage;
	@Min(1)
	@Max(10)
	private int firepower;
	@Min(1)
	@Max(10)
	private int skill;

	/**
	 * 
	 * Inversely compare 2 transformers based on the rank
	 *  
	 */
	@Override
	public int compareTo(Transformer o) {
		if (getRank() > o.getRank())
			return -1;
		if (getRank() == o.getRank()) {
			int h1 = hashCode();
			int h2 = o.hashCode();
			if (h1 > h2)
				return -1;
			else if (h1 == h2)
				return 0;
			else
				return 1;
		} else
			return 1;
	}
}
