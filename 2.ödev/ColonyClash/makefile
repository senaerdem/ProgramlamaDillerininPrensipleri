all: compile run

compile:
	gcc -I ./include/ -o ./lib/Taktik.o -c ./src/Taktik.c
	gcc -I ./include/ -o ./lib/Koloni.o -c ./src/Koloni.c
	gcc -I ./include/ -o ./lib/Oyun.o -c ./src/Oyun.c
	gcc -I ./include/ -o ./lib/Uretim.o -c ./src/Uretim.c
	gcc -I ./include/ -o ./lib/AUretim.o -c ./src/AUretim.c
	gcc -I ./include/ -o ./lib/BUretim.o -c ./src/BUretim.c
	gcc -I ./include/ -o ./lib/ATaktik.o -c ./src/ATaktik.c
	gcc -I ./include/ -o ./lib/BTaktik.o -c ./src/BTaktik.c
	gcc -I ./include/ -o ./bin/Test ./lib/Taktik.o ./lib/Koloni.o ./lib/Oyun.o ./lib/Uretim.o ./lib/AUretim.o ./lib/BUretim.o ./lib/ATaktik.o ./lib/BTaktik.o ./src/Test.c

run:
	./bin/Test