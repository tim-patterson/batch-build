CREATE TABLE test.a (
  i int COMMENT 'This will actually be a field in the data'
)
COMMENT 'This is just an example table'
PARTITIONED BY (j int COMMENT 'This is just a peusdo column')
;
