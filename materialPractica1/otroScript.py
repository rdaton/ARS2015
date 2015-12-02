#!/usr/bin/python2
# -*- coding: utf-8 -*-

##intento import cElementTree que es código nativo
import sys
import os
import csv

try:
    import xml.etree.cElementTree as ET
except ImportError:
    import xml.etree.ElementTree as ET

"""
Creado 1 de Diciembre 2015
@author: R. Daton

"""

##la primera versión se hizo con lxml
##la diferencia con xml a secas, (versión actual)
##es que no tenemos que crear el diccionario
##de namespaces a mano.. pero eso da igual.
##porque aun con lxml hay referencias a nombres del xml, que son hardcoded
##visto en http://stackoverflow.com/questions/14853243/parsing-xml-with-namespace-in-python-via-elementtree

unNameSpaces={'dc': 'http://purl.org/dc/terms/',
            'movie': 'http://data.linkedmdb.org/resource/movie/',
            'rdf': 'http://www.w3.org/1999/02/22-rdf-syntax-ns#',
            }







def parseaPelicula (ficheroPeliculas,fCsvPeliculas):
	##creo puntero a la raíz del árbol
	unArbol = ET.parse(ficheroPeliculas)
	unaRaiz = unArbol.getroot()

	#Saco id de pelicula
	unIdPelicula= unaRaiz.find('.//movie:filmid',unNameSpaces).text

	##Saco título de pelicula
	unTituloPelicula= unaRaiz.find('.//dc:title',unNameSpaces).text

	fCsvPeliculas.write(unIdPelicula+';'+unTituloPelicula+'\n')

	##Saco Actores que han participado
	unaListaActores=list();
	for todoElemento in unaRaiz.iterfind('.//movie:actor',unNameSpaces):
	    ##cojo la lista de claves de atributos, y me quedo con el primer nombre de clave
	    ##me evito tener que meter en unaKey el siguiente tocho
	    ##{http://www.w3.org/1999/02/22-rdf-syntax-ns#}resource
	    unaKey=todoElemento.attrib.keys()[0]    
	    unaCadenaActor=todoElemento.attrib.get(unaKey)
	    ##unaCadenaActor, contiene una url tipo http://A/B/C/idActor    
	    ##voy a extraer idActor, usando como separador "/" , y accediendo a la posición 5 (rango 0-5)
	    unSeparador="/"
	    print unIdPelicula,',',unaCadenaActor.split(unSeparador)[5]    




def abreFicheroRw (nombreFichero) :
    f=open(nombreFichero,'w');
    return f;

def cierraFicheroRw(f):
    f.close()


def main():
##bloque de declaración de ficheros de entrada, salida
##indices de ficheros, etc,

##declaraciones e inicializaciones para ficheros de peliculas
    ##puntero a fichero xml en curso
    directorioPelis = 'films'
    idPelicula=1
    fEntradaPelicula = 'fichero'+ str(idPelicula) + '.xml'
    fEntradaPeliculaConRuta=os.path.join(directorioPelis,fEntradaPelicula)
    
    ##fichero csv de peliculas
    ##creo carpeta de salida para fichero csv
    directorioPelisCsv='films_csv'
    if not os.path.exists(directorioPelisCsv):
        os.makedirs(directorioPelisCsv)
    fSalidaPeliculaCsv = 'pelisCsv.csv'
    fSalidaPeliculasCsvConRuta=os.path.join(directorioPelisCsv,fSalidaPeliculaCsv)
    ##abro el fichero csv para escritura    
    
    ##Genero fichero de nodos de peliculas

    ficheroPelisCsv=abreFicheroRw(fSalidaPeliculasCsvConRuta)
    
    
    parseaPelicula(fEntradaPeliculaConRuta,ficheroPelisCsv)

    
    cierraFicheroRw(ficheroPelisCsv)

    

if __name__ == "__main__":
    sys.exit(main())
