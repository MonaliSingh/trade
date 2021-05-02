package com.monali.deutsche;

import com.monali.deutsche.controller.TradeController;
import com.monali.deutsche.model.TradeModel;
import com.monali.deutsche.model.TradeModels;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration test for TradeApplication
 *
 * @author  Monali Singh
 * @version 1.0
 * @since   2021-05-02
 */

@SpringBootTest(classes = TradeApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 public class TradeApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private TradeController tradeController;

	@LocalServerPort
	private int port;

	@Sql({"classpath:schema.sql","classpath:data.sql"})
	@Test
	public void testGetAllTrades(){
		assertTrue(this.restTemplate.getForObject("http://localhost:"+port+"/api/trade/all",TradeModels.class).getTradeModelList().size()==4);

	}

	@Test
	public void testAddTrade(){
		TradeModel tradeModel = new TradeModel("T4",1,"CP1","B1", LocalDate.of(2021,06,01), LocalDate.now(),"N");
		ResponseEntity<String> responseEntity = this.restTemplate.
				postForEntity("http://localhost:"+port+"/api/trade/create",tradeModel,String.class);
		assertEquals(200,responseEntity.getStatusCodeValue());
	}
	@Test
	public void testAddTradeWithLowerVersion(){
		TradeModel tradeModel = new TradeModel("T1",1,"CP1","B1", LocalDate.of(2021,06,01), LocalDate.now(),"N");
		try {
			ResponseEntity<String> responseEntity = this.restTemplate.
					postForEntity("http://localhost:" + port + "/api/trade/create", tradeModel, String.class);
		}
		catch (RuntimeException e){
			assertEquals(e.getMessage(),"Lower Version can not be added");
		}

	}

	@Test
	public void testAddTradeWithWorngMaturityDate(){
		TradeModel tradeModel = new TradeModel("T1",1,"CP1","B1", LocalDate.of(2020,06,01), LocalDate.now(),"N");
		try {
			ResponseEntity<String> responseEntity = this.restTemplate.
					postForEntity("http://localhost:" + port + "/api/trade/create", tradeModel, String.class);
		}
		catch (RuntimeException e){
			assertEquals(e.getMessage(),"Maturity Date should not be less than Current Date.");
		}

	}

	@Test
	public void testScheduler(){
		List<TradeModel> existingList = new ArrayList<>();
		existingList = this.restTemplate.getForObject("http://localhost:"+port+"/api/trade/all",TradeModels.class).getTradeModelList();
		tradeController.updateExpired();
		List<TradeModel> updatedList = new ArrayList<>();
		updatedList = this.restTemplate.getForObject("http://localhost:"+port+"/api/trade/all",TradeModels.class).getTradeModelList();
		assertEquals(existingList.get(0).getExpired(),"N");
		assertEquals(updatedList.get(0).getExpired(),"Y");

	}

}
