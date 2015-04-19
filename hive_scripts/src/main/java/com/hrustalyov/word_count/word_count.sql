DROP TABLE if EXISTS docs;

CREATE TABLE docs (line STRING);

LOAD DATA LOCAL INPATH '/path/to/word_count.txt' OVERWRITE INTO TABLE docs;

SELECT word, count(*) FROM docs LATERAL VIEW explode(split(line, ' ')) tmp_table AS word GROUP BY word;