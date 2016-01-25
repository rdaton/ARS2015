#!/bin/bash

##De libre escala
java -jar pr2r.jar -t libre -n 500 -m 3 &
java -jar pr2r.jar -t libre -n 1000 -m 3 &
java -jar pr2r.jar -t libre -n 5000 -m 3 &
##comparación actores
java -jar pr2r.jar -t libre -n 7701 -m 3 &
##comparación peliculas
java -jar pr2r.jar -t libre -n 2500 -m 3 &


java -jar pr2r.jar -t libre -n 500 -m 4 &
java -jar pr2r.jar -t libre -n 1000 -m 4 &
java -jar pr2r.jar -t libre -n 5000 -m 4 &
##comparación actores
java -jar pr2r.jar -t libre -n 7701 -m 4 &
##comparación peliculas
java -jar pr2r.jar -t libre -n 2500 -m 4 &

##comparación actores (mejor)
java -jar pr2r.jar -t libre -n 7701 -m 8 &
##comparación peliculas(mejor)
java -jar pr2r.jar -t libre -n 2500 -m 8 &



##comparación peliculas
##Aleatorias
##fase subcritica ; p<1/N
java -jar pr2r.jar -t aleatoria -n 500 -p 0.001 &
java -jar pr2r.jar -t aleatoria -n 1000 -p 0.0005 &
java -jar pr2r.jar -t aleatoria -n 5000 -p 0.0001 &

##fase crítica ; p=1/N
java -jar pr2r.jar -t aleatoria -n 500 -p 0.002 &
java -jar pr2r.jar -t aleatoria -n 1000 -p 0.001 &
java -jar pr2r.jar -t aleatoria -n 5000 -p 0.0002 &

##fase supercrítica; p>1/N
java -jar pr2r.jar -t aleatoria -n 500 -p 0.003 &
java -jar pr2r.jar -t aleatoria -n 1000 -p 0.002 &
java -jar pr2r.jar -t aleatoria -n 5000 -p 0.0003 &

##fase conectada; p>=lnN/N
java -jar pr2r.jar -t aleatoria -n 500 -p 0.0124 &
java -jar pr2r.jar -t aleatoria -n 1000 -p 0.00690 &
java -jar pr2r.jar -t aleatoria -n 5000 -p 0.001703 &

