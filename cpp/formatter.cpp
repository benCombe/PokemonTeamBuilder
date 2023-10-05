#include <iostream>
#include <fstream>

using namespace std;

fstream file, out;
char c;


int main(int argc, char ** argv){
    if (argc < 2) cerr<<"Missing File Name"<<endl;
    else{
        file.open(argv[1], ios::in);
        out.open("output.txt", ios::out);
        while(file >> noskipws >> c){
            
            if (c == EOF) break;
            if (c == ',') {
                out<<'\n';
                for (int i = 0; i < 2; i++)
                file >> noskipws >> c;
            }
            out<<c;
            
        }

    }
    return 0;
}