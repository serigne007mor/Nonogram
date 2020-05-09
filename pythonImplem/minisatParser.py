import sys
import os
import re

file = open("101.out", 'r')
s = ""
for each in file: 
    s = s + each
numOfVar = ""
numOfClauses = ""
parseTime = ""
elimClauses = ""
simpliTime = ""
restart = ""
conflict = ""
decisions = ""
propagation= ""
conflictLiteral = ""
memUsed = ""
CPUTime = ""
ints = list(map(int, re.findall(r'\d+', s)))
floats = re.findall("\d+\.\d+", s)
ints = re.findall('\d+', s)
print(ints)