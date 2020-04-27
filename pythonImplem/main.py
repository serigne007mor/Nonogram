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

def get_key(my_dict, val): 
    for key, value in my_dict.items(): 
         if val == value: 
             return key 
  
    return "key doesn't exist"

def createSat(file, out):
    # file = open('instances/big/Nonogram-069-regular-table.xml')
    # file = open('instances/small/test-001-ternary.xml')
    # out = open('result.cnf', 'w')
    docu = xmltodict.parse(file.read())
    finalOutput  = ""
    xVar = []
    qVar = []
    xId = []
    qId = []
    variables = []
    varId = []
    varValue = []
    varValue2 = []
    xVarDict = {}
    qVarDict = {}
    numClauses = 0
    for val in docu.get("instance").get("variables").get("var"):
        for key, value in val.items():
            variables.append(value)
    for i in range(0,len(variables)):
        if i%2 == 0:
            varId.append(variables[i])
        
        elif i%2 == 1:
            varValue.append(variables[i].split(" "))
            varValue2.append(variables[i].split(" "))
            
    for i in range(0,len(varId)):
        if (varId[i][0] == 'x'):
            xId.append(varId[i])
            xVar.append(varValue[i])
        else:
            qId.append(varId[i])
            qVar.append(varValue[i])
    
    qId = sorted(qId, key=lambda x: int("".join([i for i in x if i.isdigit()])))
    xId = sorted(xId, key=lambda x: int("".join([i for i in x if i.isdigit()])))
    
    for id, var in zip (xId, xVar):
        xVarDict[id] = var
    for id, var in zip (qId, qVar):
        qVarDict[id] = var
    xSatVarDict = {}
    nqVarDict = qVarDict.copy()
    qSatVarDict = {}
    varDict = {}
    satValue = varValue2.copy()
    satTracker = 1
    satVarDict  =  {}
    
   
    for  i in range(0,len(satValue)):
        for j in range(0,len(varValue2[i])):
            satValue[i][j]  = str(satTracker)
            satTracker+=1 
    # print(varValue)
    for id, valueV in zip(varId, varValue):
        varDict[id] = valueV
    for id, value in zip(varId, satValue):
        satVarDict[id] = value 
    
    for id in xId:
        xSatVarDict[id] = satVarDict[id] 
    trackQ = 0
    for id in qId:
        qSatVarDict[id] = satVarDict[id] 
        qVar[trackQ] = satVarDict[id]
        trackQ+=1

        
    
    
    
    ##############################
    for i in varId:
        for j in satVarDict.get(i):
            finalOutput = finalOutput + (str(j)+" ")
        finalOutput = finalOutput + "0\n"
        numClauses+=1

    for currentId in varId:
        for i in range(0, len(satVarDict.get(currentId))):
            for j in range(i+1, len(satVarDict.get(currentId))):
                finalOutput = finalOutput + "-"+str(satVarDict.get(currentId)[i])+" -"+str(satVarDict.get(currentId)[j])+" 0\n"
                numClauses+=1
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
                extentionList.append(val.split(" "))
            
    s = []
    supportList=[]
    for i in extentionSupport:
        s.append(i.replace(")(",".").replace("(","").replace(")","").split("."))
    
    for i in s:
        currentSupport = []
        for j in i:
            currentSupport.append(tuple(j.split(",")))
        supportList.append(currentSupport)
        
    IdSupportDict = {}
    IdListDict = {}
    listSupportDict = {}
    
    for id, support in zip(extentionId, supportList):
        IdSupportDict[id] = support
    
    for id, var in zip(extentionId, extentionList):
        IdListDict[id] = var
 
    allSupportSat = []
    allSupport = []

    for i, v in IdListDict.items():
        allSupportSat.append(list(product(satVarDict.get(IdListDict.get(i)[0]), satVarDict.get(IdListDict.get(i)[1]), satVarDict.get(IdListDict.get(i)[2]))))
        allSupport.append(list(product(varDict.get(IdListDict.get(i)[0]), varDict.get(IdListDict.get(i)[1]), varDict.get(IdListDict.get(i)[2]))))
    
    noGoodTemp = []
    noGood = []
    for i in range(0, len(allSupport)):
        for j in range(0, len(allSupport[i])):
            if(allSupport[i][j] in IdSupportDict.get(extentionId[i])):
                # print(allSupport[i][j])
                noGoodTemp.append(allSupportSat[i][j])
        noGood.append(noGoodTemp)
                
                
    # print(noGoods)
    print(len(allSupportSat))
    print(len(noGood))
    for i in range(0,len(allSupportSat)):
        allSupportSat[i] = [x for x in allSupportSat[i] if x not in noGood[i]]
    print(len(allSupportSat))
    
    for i in allSupportSat:
        for j in i:
            finalOutput = finalOutput + "-"+j[0]+" -"+j[1]+" -"+j[2]+" 0\n"
            numClauses+=1
           
    print(numClauses)
    out.write("c this is nonogram instance\n")
    out.write("p cnf "+str(satTracker-1)+" "+str(numClauses)+"\n")
    out.write(finalOutput)
    out.close 
    a = [(1,2,3) , (4,5,6)]
    c = [(1,2,3),(4,5,6), (7,8,9),(10,11,12)]
    d = [x for x in c if x not in a]
    print(d)

    