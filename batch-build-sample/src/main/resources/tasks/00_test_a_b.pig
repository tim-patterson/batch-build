a = LOAD 'test.a' USING org.apache.hive.hcatalog.pig.HCatLoader();
b = FILTER a by j==5;
store b into 'test.b' using org.apache.hive.hcatalog.pig.HCatStorer();