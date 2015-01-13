a = LOAD 'test.batting_data' USING org.apache.hive.hcatalog.pig.HCatLoader();
c = FILTER a by j==5;
store c into '/test/batting_data';

b = LOAD '/test/batting_data';
store a into 'test.batting_data' using org.apache.hive.hcatalog.pig.HCatStorer();