#!/usr/bin/python2
# -*- coding: utf-8 -*-
from lxml import etree
tree = etree.parse("fichero1.xml")
root = tree.getroot()
for elem in tree.iterfind('movie:film',root.nsmap):
    unaCadena=elem.tostring()
    print  unaCadena
