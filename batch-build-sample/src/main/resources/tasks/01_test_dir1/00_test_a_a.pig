a = LOAD 'test.a' USING org.apache.hive.hcatalog.pig.HCatLoader();
b = FILTER a by j==2;
store b into 'test.a' using org.apache.hive.hcatalog.pig.HCatStorer();