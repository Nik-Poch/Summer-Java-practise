#include <iostream>
#include <fstream>

#define SIZE 20

int main() {
    std::ofstream outfile ("handMadeFirstMap.json");
    
    outfile << "[" << std::endl;
    for(int i = 0; i < SIZE; ++i) {
        for(int j = 0; j < SIZE; ++j) {
            outfile << "\t{" << std::endl;
            outfile << "\t\t\"x\" : " << i << "," << std::endl;
            outfile << "\t\t\"y\" : " << j << "," << std::endl;

            if( (i == 8 && (j == 2 || j == 3 || j == 4 || j == 5 || j == 6)) || (i == 9 && (j == 5 || j == 6)) ||  (i == 1 && (j == 8 || j == 9)) || (i == 2 && (j == 8 || j == 9)) || (j == 14 && (i == 14 || i == 15 || i == 16)) || (i == 16 && j == 15) || (i == 7 && j == 18) || (i == 8 && (j == 17 || j == 18 || j == 19)) || (i == 9 && j == 19)) {
                outfile << "\t\t\"type\" : \"iron\"" << std::endl;
            } else if( (j == 2 && (i == 2 || i == 3 || i ==4)) || (i == 4 && (j == 2 || j == 3 || j == 4)) || (i == 3 && j == 4) || (i == 16 && (j == 4 || j == 7)) || (i == 15 && j == 7) || (i == 14 && (j == 7 || j == 8 || j == 9)) || (i == 1 && (j == 18 || j == 19)) || (j == 17 && (i == 12 || i == 13)) || (i == 13 && j == 18) ) {
                outfile << "\t\t\"type\" : \"rock\"" << std::endl;
            } else if( (j == 2 && (i == 13 || i == 14 || i == 15 || i == 16 || i == 17)) || (i == 7 && (j == 9 || j == 10 || j == 11)) || (i == 8 && (j == 11 || j == 12 || j == 13)) ) {
                outfile << "\t\t\"type\" : \"gold\"" << std::endl;
            } else if(i == 13 && j == 20) {
                outfile << "\t\t\"type\" : \"hell\"" << std::endl;
            } else if(j != 0) {
                outfile << "\t\t\"type\" : \"ground\"" << std::endl;
            } else {
                outfile << "\t\t\"type\" : \"air\"" << std::endl;
            }
            
            outfile << "\t}," << std::endl;
        }
    }
    outfile << "]" << std::endl;

    outfile.close();

    return 0;
}
