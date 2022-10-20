package com.voyager.jolybell.model;

import java.util.ArrayList;
import java.util.Arrays;

public class GetCatalogItems {

    public static void SetCategorys(){
        // Categorys
        CategoryItems.categorys = new ArrayList<>();
        CategoryItems.categorys.add(new Category(0, "Футболки"));
        CategoryItems.categorys.add(new Category(1, "Свитшоты"));
        CategoryItems.categorys.add(new Category(2, "Худи"));
        CategoryItems.categorys.add(new Category(3, "Штаны/шорты"));
        CategoryItems.categorys.add(new Category(4, "Поло"));
        CategoryItems.categorys.add(new Category(5, "Рубашки"));
        CategoryItems.categorys.add(new Category(6, "Кепки"));
        CategoryItems.categorys.add(new Category(7, "Шапки"));
        CategoryItems.categorys.add(new Category(8, "Трусы"));
        CategoryItems.categorys.add(new Category(9, "Рюкзаки"));
        CategoryItems.categorys.add(new Category(10, "Сувениры"));
        CategoryItems.categorys.add(new Category(11, "Мемы"));
    }

    public static void GetCatalogItems(){
        CatalogItems.items = new ArrayList<>();
        // Футболки
        // item 1
        CatalogItems.items.add(
                new ItemFromShopBuilder()
                        .setItemId(CatalogItems.items.size())
                        .setName("B.O.M.J Black")
                        .setCosts(new float[]{
                                (float) 24.25, // USD
                                (float) 20.53, // EUR
                                (float) 1750,  // RUB
                                (float) 625    // UAH
                        })
                        .setDescription("Тонкая. Легкая. Черная.\n" +
                                "        \n" +
                                "Футболка линейки B.O.M.J - первая единица контрбрендовой линии одежды. Потрясающие качество в совместительстве с уточненным подходом к деталям, которым не могут похвастаться именитые раздутые фирмы.\n" +
                                "\n" +
                                "Ткань сорта пенье\n" +
                                "\n" +
                                "Состав: 95% cotton, 5% spandex. Плотность 170 г/м²")
                        .setImg(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "https://cdn.jolybell.com/images/ucPsORdrx3gjKUU.webp?width=765&height=840&quality=90",
                                        "https://cdn.jolybell.com/images/6KqdoQNQqbJ8RKy.webp?width=765&height=843&quality=90",
                                        "https://cdn.jolybell.com/images/v5wgeZmy0P2AsTu.webp?width=765&height=494&quality=90"
                                }))
                        )
                        .setSizes(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "XS",
                                        "S",
                                        "M",
                                        "L",
                                        "XL",
                                        "2XL",
                                        "3XL"
                                }))
                        )
                        .setCategoryId(0)
                        .build()
        );
        // item 2
        CatalogItems.items.add(
                new ItemFromShopBuilder()
                        .setItemId(CatalogItems.items.size())
                        .setName("B.O.M.J White")
                        .setCosts(new float[]{
                                (float) 24.25, // USD
                                (float) 20.53, // EUR
                                (float) 1750,  // RUB
                                (float) 625    // UAH
                        })
                        .setDescription("Тонкая. Легкая. Белая.\n" +
                                "\n" +
                                "Футболка линейки B.O.M.J - первая единица контрбрендовой линии одежды. Потрясающие качество в совместительстве с уточненным подходом к деталям, которым не могут похвастаться именитые раздутые фирмы.\n" +
                                "\n" +
                                "Ткань сорта пенье\n" +
                                "\n" +
                                "Состав: 95% cotton, 5% spandex. Плотность 170 г/м²")
                        .setImg(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "https://cdn.jolybell.com/images/23krhtEjb4SgZcQ.webp?width=765&height=840&quality=90",
                                        "https://cdn.jolybell.com/images/Q1eA8G3HR8Idq3x.webp?width=765&height=843&quality=90"
                                }))
                        )
                        .setSizes(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "XS",
                                        "S",
                                        "M",
                                        "L",
                                        "XL",
                                        "2XL",
                                        "3XL"
                                }))
                        )
                        .setCategoryId(0)
                        .setTheme(ItemFromShop.Theme.Light)
                        .build()
        );
        // item 3
        CatalogItems.items.add(
                new ItemFromShopBuilder()
                        .setItemId(CatalogItems.items.size())
                        .setName("Tryapka")
                        .setCosts(new float[]{
                                (float) 24.25, // USD
                                (float) 20.53, // EUR
                                (float) 1750,  // RUB
                                (float) 625    // UAH
                        })
                        .setDescription("Трудно не заметить поднявшийся с лужи тренд на безвкусную тряпку за 30 тысяч, что примечательно, истории не известен ни один случай, когда она была бы куплена в оригинале. Мы не могли не удержаться, что бы над этим не поглумиться.\n" +
                                "\n" +
                                "Ткань сорта пенье\n" +
                                "\n" +
                                "Состав: 95% cotton, 5% spandex. Плотность 170 г/м²")
                        .setImg(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "https://cdn.jolybell.com/images/oFx2NyWY1bTjEq7.webp?width=765&height=840&quality=90",
                                        "https://cdn.jolybell.com/images/J196fqMbygDe4vD.webp?width=765&height=843&quality=90"
                                }))
                        )
                        .setSizes(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "XS",
                                        "S",
                                        "M",
                                        "L",
                                        "XL",
                                        "2XL",
                                        "3XL"
                                }))
                        )
                        .setCategoryId(0)
                        .build()
        );

        // Свитшоты
        // item 4
        CatalogItems.items.add(
                new ItemFromShopBuilder()
                        .setItemId(CatalogItems.items.size())
                        .setName("JOLY.SHOT B.O.M.J Black")
                        .setCosts(new float[]{
                                (float) 34.91, // USD
                                (float) 29.55, // EUR
                                (float) 2550,  // RUB
                                (float) 925    // UAH
                        })
                        .setDescription("Все еще контрбрендово, но только теплее. Согревает не любовь, а ткань.\n" +
                                "\n" +
                                "Состав: 60% cotton, 40% polyester. Плотность 320 г/м²")
                        .setImg(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "https://cdn.jolybell.com/images/55qlqQNta3nmPHJ.png?width=765&height=644",
                                        "https://cdn.jolybell.com/images/7SbLZKVpKaF1luB.png?width=765&height=644",
                                        "https://cdn.jolybell.com/images/o5ChAV1K4O9yvcH.png?width=765&height=945",
                                        "https://cdn.jolybell.com/images/C3pRIt5AuDU93Ap.png?width=765&height=1073"
                                }))
                        )
                        .setSizes(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "XS",
                                        "S",
                                        "M",
                                        "L",
                                        "XL",
                                        "2XL",
                                        "3XL"
                                }))
                        )
                        .setCategoryId(1)
                        .build()
        );
        // item 5
        CatalogItems.items.add(
                new ItemFromShopBuilder()
                        .setItemId(CatalogItems.items.size())
                        .setName("JOLY.SHOT Mirror Black")
                        .setCosts(new float[]{
                                (float) 34.91, // USD
                                (float) 29.55, // EUR
                                (float) 2550,  // RUB
                                (float) 925    // UAH
                        })
                        .setDescription("Зеркальная надпись\n" +
                                "\n" +
                                "Осторожно, есть вероятность невольно вертеть чужими головами в округе. Подсказка расшифровки таится в зеркале. Этот продукт - концентрация всей нашей идеологии в теплом формате.\n" +
                                "\n" +
                                "Состав: 60% cotton, 40% polyester. Плотность 320 г/м²")
                        .setImg(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "https://cdn.jolybell.com/images/4EZvCxHPg3tJEXm.png?width=765&height=644",
                                        "https://cdn.jolybell.com/images/Y1kF05bFqYmeYlM.png?width=765&height=644",
                                        "https://cdn.jolybell.com/images/4Rbw6TxK6Pbl9Aa.png?width=765&height=964",
                                        "https://cdn.jolybell.com/images/ibZX8pubTKaKGDe.png?width=765&height=1073"
                                }))
                        )
                        .setSizes(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "XS",
                                        "S",
                                        "M",
                                        "L",
                                        "XL",
                                        "2XL",
                                        "3XL"
                                }))
                        )
                        .setCategoryId(1)
                        .build()
        );

        // Худи

        // item 6
        CatalogItems.items.add(
                new ItemFromShopBuilder()
                        .setItemId(CatalogItems.items.size())
                        .setName("Худи ZIP Black")
                        .setCosts(new float[]{
                                (float) 53.65, // USD
                                (float) 45.41, // EUR
                                (float) 3900,  // RUB
                                (float) 1425    // UAH
                        })
                        .setDescription("53.65 USD\n" +
                                "Худи свободного кроя изготовлено из плотного, премиального качества трикотажа сорта пенье, с добавлением приятного начеса. Внутренняя отделка из сетки. Модель с карманами, эластичными манжетами на рукавах и поясе дополнена застёжкой-молнией и капюшоном.\n" +
                                "\n" +
                                "Состав 70% cotton, (внешняя часть) 30% polyester (начес, внутренняя)\n" +
                                "\n" +
                                "*Важно! Ознакомьтесь с размерной сеткой, пошив и размеры отличаются от других (старых) худи/свитшотов.")
                        .setImg(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "https://cdn.jolybell.com/images/mx8wqsBKyYfDCLT.webp?width=434&height=540&quality=90",
                                        "https://cdn.jolybell.com/images/L0Lq5PmvxfnwNrC.webp?width=420&height=540&quality=90",
                                        "https://cdn.jolybell.com/images/lZ7nfT8XeGe9pNV.webp?width=607&height=540&quality=90",
                                        "https://cdn.jolybell.com/images/bT27SMaR9VKiVU9.webp?width=654&height=540&quality=90",
                                        "https://cdn.jolybell.com/images/WvmdWnXO0G5SSCd.webp?width=304&height=540&quality=90"
                                }))
                        )
                        .setSizes(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "S",
                                        "M",
                                        "L",
                                        "XL",
                                        "2XL"
                                }))
                        )
                        .setCategoryId(2)
                        .build()
        );

        // Штаны/шорты

        // item 7
        CatalogItems.items.add(
                new ItemFromShopBuilder()
                        .setItemId(CatalogItems.items.size())
                        .setName("Штаны B.O.M.J")
                        .setCosts(new float[]{
                                (float) 45.56, // USD
                                (float) 38.56, // EUR
                                (float) 3300,  // RUB
                                (float) 1200   // UAH
                        })
                        .setDescription("Это штаны.\n" +
                                "\n" +
                                "Ткань футер петля. Состав: 80% cotton, 20% polyester\n" +
                                "\n" +
                                "Эластичный пояс на шнурках, карманы на змейке, карман сзади, декоративные швы спереди, лого B.O.M.J нанесено методом машинной вышивки.")
                        .setImg(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "https://cdn.jolybell.com/images/Ai4PE4QOHLzqIPc.webp?width=282&height=540&quality=90",
                                        "https://cdn.jolybell.com/images/rCbjpM8ldycTerr.webp?width=405&height=540&quality=90"
                                }))
                        )
                        .setSizes(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "XS",
                                        "S",
                                        "M",
                                        "L",
                                        "XL",
                                        "2XL",
                                        "3XL"
                                }))
                        )
                        .setCategoryId(3)
                        .build()
        );


        // Сувениры

        // item 8
        CatalogItems.items.add(
                new ItemFromShopBuilder()
                        .setItemId(CatalogItems.items.size())
                        .setName("Кружка хамелеон B.O.M.J")
                        .setCosts(new float[]{
                                (float) 11.02, // USD
                                (float) 9.33,  // EUR
                                (float) 800,   // RUB
                                (float) 275    // UAH
                        })
                        .setDescription("При наполнении кипятком, на кружке проявляется изображение B.O.M.J\n" +
                                "\n" +
                                "Объем 330 мл")
                        .setImg(
                                new ArrayList<>(Arrays.asList(new String[]{
                                        "https://cdn.jolybell.com/images/csRxS4QpXCKHMoO.webp?width=771&height=540&quality=90"
                                }))
                        )
                        .setCategoryId(10)
                        .setTheme(ItemFromShop.Theme.Light)
                        .build()
        );
    }
}
