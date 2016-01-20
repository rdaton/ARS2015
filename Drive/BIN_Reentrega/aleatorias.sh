#!/bin/bash


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

