


import os

for i in range(1, 181):
    inputFile = "instances/big/Nonogram-"+str(i).zfill(3)+"-regular-table.xml"
    outputFile = "sat/Nonogram-"+str(i).zfill(3)+"-regular-table.cnf"
    # if(os.path.isfile(inputFile) and os.path.isfile(outputFile)):
    #     print(str(i).zfill(3), "OK")

    if(os.path.isfile(inputFile) and not(os.path.isfile(outputFile))):
        print(str(i).zfill(3), "SAT NOT EXIST")
    
    if(not(os.path.isfile(inputFile)) and (os.path.isfile(outputFile))):
        print(str(i).zfill(3), "SAT EXIST FILE NOT EXIST")