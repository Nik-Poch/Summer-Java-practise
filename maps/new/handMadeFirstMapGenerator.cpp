/**
 * x
 * y
 * ore:
 *      imgFileName
 *      movable
 *      oreType
 * checked
 * 
 */

#include <iostream>
#include <fstream>

#define SIZE 20

int main() {
    std::ofstream outfile ("handMadeFirstMap.json");
    
    outfile << "[" << std::endl;
    for(int i = 0; i < SIZE; ++i) {
        for(int j = 0; j < SIZE + 1; ++j) {
            outfile << "\t{" << std::endl;
            outfile << "\t\t\"posX\" : " << i << "," << std::endl;
            outfile << "\t\t\"posY\" : " << j << "," << std::endl;
            outfile << "\t\t\"checked\" : " << "false" << "," << std::endl;
            outfile << "\t\t\"ore\" : " << std::endl;
            outfile << "\t\t\t{" << std::endl;

            if( (i == 8 && (j == 2 || j == 3 || j == 4 || j == 5 || j == 6)) || (i == 9 && (j == 5 || j == 6)) ||  (i == 1 && (j == 8 || j == 9)) || (i == 2 && (j == 8 || j == 9)) || (j == 14 && (i == 14 || i == 15 || i == 16)) || (i == 16 && j == 15) || (i == 7 && j == 18) || (i == 8 && (j == 17 || j == 18 || j == 19)) || (i == 9 && j == 19)) {
                outfile << "\t\t\t\t\"movable\" : true," << std::endl;
                outfile << "\t\t\t\t\"oreType\" : \"IRON_ORE\"" << std::endl;
            } else if( (j == 2 && (i == 2 || i == 3 || i ==4)) || (i == 4 && (j == 2 || j == 3 || j == 4)) || (i == 3 && j == 4) || (i == 16 && (j == 4 || j == 7)) || (i == 15 && j == 7) || (i == 14 && (j == 7 || j == 8 || j == 9)) || (i == 1 && (j == 18 || j == 19)) || (j == 17 && (i == 12 || i == 13)) || (i == 13 && j == 18) ) {
                outfile << "\t\t\t\t\"movable\" : true," << std::endl;
                outfile << "\t\t\t\t\"oreType\" : \"STONE_ORE\"" << std::endl;
            } else if( (j == 2 && (i == 13 || i == 14 || i == 15 || i == 16 || i == 17)) || (i == 7 && (j == 9 || j == 10 || j == 11)) || (i == 8 && (j == 11 || j == 12 || j == 13)) ) {
                outfile << "\t\t\t\t\"movable\" : true," << std::endl;
                outfile << "\t\t\t\t\"oreType\" : \"GOLD_ORE\"" << std::endl;
            } else if(i == 13 && j == 20) {
                outfile << "\t\t\t\t\"movable\" : true," << std::endl;
                outfile << "\t\t\t\t\"oreType\" : \"HELL\"" << std::endl;
            } else if(j != 0) {
                outfile << "\t\t\t\t\"oreType\" : \"GROUND\"" << std::endl;
            } else {
                outfile << "\t\t\t\t\"oreType\" : \"AIR\"" << std::endl;
            }
            outfile << "\t\t\t}" << std::endl;
            
            outfile << "\t}," << std::endl;
        }
    }
    outfile << "]" << std::endl;

    outfile.close();

    return 0;
}
