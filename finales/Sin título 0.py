# -*- coding: utf-8 -*-
"""
Created on Tue Nov 03 09:24:55 2015

@author: César
"""
import os
import csv
from BeautifulSoup import *
import sys  
reload(sys)  
sys.setdefaultencoding('utf-8')

def prueba():
    
    listaActores = list()
    
    directorioOriginal = os.getcwd()
    directorioPelis = os.path.join(directorioOriginal, 'films')
    os.chdir(directorioPelis)
    ficheros = os.listdir(directorioPelis)
    f=open(directorioOriginal + "\datos.csv","a")
    for fichero in ficheros:
        xmlPeli = open(fichero)
        sopa = BeautifulSoup(xmlPeli)
        id = sopa.find("movie:filmid")
        f.write(str(id.text) + ',')
        titulo = sopa.find("dc:title")
        f.write(str(titulo.text))
        #print titulo.text
        actores = sopa.findAll("movie:actor")
        for actor in actores:
            #print actor["rdf:resource"]
            listaActores.append(actor["rdf:resource"])
            listaActores[0] = listaActores[0].replace("http://data.linkedmdb.org/resource/actor/"
                                            ,"data.linkedmdb.org.data.actor.")

            directorioActores = os.path.join(directorioOriginal, 'actors')
            os.chdir(directorioActores)

            xmlActor = open(listaActores[0] + ".xml")
            sopa2 = BeautifulSoup(xmlActor)
            nombre = sopa2.find("movie:actor_name")
            f.write("," + str(nombre.text))         
            
            listaActores[0] = listaActores[0].replace("data.linkedmdb.org.data.actor.",
                                                "http://data.linkedmdb.org/resource/actor/")
            listaActores.remove(actor["rdf:resource"])
            xmlPeli.close()
            xmlActor.close()
            os.chdir(directorioPelis)
        
        f.write("\n")                
    f.close()
    

#Aun no funciona
def frecuencias():

    directorioOriginal = os.getcwd()
    csvFile = open(directorioOriginal + '\datos.csv')
    lector = csv.reader(csvFile)
    datos = list(lector)
    actorPelis = []
    
    
    print datos
    f=open("aristasPelis.csv","a")      
    
    for i in range(len(datos)):#recorre la lista de listas
        for j in range(2,len(datos[i])):#recorre las listas de dentro de la lista
            if (len(datos[i]) > 2):
                actor = datos[i][j]
                if (actor not in actorPelis):
                    actorPelis.append(actor)
                    for k in range(len(datos)):
                        if (actor in datos[k]):
                            peli = datos [k][0]
                            actorPelis.append(","+str(peli))
                
            actorPelis.append('\n')          
        
                 
    for i in range(len(actorPelis)):
        f.write(str(actorPelis[i]))
           
    f.close()
    csvFile.close()
    
def aristas():
    directorioOriginal = os.getcwd()
    csvFile = open(directorioOriginal + '\\aristasPelis.csv')
    lector = csv.reader(csvFile)
    datos = list(lector)
    actorPelis = []
    
    f=open("aristas.csv","a")    
    
    for i in range(len(datos)):
        for j in range(1,len(datos[i])):
            actor = datos[i][0]
            if (len(datos[i]) > 1):
                peli1 = datos[i][j]
                for k in range(j+1,len(datos[i])):
                    peli2 = datos[i][k]
                    actorPelis.append(str(actor)+ ',' +str(peli1)+','+str(peli2)+'\n')
    
    for i in range(len(actorPelis)):
        f.write(str(actorPelis[i]))
    f.close()
    csvFile.close()
    
    
    
    