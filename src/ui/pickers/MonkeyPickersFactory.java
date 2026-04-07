package ui.pickers;

import fri.shapesge.Square;
import utils.Vector2D;

public class MonkeyPickersFactory {
    private static MonkeyPickersFactory instance;
    private static final String[] MONKEYS = new String[]{"dart", "ice", "cannon", "sniper", "dartling"};

    private MonkeyPickersFactory() {
    }

    public static MonkeyPickersFactory getInstance() {
        if (MonkeyPickersFactory.instance == null) {
            MonkeyPickersFactory.instance = new MonkeyPickersFactory();
        }
        return MonkeyPickersFactory.instance;
    }

    public void createPickers() {
        int startX = 1550;
        int startY = 20;
        int columnSpacing = 90;
        int rowSpacing = 90;
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

            new MonkeyPicker(MonkeyPickersFactory.MONKEYS[i], (i + 1) * 100, position);
        }
    }
}
