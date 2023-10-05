#include <iostream>
#include <fstream>
#include <string>

using namespace std;



int main(int argc, char** argv){
    
    string types[] = {"Normal", "Fire", "Water", "Electric", "Grass", "Ice", "Fighting", "Poison", "Ground", "Flying", "Psychic", "Bug", "Rock", "Ghost", "Dragon", "Dark", "Steel", "Fairy"};
    
    if (argc < 2) cerr<<"Missing File Name"<<endl;
    else{
        ofstream fileOut(argv[1]);

        for (int i = 0; i < 18; i++){
            fileOut << types[i];    
            for (int j = 0; j < 18; j++){
                fileOut << "\tX";
            }
            fileOut << "\n";
        }
        

    }
    return 0;
}