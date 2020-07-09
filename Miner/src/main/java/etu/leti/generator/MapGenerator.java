package etu.leti.generator;

import etu.leti.field.Cell;
import java.util.Random;
import etu.leti.ore.Ore;
import etu.leti.ore.OreTypes;
import java.util.HashMap;

public class MapGenerator
{
    int currX = 0, currY = 0, randX = 0, randY = 0;
    int randOre = 0, randSize = 0, countVeinsInSector = 0, rand = 0;
    int veinX = 0, veinY = 0;
    int x = 0 , y = 0;

    Cell[][] field;
    HashMap<Integer, OreTypes> ore;
    HashMap<Integer, String> picture;
    Random random;

    public MapGenerator(int initX, int initY) {
       random = new Random();
       x = initX;
       y = initY;
       field = new Cell[x][y];
       initOreList();
    }

    private void initOreList() {
        ore = new HashMap<>();
        ore.put(1, OreTypes.GOLD_ORE);
        ore.put(2, OreTypes.IRON_ORE);
        ore.put(3, OreTypes.STONE_ORE);
        ore.put(4, OreTypes.HELL);
        ore.put(10, OreTypes.GROUND);
        ore.put(11, OreTypes.AIR);

        picture = new HashMap<>();
        picture.put(1, "gold.jpg");
        picture.put(2, "iron.jpg");
        picture.put(3, "stone.jpg");
        picture.put(4, "hell.jpeg");
        picture.put(10, "ground.jpg");
        picture.put(11, "air.jpg");
    }

    private void changeQuarter(int arg, int x, int y) {
        if (arg==0)//Меняем крайние точки в зависимости от четверти, где находимся
        { currX = 0; currY = 0;}
        if (arg==1)
        { currX = x/2; currY = 0;}
        if (arg==2)
        { currX = 0; currY = y/2;}
        if (arg==3)
        { currX = x/2; currY = y/2;}
    }

    private void randomizeOre(int arg, int x, int y) {

        randX = random.nextInt((x / 2)) + currX;//Радномим точку спауна руды
        randY = random.nextInt((x / 2)) + currY;
        if (arg == 1) {
            randOre = random.nextInt(3) + 1;//Радномим тип руды
            randSize = random.nextInt(4) + random.nextInt(4) + 2;//Радномим размер жилы нормальный распределением (почти)
        }
    }

    private void randomizeVeinsCount() {
        rand = random.nextInt(100 ) + 1;//Рандомим 1 или 2 жилы в каждой четверти с вероятностью 70% в бОльшую сторону
        if (rand>60) {
            countVeinsInSector = 3;
        }
        if (rand>10) {
            countVeinsInSector = 2;
        }
        else
            countVeinsInSector = 1;
    }


    private void dirtAndAirFiller(int x, int y) {
        for (int i = 0;i<1;i++) {
            for (int j = 0; j < x; j++){
                field[j][i] = new Cell(j, i, new Ore(picture.get(11), ore.get(11)));//Заполняем землей все пустые клетки
            }
        }
        for (int i = 1;i<y;i++) {
            for (int j = 0; j < x; j++) {
                if (field[j][i] == null) {
                    field[j][i] = new Cell(j, i, new Ore(picture.get(10), ore.get(10)));//Заполняем землей все пустые клетки
                }
            }
        }
    }

    public void reset() {
        currX = 0; currY = 0; randX = 0; randY = 0;
        randOre = 0; randSize = 0; countVeinsInSector = 0; rand = 0;
        veinX = 0; veinY = 0;
        field = new Cell[x][y];
    }

    private int checkNeighbors(int xC, int yC, int prevX, int PrevY) {
       for(int i = yC-1;i<yC+1;++i)
       {
           for(int j = xC-1;j<xC+1;++j)
           {
               if (i>0 && j>0 && j<x && i<y) {
                   if(field[j][i]!=null )
                   {
                       if (field[j][i].getOre().getOreType()!=field[prevX][PrevY].getOre().getOreType())
                       {
                           return 0;
                       }
                   }
               }
           }
       }
       return 1;
    }

    public Cell[][] generateMap() {
        rand = random.nextInt(x);
        field[rand][y-1] = new Cell(rand, y-1, new Ore(picture.get(4), ore.get(4)));//Постановка ада

        for (int i = 0; i < 4; ++i) {
            randomizeVeinsCount();
            changeQuarter(i, x, y);
            for (int n = 0; n < countVeinsInSector; ++n) {
                randomizeOre(1, x, y);
                for(int g = 0; g < 10; ++g) { // Даём 10 попыток попасть "сердцем" жилы в пустое место
                    if (field[randX][randY] == null) {
                        g = 10;
                        field[randX][randY] = new Cell(randX, randY, new Ore(picture.get(randOre), ore.get(randOre)));//Ставим первую руду
                        veinX = randX;//Запоминаем точку, где поставили
                        veinY = randY;
                        for (int k = 0; k < randSize; ++k) {
                            randX = random.nextInt(3) - 1;//Радномим Отклонение
                            if (randX == 0) { //если по х отклонение 0, то смотрим у
                                randY = random.nextInt(3) - 1;//существует вероятность два раза получить 0, 1\9 примерно, можно поправить, но пока забил
                                if (randX == 0 && randY == 0){
                                    k--;
                                }
                            }
                            else
                                randY = 0;//если по х УЖЕ есть отклонение, то у делаем 0, так как по-диагонали нельзя ставить руду
                            if (veinX + randX < x && veinY + randY <y && veinX + randX > 0 && veinY + randY > 0) {
                                if (field[veinX + randX][veinY + randY] == null && checkNeighbors(veinX+ randX,veinY + randY,veinX,veinY)==1) {//Проверка на то, что не попали в другую жилу
                                    veinX += randX;//Сменяем последнюю координату жилы
                                    veinY += randY;
                                    field[veinX][veinY] = new Cell(veinX, veinY, new Ore(picture.get(randOre), ore.get(randOre)));//Ставим блок
                                }
                                //else
                                    //k--;
                            }
                        }
                    } else {
                        randomizeOre(0, x, y);
                    }
                }
            }

        }
        dirtAndAirFiller(x, y);
        return field;
    }
}
