package etu.leti.generator;


import etu.leti.field.Cell;
import java.util.Random;
import etu.leti.ore.Ore;
import etu.leti.ore.OreTypes;
import java.util.HashMap;



public class MapGenerator
{
    int currX = 0, currY = 0, randX, randY, randOre, randSize, countVeinsInSector, rand, veinX, veinY;

    private void changeQuarter(int arg, int x, int y)
    {
        if (arg==0)//Меняем крайние точки в зависимости от четверти, где находимся
        { currX = 0; currY = 0;}
        if (arg==1)
        { currX = x/2; currY = 0;}
        if (arg==2)
        { currX = 0; currY = y/2;}
        if (arg==3)
        { currX = x/2; currY = y/2;}
    }



    public Cell[][] generateMap(int x, int y)
    {

        HashMap<Integer, OreTypes> ore = new HashMap<>();
        ore.put(1, OreTypes.GOLD_ORE);
        ore.put(2, OreTypes.IRON_ORE);
        ore.put(3, OreTypes.STONE_ORE);
        ore.put(4, OreTypes.HELL);
        ore.put(10, OreTypes.GROUND);

        HashMap<Integer, String> picture = new HashMap<>();
        picture.put(1, "gold.png");
        picture.put(2, "iron.png");
        picture.put(3, "stone.png");
        picture.put(4, "hell.png");
        picture.put(10, "ground.png");

        Cell[][] field = new Cell[x][y];

        Random random = new Random();
        rand = random.nextInt(x);
        field[rand][y] = new Cell(rand,y, new Ore(picture.get(4),ore.get(5)));//Постановка ада

        for (int i = 0; i<4; i++)
        {
            rand = random.nextInt(100 ) + 1;//Рандомим 1 или 2 жилы в каждой четверти с вероятностью 70% в бОльшую сторону
            if (rand>30)
            {
                countVeinsInSector = 2;
            }
            else
                countVeinsInSector = 1;

            changeQuarter(i,x,y);

            for (int n = 0; n< countVeinsInSector; n++)
            {
                randX = random.nextInt((x / 2)-1) + currX;//Радномим точку спауна руды
                randY = random.nextInt((x / 2)-1) + currY;
                randOre =  random.nextInt(2) + 1;//Радномим тип руды
                randSize = random.nextInt(4) + random.nextInt(3) + 1;//Радномим размер жилы нормальный распределением (почти)
                for(int g = 0;g<10;g++)//Даем 10 попыток попасть "сердцем" жилы в пустое место
                {
                    if (field[randX][randY] == null)
                    {
                        g=10;
                        field[randX][randY] = new Cell(randX, randY, new Ore(picture.get(randOre), ore.get(randOre)));//Ставим первую руду
                        for (int k = 0; k < randSize; k++)
                        {
                            veinX = randX;//Запоминаем точку, где поставили
                            veinY = randY;
                            randX = random.nextInt(2) - 1;//Радномим Отклонение
                            if (randX == 0) //если по х отклонение 0, то смотрим у
                            {
                                randY = random.nextInt(2) - 1;//существует вероятность два раза получить 0, 1\9 примерно, можно поправить, но пока забил
                            }
                            else
                                randY = 0;//если по х УЖЕ есть отклонение, то у делаем 0, так как по-диагонали нельзя ставить руду
                            if (veinX + randX <x && veinY + randY <y && veinX + randX <0 && veinY + randY <0)
                            {
                                if (field[veinX + randX][veinY + randY] == null)//Проверка на то, что не попали в другую жилу
                                {
                                    veinX += randX;//Сменяем последнюю координату жилы
                                    veinY += randY;
                                    field[veinX][veinY] = new Cell(veinX, veinY, new Ore(picture.get(randOre), ore.get(randOre)));//Ставим блок
                                }
                            }
                        }
                    }
                    else
                    {
                        randX = random.nextInt((x / 2)-1) + currX;//Радномим заново точку спауна руды
                        randY = random.nextInt((x / 2)-1) + currY;
                    }
                }
            }

        }
        for (int i = 0;i<y;i++)
        {
            for (int j = 0;j<x;j++)
            {
                if (field[x][y] == null)
                {
                    field[x][y] = new Cell(x, y, new Ore(picture.get(10), ore.get(10)));//Заполняем землей все пустые клетки
                }
            }
        }
        return field;
    }
}
