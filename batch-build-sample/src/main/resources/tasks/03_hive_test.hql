-- Some comments

-- Set command
set foo=5;

-- Query
INSERT OVERWRITE TABLE test.c
SELECT i FROM test.a a where a.j=5;

-- Query 2
INSERT OVERWRITE TABLE test.c
SELECT i FROM test.b;
