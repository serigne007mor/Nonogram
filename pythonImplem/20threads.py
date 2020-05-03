import xmltodict
import json
import os
import cProfile
from itertools import product
import sys
import threading
import multiprocessing


def createSat(inputFile, outputFile):
    
    if(os.path.isfile(inputFile)):
        print(inputFile, "START, running on:", os. getppid())
        file = open(inputFile, 'r')
        # file = open('instances/small/test-001-ternary.xml')
        out = open(outputFile, 'w')
        print(inputFile, "CONVERT START")
        docu = xmltodict.parse(file.read())
        print(inputFile, "CONVERT DONE")
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
        print(inputFile, "GET VAR START")
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
        print(inputFile, "GET VAR DONE")
        print(inputFile, "GET SAT VAR START")
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
        print(inputFile, "GET SAT VAR DONE")
        print(inputFile, "GET SAT VAR WRITING")
        for i in varId:
            for j in satVarDict.get(i):
                finalOutput = finalOutput + (str(j)+" ")
            finalOutput = finalOutput + "0\n"
            numClauses+=1
        print(inputFile, "GET SAT VAR WRITING OVER")
        print(inputFile, "GET SAT NEGATION WRITING")
        for currentId in varId:
            for i in range(0, len(satVarDict.get(currentId))):
                for j in range(i+1, len(satVarDict.get(currentId))):
                    finalOutput = finalOutput + "-"+str(satVarDict.get(currentId)[i])+" -"+str(satVarDict.get(currentId)[j])+" 0\n"
                    numClauses+=1
        print(inputFile, "GET SAT NEGATION WRITING OVER")
        extentionId = []
        extentionList = []
        extentionSupport = []
        print(inputFile, "PARSE EXTENTION START")
        for i in docu.get("instance").get("constraints").get("extension"):
            for key, val in i.items():
                if key == "@id":
                    extentionId.append(val)
                if key == "supports":
                    extentionSupport.append(val)
                if key == "list":
                    extentionList.append(val.split(" "))
        print(inputFile, "PARSE EXTENTION DONE")   
        s = []
        supportList=[]
        print(inputFile, "PARSE SUPPORT START")
        for i in extentionSupport:
            s.append(i.replace(")(",".").replace("(","").replace(")","").split("."))
        
        for i in s:
            currentSupport = []
            for j in i:
                currentSupport.append(tuple(j.split(",")))
            supportList.append(currentSupport)
        print(inputFile, "PARSE SUPPORT DONE") 
        IdSupportDict = {}
        IdListDict = {}
        listSupportDict = {}
        
        for id, support in zip(extentionId, supportList):
            IdSupportDict[id] = support
        
        for id, var in zip(extentionId, extentionList):
            IdListDict[id] = var
    
        allSupportSat = []
        allSupport = []
        print(inputFile, "ALL COMBINATION START")
        for i, v in IdListDict.items():
            allSupportSat.append(list(product(satVarDict.get(IdListDict.get(i)[0]), satVarDict.get(IdListDict.get(i)[1]), satVarDict.get(IdListDict.get(i)[2]))))
            allSupport.append(list(product(varDict.get(IdListDict.get(i)[0]), varDict.get(IdListDict.get(i)[1]), varDict.get(IdListDict.get(i)[2]))))
        
        noGoodTemp = []
        noGood = []
        print(inputFile, "ALL COMBINATION DONE")
        print(inputFile, "GET SUPPORT START")
        for i in range(0, len(allSupport)):
            for j in range(0, len(allSupport[i])):
                if(allSupport[i][j] in IdSupportDict.get(extentionId[i])):
                    # print(allSupport[i][j])
                    noGoodTemp.append(allSupportSat[i][j])
            noGood.append(noGoodTemp)
        print(inputFile, "GET SUPPORT DONE")        
                    
        print(inputFile, "REMOVE SUPPORT START")
    
        for i in range(0,len(allSupportSat)):
            allSupportSat[i] = [x for x in allSupportSat[i] if x not in noGood[i]]
        print(inputFile, "GET SUPPORT DONE")  
        print(inputFile, "PRINT TO FINAL STRING START")  
        for i in allSupportSat:
            for j in i:
                finalOutput = finalOutput + "-"+j[0]+" -"+j[1]+" -"+j[2]+" 0\n"
                numClauses+=1
        print(inputFile, "PRINT TO FINAL STRING DONE")  
        print(inputFile, "WRITING")
        out.write("c this is nonogram instance\n")
        out.write("p cnf "+str(satTracker-1)+" "+str(numClauses)+"\n")
        out.write(finalOutput)
        out.close 
        a = [(1,2,3) , (4,5,6)]
        c = [(1,2,3),(4,5,6), (7,8,9),(10,11,12)]
        d = [x for x in c if x not in a]
        print(inputFile, "DONE")
    else:
        print(inputFile, "DOES NOT EXITS")

