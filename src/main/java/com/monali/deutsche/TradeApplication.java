package com.monali.deutsche;

import com.monali.deutsche.dao.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The TradeApplication program implements an transmission of trade application
 *
 * @author  Monali Singh
 * @version 1.0
 * @since   2021-05-02
 */

@SpringBootApplication
@EnableScheduling
public class TradeApplication {
	@Autowired
	private TradeRepository tradeRepository;

	public static void main(String[] args) {
		SpringApplication.run(TradeApplication.class, args);
	}
}
