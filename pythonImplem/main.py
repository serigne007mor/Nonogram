import xmltodict
import json
import os
import cProfile
from itertools import product

def getVariables():
    pass

def getXOR():
    pass

def getNonSupports():
    pass

if __name__ == "__main__":
    # file = open('instances/big/Nonogram-069-regular-table.xml')
    file = open('instances/small/test-001-ternary.xml')
    docu = xmltodict.parse(file.read())
    out = open('result.cnf', 'w')
    variables = []
    varId = []
    varValue = []
    output = ""
    for val in docu.get("instance").get("variables").get("var"):
        for key, value in val.items():
            variables.append(value)
    for i in range(0,len(variables)):
        if i%2 == 0:
            varId.append(variables[i])
        elif i%2 == 1:
            varValue.append(variables[i].split(" "))

    varDict = {}
    satValue = varValue.copy()
    satTracker = 1
    satVarDict  =  {}
    for  i in range(0,len(satValue)):
        for j in range(0,len(varValue[i])):
            satValue[i][j]  = satTracker
            satTracker+=1 
            
    for id, value in zip(varId, varValue):
        varDict[id] = value

    for id, value in zip(varId, satValue):
        satVarDict[id] = value 
    
    for i in varId:
        for j in satVarDict.get(i):
            out.write(str(j)+" ")
        out.write("0\n")

    for currentId in varId:
        for i in range(0, len(satVarDict.get(currentId))):
            for j in range(i+1, len(satVarDict.get(currentId))):
                out.write("-"+str(satVarDict.get(currentId)[i])+" -"+str(satVarDict.get(currentId)[j])+" 0\n")
                
    extentionId = []
    extentionList = []
    extentionSupport = []
    
    for i in docu.get("instance").get("constraints").get("extension"):
        for key, val in i.items():
            if key == "@id":
                extentionId.append(val)
            if key == "supports":
                extentionSupport.append(val)
            if key == "list":
                extentionList.append(val)
            
    s = []
    supportList=[]
    for i in extentionSupport:
        s.append(i.replace(")(",".").replace("(","").replace(")","").split("."))
    
    for i in s:
        currentSupport = []
        for j in i:
            currentSupport.append(tuple(j.split(",")))
        supportList.append(currentSupport)
        
    supportIdListDict = {}
    varIdListDict = {}
    
    for id, support in zip(extentionId, supportList):
        supportIdListDict[id] = support
    
    for id, var in zip(extentionId, extentionList):
        varIdListDict[id] = var.split(" ")
        
   
    
    res = list(product(varValue[0], varValue[1],varValue[2]))