def core1():#DONE
    inputFile = "instances/big/Nonogram-004-regular-table.xml"
    outputFile = "sat/Nonogram-004-regular-table.cnf"
    createSat(inputFile, outputFile)

def core2():
    inputFile = "instances/big/Nonogram-007-regular-table.xml"
    outputFile = "sat/Nonogram-007-regular-table.cnf"
    createSat(inputFile, outputFile)

def core3():
    inputFile = "instances/big/Nonogram-024-regular-table.xml"
    outputFile = "sat/Nonogram-024-regular-table.cnf"
    createSat(inputFile, outputFile)
        
def core4():
    inputFile = "instances/big/Nonogram-025-regular-table.xml"
    outputFile = "sat/Nonogram-025-regular-table.cnf"
    createSat(inputFile, outputFile)

def core5():
    inputFile = "instances/big/Nonogram-026-regular-table.xml"
    outputFile = "sat/Nonogram-026-regular-table.cnf"
    createSat(inputFile, outputFile)

def core6():
    inputFile = "instances/big/Nonogram-028-regular-table.xml"
    outputFile = "sat/Nonogram-028-regular-table.cnf"
    createSat(inputFile, outputFile)

def core7():
    inputFile = "instances/big/Nonogram-029-regular-table.xml"
    outputFile = "sat/Nonogram-029-regular-table.cnf"
    createSat(inputFile, outputFile)

def core8():
    inputFile = "instances/big/Nonogram-030-regular-table.xml"
    outputFile = "sat/Nonogram-030-regular-table.cnf"
    createSat(inputFile, outputFile)

# def core9():
#     for i in range(88,99):
#         inputFile = "instances/big/Nonogram-"+str(i).zfill(3)+"-regular-table.xml"
#         outputFile = "sat/Nonogram-"+str(i).zfill(3)+"-regular-table.cnf"
#         createSat(inputFile, outputFile)

# def core10():
#     for i in range(99,110):
#         inputFile = "instances/big/Nonogram-"+str(i).zfill(3)+"-regular-table.xml"
#         outputFile = "sat/Nonogram-"+str(i).zfill(3)+"-regular-table.cnf"
#         createSat(inputFile, outputFile)

# def core11():
#     for i in range(110, 121):
#         inputFile = "instances/big/Nonogram-"+str(i).zfill(3)+"-regular-table.xml"
#         outputFile = "sat/Nonogram-"+str(i).zfill(3)+"-regular-table.cnf"
#         createSat(inputFile, outputFile)

# def core12():
#     for i in range(121, 132):
#         inputFile = "instances/big/Nonogram-"+str(i).zfill(3)+"-regular-table.xml"
#         outputFile = "sat/Nonogram-"+str(i).zfill(3)+"-regular-table.cnf"
#         createSat(inputFile, outputFile)


if __name__ == "__main__":
    
    
    inputFile = "instances/big/Nonogram-066-regular-table.xml"
    outputFile = "sat/Nonogram-066-regular-table.cnf"
    createSat(inputFile, outputFile)
    
    # t1 = multiprocessing.Process(target=core1, name='t1') 
    # t2 = multiprocessing.Process(target=core2, name='t2') 
    # t3 = multiprocessing.Process(target=core3, name='t3') 
    # t4 = multiprocessing.Process(target=core4, name='t4')
    # t5 = multiprocessing.Process(target=core5, name='t5') 
    # t6 = multiprocessing.Process(target=core6, name='t6') 
    # t7 = multiprocessing.Process(target=core7, name='t7') 
    # t8 = multiprocessing.Process(target=core8, name='t8') 
    # t9 = threading.Thread(target=to99, name='t9') 
    # t10 = threading.Thread(target=to110, name='t10') 
    # t11 = threading.Thread(target=to121, name='t11') 
    # t12 = threading.Thread(target=to132, name='t12') 
 

    # t1.start()
    # t2.start()
    # t3.start()
    # t4.start()
    # t5.start()
    # t6.start()
    # t7.start()
    # t8.start()
    # t9.start()
    # t10.start()
    # t11.start()
    # t12.start()