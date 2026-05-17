package ui.pickers;

import fri.shapesge.Image;
import fri.shapesge.Square;
import fri.shapesge.TextBlock;
import monkey.MonkeyType;
import utils.Vector2D;

public class MonkeyPickersFactory {
    private static MonkeyPickersFactory instance;
    private static final MonkeyType[] MONKEYS = MonkeyType.values();

    private MonkeyPickersFactory() {
    }

    /**
     * Vracia singleton továrne na vytváranie pickerov pre opice.
     *
     * @return jediná inštancia MonkeyPickersFactory
     */
    public static MonkeyPickersFactory getInstance() {
        if (MonkeyPickersFactory.instance == null) {
            MonkeyPickersFactory.instance = new MonkeyPickersFactory();
        }
        return MonkeyPickersFactory.instance;
    }

    /**
     * Vytvorí vizuálne pickery pre všetky typy opíc a umiestni ich na bočný panel.
     */
    public void createPickers() {
        int startX = 1550;
        int startY = 20;
        int columnSpacing = 90;
        int rowSpacing = 110;
        int rectSize = 90;

        for (int i = 0; i < MonkeyPickersFactory.MONKEYS.length; i++) {
            int column = i % 2;
            int row = i / 2;

            int currentX = startX + (column * columnSpacing);
            int currentY = startY + (row * rowSpacing);

            Vector2D position = new Vector2D(currentX, currentY);

            int rectX = currentX - 13;
            int rectY = currentY - 13;

            Square background = new Square(rectX, rectY);
            background.changeSize(rectSize);
            background.changeColor("light gray");
            background.makeVisible();

            int price = MonkeyType.valueOf(MONKEYS[i].name()).getCost();
            new MonkeyPicker(MonkeyPickersFactory.MONKEYS[i].name(), price, position);

            int textX = rectX + 10;
            int textY = rectY + rectSize + 15;

            TextBlock priceText = new TextBlock(String.valueOf(price), textX, textY);
            priceText.changeColor("black");
            priceText.makeVisible();

            int dollarX = textX + 35;
            int dollarY = textY - 12;

            Image dollarIcon = new Image("res/assets/misc/dollar.png", dollarX, dollarY);
            dollarIcon.makeVisible();
        }
    }
}
