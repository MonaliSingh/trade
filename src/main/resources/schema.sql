DROP TABLE if exists Trade;

CREATE TABLE Trade
(
    trade_id VARCHAR NOT NULL,
    version NUMERIC NOT NULL,
    counter_party_id VARCHAR NOT NULL,
    book_id VARCHAR NOT NULL,
    maturity_date DATE NOT NULL,
    created_date DATE NOT NULL,
    expired VARCHAR(1) NOT NULL,
    PRIMARY KEY (trade_id,version)
);

