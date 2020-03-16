def effaceTableau (tab):
   '''
   (list) -> None
   Cette fonction prepare le tableau de jeu (la matrice) 
   en mettant '-' dans tous les elements.
   Elle ne crée pas une nouvelle matrice
   Preconditions: tab est une reference a une matrice n x n qui contient '-', 'X' ou 'O'
   '''
   for i in range(0,len(tab)):
      for j in range(0,len(tab[i])):
        tab[i][j] = '-'
    # a completer
    
    # retourne rien

      
def verifieGagner(tab):  
    '''(list) ->  bool
    * Preconditions: tab est une reference a une matrice n x n qui contient '-', 'X' ou 'O'
    * Verifie s'il y a un gagnant.
    * Cherche pour 3 X's et O's dans une ligne, colonne, et diagonal.
    * Si on a trouvé, affiche le gagnant (le message "Joueur X a gagné!" 
    * ou "Joueur O a gagné!") et retourne True.
    * S'il y a un match nul (verifie ca avec la fonction testMatchNul),
    * affiche "Match nul" et retourne True.
    * Si le jeu n'est pas fini, retourne False.
    * La fonction appelle les fonctions testLignes, testCols, testDiags
    * pour verifier s'il y a un gagnant.
    * Ces fonctions retournent le gagnant 'X' ou 'O', ou '-' s'il n'y a pas de gagnant
    '''
    if(not (testMatchNul)):
      if(testCols(tab) != '-' ):
        return testCols(tab)
      elif(testDiags(tab) != '-'):
        return testDiags(tab)
      elif(testLignes(tab) != '-'):
        return testDiags(tab)

    # a completer
   
    return not(testMatchNul)  # a changer

 
def testLignes(tab):
  ''' (list) ->  str
  * verifie s’il y a une ligne gagnante.
  * cherche trois 'X' ou trois 'O' dans une ligne.  
  * Si on trouve, le caractere 'X' ou 'O' et retourné, sinon '-' est retourné.
  * Preconditions: tab est une reference a une matrice n x n qui contient '-', 'X' ou 'O'
  '''
  if(tab[0][0] == tab[0][1] and tab[0][1] == tab[0][2]):
      return tab[0][0]
  elif(tab[1][0] == tab[1][1] and tab[1][1] == tab[1][2]):
      return tab[1][0]
  elif(tab[2][0] == tab[2][1] and tab[2][1] == tab[2][2]):
      return tab[2][0]
  return '-'
  
  
def testCols(tab):
   ''' (list) ->  str
   * verifie s’il y a une colonne gagnante.
   * cherche trois 'X' ou trois 'O' dans une colonne.  
   * Si on trouve, le caractere 'X' ou 'O' et retourné, sinon '-' est retourné.
   * Preconditions: tab est une reference a une matrice n x n qui contient '-', 'X' ou 'O'
   '''
   if(tab[0][0] == tab[1][0] and tab[1][0] == tab[2][0]):
      return tab[0][0]
   elif(tab[0][1] == tab[1][1] and tab[1][1] == tab[2][1]):
      return tab[0][1]
   elif(tab[0][2] == tab[1][2] and tab[1][2] == tab[2][2]):
      return tab[0][2]
    
   # a completer
  
   return '-'   #a changer pour retourner le gagnant, ou '-' s'il n'y a pas de gagnant

   
def testDiags(tab):
   ''' (list) ->  str
   * cherche trois 'X' ou trois 'O' dans une diagonale.  
   * Si on trouve, le caractere 'X' ou 'O' et retourné
   * sinon '-' est retourné.
   * Preconditions: tab est une reference a une matrice n x n qui contient '-', 'X' ou 'O'
   '''
   if(tab[0][0] == tab[1][1] and tab[1][1] == tab[2][2]):
      return tab[0][0]
   elif(tab[0][2] == tab[1][1] and tab[1][1] == tab[2][0]):
      return tab[0][2]

   # a completer
    
   return '-'   # a changer pour retourner le gagnant, ou '-' s'il n'y a pas de gagnant

  
  
def testMatchNul(tab):
   ''' (list) ->  bool
   * verifie s’il y a un match nul
   * verifie si tous les elements de la matrice contiennent X ou O, pas '-'.  
   * Si on ne trouve pas de '-' dans la matrice, retourne True. 
   * S'il y a de '-', retourne false.
   * Preconditions: tab est une reference a une matrice n x n qui contient '-', 'X' ou 'O'
   '''
   for i in range(0,len(tab)):
      for j in range(0,len(tab[i])):
        if(tab[i][j] == '-'):
          return False
   if(testCols(tab) == '-' and testDiags(tab) == '-' and testLignes(tab) == '-'):
    return True
   # a completer
  
   return False  # a changer

