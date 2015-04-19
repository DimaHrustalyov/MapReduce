DROP TABLE if EXISTS docs;

CREATE TABLE docs (line STRING);

LOAD DATA LOCAL INPATH '/path/to/resources/text.json' OVERWRITE INTO TABLE docs;

CREATE TABLE json_file AS
  SELECT get_json_object(docs.line, '$.file') AS file,
         get_json_object(docs.line, '$.text') AS text
  FROM docs;

CREATE TABLE words_file AS
  SELECT split(text, ' ') AS words,
         file as file
  FROM json_file;

SELECT word, file FROM words_file LATERAL VIEW explode(words) word_table AS word GROUP BY word, file;