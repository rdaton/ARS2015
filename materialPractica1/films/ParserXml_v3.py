#!/usr/bin/python2

# -*- coding: utf-8 -*-
"""
Created on Wed Oct 21 23:08:30 2015

@author: César
"""

#Importamos el módulo
from xml.dom import minidom
import os
import sys  
reload(sys)  
sys.setdefaultencoding('utf-8')

def main():
    resultado = []   
    contador = 0
    
    print "Extrayendo peliculas y actores..."
    
    directorioOriginal = os.getcwd()
    
    directorio = os.path.join(directorioOriginal, 'films')
    os.chdir(directorio)
    ficheros = os.listdir(directorio)
    f=open("datos.csv","a")
    for fichero in ficheros:
        contador = contador + 1
        peli = buscaPeli(fichero,'dc:title')
        resultado = peli
        datos = buscaActores(fichero,'movie:actor')
        directorio = os.path.join(directorioOriginal, 'actors')
        os.chdir(directorio)
        ficheros = os.listdir(directorio)
        for fichero in ficheros:
            for i in range(len(datos)):
                if (fichero == datos[i]+".xml"):
                    nombre = buscaNombre(fichero,'movie:actor_name')
                    resultado.append(';' + str(nombre))

        print resultado    
        for i in range(len(resultado)):
            f.write(str(peli[i]))
        f.write('\n')
        directorio = os.path.join(directorioOriginal, 'films')
        os.chdir(directorio)
        ficheros = os.listdir(directorio)
   
    
    f.close()
    print "Terminado: generado datos.csv"

def buscaPeli(fichero,tag):
    resultList = []
    try:
        dom = minidom.parse(fichero)
        elements = dom.getElementsByTagName(tag)
        if len(elements) != 0:
            for i in range(0,len(elements)):
                resultList.extend([elements[i].childNodes[0].nodeValue])
        else:
            print 'No se ha encontrado ' + tag
    except:
        print 'No hay datos'
        print 'Fichero: ' + fichero
        print 'Etiqueta: ' + tag

    return resultList

def buscaActores(fichero,tag):
    lista = list()
    
    try:
        dom = minidom.parse(fichero)
        elements = dom.getElementsByTagName(tag)

        if len(elements) != 0:
            for i in range(0,len(elements)):
                lista.append(elements[i].getAttribute("rdf:resource"))
            for i in range(len(lista)):
                lista[i] = lista[i].replace("http://data.linkedmdb.org/resource/actor/","data.linkedmdb.org.data.actor.")
        else:
            print 'No hay elementos en el fichero XML con la etiqueta ' + tag
    except:
        print 'No se ha encontrado'
        print 'Fichero: ' + fichero
        print 'Etiqueta: ' + tag

    return lista
    
def buscaNombre(fichero,tag):
    resultList = []
    try:
        dom = minidom.parse(fichero)
        elements = dom.getElementsByTagName(tag)
        if len(elements) != 0:
            for i in range(0,len(elements)):
                resultList.extend([elements[i].childNodes[0].nodeValue])
        else:
            print 'No se ha encontrado ' + tag
    except:
        print 'No hay datos'
        print 'Fichero: ' + fichero
        print 'Etiqueta: ' + tag

    return resultList



