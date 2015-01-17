b = LOAD 'test.b' USING org.apache.hive.hcatalog.pig.HCatLoader();
store b into 'test.b' using org.apache.hive.hcatalog.pig.HCatStorer();