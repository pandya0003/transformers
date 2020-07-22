package com.transformers.controller;

//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.transformers.model.Team;
import com.transformers.model.Transformer;
import com.transformers.repository.TransformerRepository;

@SpringBootTest
//@RunWith(SpringRunner.class)
class TransformersControllerTest {
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private TransformerRepository tr;
	
	private MockMvc mockMvc;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); //apply(springSecurity()).
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	/**
	 * Adding new transformer is successful.
	 * 
	 * @throws Exception
	 */
	@Test
	public void addNew() throws Exception {
		
		tr.deleteAll();
		
		String content = "{\r\n" + 
				"    \"id\": 3,\r\n" + 
				"    \"name\": \"TF1\",\r\n" + 
				"    \"team\": \"DECEPTICON\",\r\n" + 
				"    \"strength\": 8,\r\n" + 
				"    \"intelligence\": 9,\r\n" + 
				"    \"speed\": 2,\r\n" + 
				"    \"endurance\": 6,\r\n" + 
				"    \"rank\": 7,\r\n" + 
				"    \"courage\": 5,\r\n" + 
				"    \"firepower\": 6,\r\n" + 
				"    \"skill\": 10\r\n" + 
				"}";
		
		mockMvc.perform(post("/transformers/").contentType(MediaType.APPLICATION_JSON).content(content.getBytes("UTF-8")))
				.andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith("application/json"))
				;
		assertEquals( 1, tr.count());
		
	}
	/**
	 * Updating transformer is successful.
	 * 
	 * @throws Exception
	 */
	@Test
	public void update() throws Exception {
		
		tr.deleteAll();
		Transformer tf = new Transformer( null, "Soundwave", Team.DECEPTICON, 8, 9, 2, 6, 7, 5, 6, 10); 
		tf = tr.save(tf);
		int id=tf.getId();
		
		String content = "{\r\n" + 
				"    \"id\": 3,\r\n" + 
				"    \"name\": \"TF1\",\r\n" + 
				"    \"team\": \"DECEPTICON\",\r\n" + 
				"    \"strength\": 8,\r\n" + 
				"    \"intelligence\": 9,\r\n" + 
				"    \"speed\": 2,\r\n" + 
				"    \"endurance\": 6,\r\n" + 
				"    \"rank\": 7,\r\n" + 
				"    \"courage\": 5,\r\n" + 
				"    \"firepower\": 6,\r\n" + 
				"    \"skill\": 1\r\n" + 
				"}";
		
		mockMvc.perform(put("/transformers/" + id ).contentType(MediaType.APPLICATION_JSON).content(content.getBytes("UTF-8")))
				.andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith("application/json"))
				;
		
		Transformer res =  tr.findById(id).get();
		assertEquals( "TF1", res.getName());
		assertEquals( 1, res.getSkill());
		
	}
	/**
	 * Listing all transformers is successful.
	 * 
	 * @throws Exception
	 */
	@Test
	public void listAllTransformer() throws Exception {
		
		tr.deleteAll();
		tr.save(new Transformer( null, "Soundwave", Team.DECEPTICON, 8, 9, 2, 6, 7, 5, 6, 10));
		tr.save(new Transformer( null, "Hubcap", Team.AUTOBOT, 4,4,4,4,4,4,4,4));
		tr.save(new Transformer( null, "Bluestreak", Team.AUTOBOT, 6,6,7,9,5,2,9,7));
		tr.save(new Transformer( null, "Soundwave", Team.DECEPTICON, 1, 1, 1, 1, 1, 1, 1, 1));
		tr.save(new Transformer( null, "Foo", Team.DECEPTICON, 1, 1, 1, 1, 1, 1, 1, 1));
		
		mockMvc.perform(get("/transformers/" ))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith("application/json"))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$" , hasSize(5)))
				;
		
		
	}
	/**
	 * Deleting a transformer is successful.
	 * @throws Exception
	 */
	@Test
	public void deleteTransformer() throws Exception {
		
		tr.deleteAll();
		Transformer tf = new Transformer( null, "Soundwave", Team.DECEPTICON, 8, 9, 2, 6, 7, 5, 6, 10); 
		tf = tr.save(tf);
		int id=tf.getId();
		
		mockMvc.perform(delete("/transformers/" + id ))
				.andExpect(status().isNoContent())
				;
		
		assertEquals( 0, tr.count());
	}
	/**
	 * Playing a game throws exception, because an invalid transformer is selected.
	 * 
	 * @throws Exception
	 */
	@Test
	void playGame_TransformerNotFound() throws Exception {
		tr.deleteAll();
		String content = "[0,1,2]";
		
		mockMvc.perform(post("/transformers/play").contentType(MediaType.APPLICATION_JSON).content(content.getBytes("UTF-8")))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith("text/plain"))
				;
	}
	
	
	/**
	 * Play a game is successful.
	 * 
	 * Soundwave, D, 8,9,2,6,7,5,6,10
	 * Bluestreak, A, 6,6,7,9,5,2,9,7
	 * Hubcap: A, 4,4,4,4,4,4,4,4
	 * 
	 * The output should be:
	 * 1 battle
	 * Winning team (Decepticons): Soundwave
	 * Survivors from the losing team (Autobots): Hubcap
	 * 
	 * @throws Exception
	 */
	@Test
	public void playGame1() throws Exception {
		
		tr.deleteAll();
		int id0 = tr.save(new Transformer( null, "Soundwave", Team.DECEPTICON, 8, 9, 2, 6, 7, 5, 6, 10)).getId(); 
		int id1 = tr.save(new Transformer( null, "Hubcap", Team.AUTOBOT, 4,4,4,4,4,4,4,4)).getId();
		int id2 = tr.save(new Transformer( null, "Bluestreak", Team.AUTOBOT, 6,6,7,9,5,2,9,7)).getId();
		
		String content = String.format("[%d,%d,%d]",id0,id1,id2) ;
		
		mockMvc.perform(post("/transformers/play").contentType(MediaType.APPLICATION_JSON).content(content.getBytes("UTF-8")))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith("application/json"))
				.andExpect(jsonPath("$.battles").value(1))
				.andExpect(jsonPath("$.winningTeam").value("DECEPTICON"))
				.andExpect(jsonPath("$.survivedLosers").isArray() )
				.andExpect(jsonPath("$.survivedLosers", hasSize(1)))
				.andExpect(jsonPath("$.survivedLosers", hasItem("Hubcap")))
				;
	}
	
	/**
	 * Play a game is successful.
	 * 
	 * The drawn game
	 * 
	 * @throws Exception
	 */
	@Test
	void playNoWinner() throws Exception {
		
		tr.deleteAll();
		int id0 = tr.save(new Transformer( null, "Soundwave", Team.DECEPTICON, 8, 9, 2, 6, 7, 5, 6, 10)).getId();
		int id1 = tr.save(new Transformer( null, "Hubcap", Team.AUTOBOT, 4,4,4,4,4,4,4,4)).getId();
		int id2 = tr.save(new Transformer( null, "Bluestreak", Team.AUTOBOT, 6,6,7,9,5,2,9,7)).getId();
		int id3 = tr.save(new Transformer( null, "Soundwave", Team.DECEPTICON, 1, 1, 1, 1, 1, 1, 1, 1)).getId();
		
		String content = String.format("[%d,%d,%d,%d]",id0,id1,id2,id3) ;
		
		mockMvc.perform(post("/transformers/play").contentType(MediaType.APPLICATION_JSON).content(content.getBytes("UTF-8")))
		.andExpect(status().isOk())
		.andExpect(content().contentTypeCompatibleWith("application/json"))
		.andExpect(jsonPath("$.battles").value(2))
		.andExpect(jsonPath("$.winningTeam", is(nullValue())))
		.andExpect(jsonPath("$.survivedLosers", is(nullValue())))
		;
	}
	
}
