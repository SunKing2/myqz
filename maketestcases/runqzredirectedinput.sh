cd maketestcases
while read input
do sleep 0
   echo "$input"
done < inputdata | perl qzfortesting.pl -phardest mystuff.qz > tmp
cat tmp | egrep -v '^==' > tmp-output
cat tmp | egrep '^===' > tmp-data
cat tmp-output
