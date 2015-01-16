a = LOAD 'test.a' USING org.apache.hive.hcatalog.pig.HCatLoader();
b = LOAD 'test.b' USING org.apache.hive.hcatalog.pig.HCatLoader();
c = JOIN a BY i, b BY i;
c1 = FOREACH c GENERATE a::i as (i);
store c1 into 'test.c' using org.apache.hive.hcatalog.pig.HCatStorer();