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
    xVar = []
    qVar = []
    xId = []
    qId = []
    variables = []
    varId = []
    varValue = []
    xVarDict = {}
    qVarDict = {}
    for val in docu.get("instance").get("variables").get("var"):
        for key, value in val.items():
            variables.append(value)
    for i in range(0,len(variables)):
        if i%2 == 0:
            varId.append(variables[i])
        
        elif i%2 == 1:
            varValue.append(variables[i].split(" "))

    for i in range(0,len(varId)):
        if (varId[i][0] == 'x'):
            xId.append(varId[i])
            xVar.append(varValue[i])
        else:
            qId.append(varId[i])
            qVar.append(varValue[i])
    
    qId = sorted(qId, key=lambda x: int("".join([i for i in x if i.isdigit()])))
    xId = sorted(xId, key=lambda x: int("".join([i for i in x if i.isdigit()])))
    
    for i, j in zip (xId, xVar):
        xVarDict[i] = j
    for i, j in zip (qId, qVar):
        qVarDict[i] = j
    xSatVarDict = {}
    qSatVarDict = {}
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
    
    for id in xId:
        xSatVarDict[id] = satVarDict[id] 
    for id in qId:
        qSatVarDict[id] = satVarDict[id] 
    
    print(qSatVarDict)
    
    # for i in varId:
    #     for j in satVarDict.get(i):
    #         out.write(str(j)+" ")
    #     out.write("0\n")

    # for currentId in varId:
    #     for i in range(0, len(satVarDict.get(currentId))):
    #         for j in range(i+1, len(satVarDict.get(currentId))):
    #             out.write("-"+str(satVarDict.get(currentId)[i])+" -"+str(satVarDict.get(currentId)[j])+" 0\n")
                
    # extentionId = []
    # extentionList = []
    # extentionSupport = []
    
    # for i in docu.get("instance").get("constraints").get("extension"):
    #     for key, val in i.items():
    #         if key == "@id":
    #             extentionId.append(val)
    #         if key == "supports":
    #             extentionSupport.append(val)
    #         if key == "list":
    #             extentionList.append(val)
            
    # s = []
    # supportList=[]
    # for i in extentionSupport:
    #     s.append(i.replace(")(",".").replace("(","").replace(")","").split("."))
    
    # for i in s:
    #     currentSupport = []
    #     for j in i:
    #         currentSupport.append(tuple(j.split(",")))
    #     supportList.append(currentSupport)
        
    # supportIdListDict = {}
    # varIdListDict = {}
    
    # for id, support in zip(extentionId, supportList):
    #     supportIdListDict[id] = support
    
    # for id, var in zip(extentionId, extentionList):
    #     varIdListDict[id] = var.split(" ")
        
    # res = []
    # for i in range(0, len(varValue)):
    #     for j in range(i+1, len (varValue)):
    #         for k in range(j+1, len(varValue)):
    #             res.append(list(product(varValue[i],varValue[j],varValue[k])))

