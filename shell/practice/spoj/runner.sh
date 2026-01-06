g++ --std=c++11 $1.cpp
./a.out < $1.in.txt > out
diff $1.out.txt out