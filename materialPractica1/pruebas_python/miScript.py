#!/usr/bin/python2
# -*- coding: utf-8 -*-
try:
    import xml.etree.cElementTree as ET
except ImportError:
    import xml.etree.ElementTree as ET

# meto namespaces
namespaces={'dc': 'http://purl.org/dc/terms/',
            'movie': 'http://data.linkedmdb.org/resource/movie/',
            'rdf': 'http://www.w3.org/1999/02/22-rdf-syntax-ns#',
            }

tree=ET.parse("fichero1.xml")
root=tree.getroot()
root.findall('dc:title',namespaces)
