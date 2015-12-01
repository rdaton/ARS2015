#!/usr/bin/python2
# -*- coding: utf-8 -*-

##intento import cElementTree que es código nativo
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

##creo puntero a la raíz del árbol
unArbol = ET.parse("fichero1.xml")
unaRaiz = unArbol.getroot()

#Saco id de pelicula
unIdPelicula= unaRaiz.find('.//movie:filmid',unNameSpaces).text

##Saco título de pelicula
unTituloPelicula= unaRaiz.find('.//dc:title',unNameSpaces).text

print unIdPelicula,unTituloPelicula

##Saco Actores que han participado
unaListaActores=list();
for todoElemento in unaRaiz.iterfind('.//movie:actor',unNameSpaces):
    ##cojo la lista de claves de atributos, y me quedo con el primer nombre de clave
    ##me evito tener que meter en unaKey el siguiente tocho
    ##{http://www.w3.org/1999/02/22-rdf-syntax-ns#}resource
    unaKey=todoElemento.attrib.keys()[0]    
    unaCadenaActor=todoElemento.attrib.get(unaKey)
    print unIdPelicula,unaCadenaActor
    
