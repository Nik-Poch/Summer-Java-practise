package etu.leti.generator;


import etu.leti.field.Cell;
import java.util.Random;
import etu.leti.ore.Ore;
import etu.leti.ore.OreTypes;
import java.util.HashMap;



public class Generator
{



    public Cell[][] generate_map(int x, int y)
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
        int curr_x = 0, curr_y = 0, rand_x, rand_y, rand_ore, rand_size;
        int count_veins_in_sector;
        Random random = new Random();
        int rand = random.nextInt(x);
        field[rand][y] = new Cell(rand,y, new Ore(picture.get(4),ore.get(5)));/**Постановка ада*/

        for (int i = 0; i<4; i++)
        {
            rand = random.nextInt(100 ) + 1;/**Рандомим 1 или 2 жилы в каждой четверти с вероятностью 70% в бОльшую сторону*/
            if (rand>30)
            {
                count_veins_in_sector = 2;
            }
            else
                count_veins_in_sector = 1;

            if (i==0)/**Меняем крайние точки в зависимости от четверти, где находимся*/
            { curr_x = 0; curr_y = 0;}
            if (i==1)
            { curr_x = x/2; curr_y = 0;}
            if (i==2)
            { curr_x = 0; curr_y = y/2;}
            if (i==3)
            { curr_x = x/2; curr_y = y/2;}

            for (int n = 0; n<count_veins_in_sector;n++)
            {
                rand_x = random.nextInt((x / 2)-1) + curr_x;/**Радномим точку спауна руды*/
                rand_y = random.nextInt((x / 2)-1) + curr_y;
                rand_ore =  random.nextInt(2) + 1;/**Радномим тип руды*/
                rand_size = random.nextInt(4) + random.nextInt(3) + 1;/**Радномим размер жилы нормальный распределением (почти) */
                for(int g = 0;g<10;g++)/**Даем 10 попыток попасть "сердцем" жилы в пустое место*/
                {
                    if (field[rand_x][rand_y] == null)
                    {
                        g=10;
                        field[rand_x][rand_y] = new Cell(rand_x, rand_y, new Ore(picture.get(rand_ore), ore.get(rand_ore)));/**Ставим первую руду*/
                        for (int k = 0; k < rand_size; k++)
                        {
                            int vein_x = rand_x, vein_y = rand_y;/**Запоминаем точку, где поставили*/
                            rand_x = random.nextInt(2) - 1;/**Радномим Отклонение*/
                            if (rand_x == 0) /**если по х отклонение 0, то смотрим у*/
                            {
                                rand_y = random.nextInt(2) - 1;/**существует вероятность два раза получить 0, 1\9 примерно, можно поправить, но пока забил*/
                            }
                            else
                                rand_y = 0;/**если по х УЖЕ есть отклонение, то у делаем 0, так как по-диагонали нельзя ставить руду*/
                            if (vein_x + rand_x<x && vein_y + rand_y<y)
                            {
                                if (field[vein_x + rand_x][vein_y + rand_y] == null)/**Проверка на то, что не попали в другую жилу*/
                                {
                                    vein_x += rand_x;/**Сменяем последнюю координату жилы*/
                                    vein_y += rand_y;
                                    field[vein_x][vein_y] = new Cell(vein_x, vein_y, new Ore(picture.get(rand_ore), ore.get(rand_ore)));/**Ставим блок*/
                                }
                            }
                        }
                    }
                    else
                    {
                        rand_x = random.nextInt((x / 2)-1) + curr_x;/**Радномим заново точку спауна руды*/
                        rand_y = random.nextInt((x / 2)-1) + curr_y;
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
                    field[x][y] = new Cell(x, y, new Ore(picture.get(10), ore.get(10)));/**Заполняем землей все пустые клетки*/
                }
            }
        }
        return field;
    }
}
